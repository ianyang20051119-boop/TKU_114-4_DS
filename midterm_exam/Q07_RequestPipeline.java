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

        for (char c : text.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                if ((c == ')' && top != '(')
                        || (c == ']' && top != '[')
                        || (c == '}' && top != '{')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static List<String> process(String[] commands) {
        List<String> result = new ArrayList<>();

        if (commands == null) {
            return result;
        }

        Deque<String> normalQueue = new ArrayDeque<>();
        Deque<String> urgentQueue = new ArrayDeque<>();

        for (String command : commands) {
            if (command == null || command.trim().isEmpty()) {
                continue;
            }

            String[] parts = command.trim().split("\\s+");

            if (parts.length != 2 && parts.length != 1) {
                continue;
            }

            String type = parts[0];

            if (type.equals("NORMAL") && parts.length == 2
                    && !parts[1].isEmpty()) {
                normalQueue.offer(parts[1]);
            } else if (type.equals("URGENT") && parts.length == 2
                    && !parts[1].isEmpty()) {
                urgentQueue.offer(parts[1]);
            } else if (type.equals("PROCESS") && parts.length == 1) {
                if (!urgentQueue.isEmpty()) {
                    result.add(takeUrgentCheckpoint(urgentQueue));
                } else if (!normalQueue.isEmpty()) {
                    result.add(normalQueue.poll());
                } else {
                    result.add("EMPTY");
                }
            }
        }

        return result;
    }

    private static String takeUrgentCheckpoint(Deque<String> urgentQueue) {
        return urgentQueue.poll();
    }
}