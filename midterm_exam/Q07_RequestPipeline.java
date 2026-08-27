import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q07_RequestPipeline {
    public static boolean isBalanced(String text) {
        if (text == null) {
            return false;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : text.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    return false;
                }

                char open = stack.pop();
                if ((ch == ')' && open != '(')
                        || (ch == ']' && open != '[')
                        || (ch == '}' && open != '{')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static String takeUrgentCheckpoint(Deque<String> urgent) {
        return urgent.removeFirst();
    }

    public static List<String> process(String[] commands) {
        List<String> result = new ArrayList<>();
        if (commands == null) {
            return result;
        }

        Deque<String> normal = new ArrayDeque<>();
        Deque<String> urgent = new ArrayDeque<>();

        for (String command : commands) {
            if (command == null) {
                continue;
            }

            String trimmed = command.trim();
            if (trimmed.isEmpty()) {
                continue;
            }

            String[] parts = trimmed.split("\\s+");

            if (parts.length == 2 && parts[0].equals("NORMAL") && !parts[1].isEmpty()) {
                normal.addLast(parts[1]);
            } else if (parts.length == 2 && parts[0].equals("URGENT") && !parts[1].isEmpty()) {
                urgent.addLast(parts[1]);
            } else if (parts.length == 1 && parts[0].equals("PROCESS")) {
                if (!urgent.isEmpty()) {
                    result.add(takeUrgentCheckpoint(urgent));
                } else if (!normal.isEmpty()) {
                    result.add(normal.removeFirst());
                } else {
                    result.add("EMPTY");
                }
            }
        }

        return result;
    }
}
