public class Q08_RecursiveAudit {

    public static int sumValid(int[] data, int index) {
        // recursion-proof C8-41
        if (data == null || data.length == 0) {
            return 0;
        }

        if (index < 0) {
            index = 0;
        }

        if (index >= data.length) {
            return 0;
        }

        int current = data[index];

        if (current >= 0 && current <= 100) {
            return current + sumValid(data, index + 1);
        }

        return sumValid(data, index + 1);
    }

    public static int countOccurrences(int[] data, int index, int target) {
        if (data == null || data.length == 0) {
            return 0;
        }

        if (index < 0) {
            index = 0;
        }

        if (index >= data.length) {
            return 0;
        }

        int count = data[index] == target ? 1 : 0;

        return count + countOccurrences(data, index + 1, target);
    }

    public static boolean isPalindrome(String text, int left, int right) {
        if (text == null) {
            return false;
        }

        if (left >= right) {
            return true;
        }

        if (left < 0 || right >= text.length()) {
            return false;
        }

        char leftChar = Character.toLowerCase(text.charAt(left));
        char rightChar = Character.toLowerCase(text.charAt(right));

        if (leftChar != rightChar) {
            return false;
        }

        return isPalindrome(text, left + 1, right - 1);
    }
}