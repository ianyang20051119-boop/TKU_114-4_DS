public class RecursiveTextTools {

    public static String reverse(String text) {
        if (text == null || text.length() <= 1) {
            return text;
        }

        return reverse(text.substring(1)) + text.charAt(0);
    }

    public static boolean isPalindrome(String text) {
        if (text == null) {
            return false;
        }

        String cleaned = text.replaceAll("\\s+", "").toLowerCase();

        return isPalindrome(cleaned, 0, cleaned.length() - 1);
    }

    private static boolean isPalindrome(String text, int left, int right) {
        if (left >= right) {
            return true;
        }

        if (text.charAt(left) != text.charAt(right)) {
            return false;
        }

        return isPalindrome(text, left + 1, right - 1);
    }

    public static int countCharacter(String text, char target) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        return (text.charAt(0) == target ? 1 : 0)
                + countCharacter(text.substring(1), target);
    }

    public static void main(String[] args) {

        System.out.println("Empty:");
        System.out.println("reverse = " + reverse(""));
        System.out.println("isPalindrome = " + isPalindrome(""));
        System.out.println("countCharacter = " + countCharacter("", 'a'));

        System.out.println();

        System.out.println("Single character:");
        System.out.println("reverse = " + reverse("A"));
        System.out.println("isPalindrome = " + isPalindrome("A"));
        System.out.println("countCharacter = " + countCharacter("A", 'A'));

        System.out.println();

        System.out.println("Level:");
        System.out.println("reverse = " + reverse("Level"));
        System.out.println("isPalindrome = " + isPalindrome("Level"));
        System.out.println("countCharacter = " + countCharacter("Level", 'e'));

        System.out.println();

        System.out.println("General string:");
        System.out.println("reverse = " + reverse("Hello World"));
        System.out.println("isPalindrome = " + isPalindrome("Hello World"));
        System.out.println("countCharacter = " + countCharacter("Hello World", 'l'));

        System.out.println();

        System.out.println("A man a plan a canal Panama:");
        System.out.println("isPalindrome = "
                + isPalindrome("A man a plan a canal Panama"));
    }
}