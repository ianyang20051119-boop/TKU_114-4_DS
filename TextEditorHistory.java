import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorHistory {

    private Deque<String> undoStack = new ArrayDeque<>();
    private Deque<String> redoStack = new ArrayDeque<>();

    public void add(String text) {
        undoStack.push(text);
        redoStack.clear();
        printState("新增：" + text);
    }

    public String undo() {
        if (undoStack.isEmpty()) {
            printState("Undo：無法操作");
            return null;
        }

        String text = undoStack.pop();
        redoStack.push(text);
        printState("Undo：" + text);
        return text;
    }

    public String redo() {
        if (redoStack.isEmpty()) {
            printState("Redo：無法操作");
            return null;
        }

        String text = redoStack.pop();
        undoStack.push(text);
        printState("Redo：" + text);
        return text;
    }

    private void printState(String operation) {
        System.out.println(operation);
        System.out.println("Undo Stack：" + undoStack);
        System.out.println("Redo Stack：" + redoStack);
        System.out.println();
    }

    public static void main(String[] args) {

        TextEditorHistory editor = new TextEditorHistory();

        editor.add("Hello");
        editor.add("Hello World");
        editor.add("Hello World!");

        editor.undo();
        editor.undo();

        editor.redo();

        editor.add("Hello Java");

        editor.undo();
        editor.redo();

        editor.undo();
        editor.undo();
        editor.undo();

        editor.undo();

        editor.redo();
        editor.redo();
        editor.redo();
        editor.redo();
    }
}