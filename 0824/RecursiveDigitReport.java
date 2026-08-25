public class RecursiveDigitReport {

    public static int digitSum(int number) {
        number = Math.abs(number);

        if (number < 10) {
            return number;
        }

        return number % 10 + digitSum(number / 10);
    }

    public static int digitCount(int number) {
        number = Math.abs(number);

        if (number < 10) {
            return 1;
        }

        return 1 + digitCount(number / 10);
    }

    public static int countDigit(int number, int target) {
        number = Math.abs(number);

        if (number < 10) {
            return number == target ? 1 : 0;
        }

        return (number % 10 == target ? 1 : 0)
                + countDigit(number / 10, target);
    }

    public static void main(String[] args) {

        System.out.println("50205");
        System.out.println("digitSum = " + digitSum(50205));
        System.out.println("digitCount = " + digitCount(50205));
        System.out.println("countDigit(5) = " + countDigit(50205, 5));

        System.out.println();

        System.out.println("0");
        System.out.println("digitSum = " + digitSum(0));
        System.out.println("digitCount = " + digitCount(0));
        System.out.println("countDigit(0) = " + countDigit(0, 0));

        System.out.println();

        System.out.println("-731");
        System.out.println("digitSum = " + digitSum(-731));
        System.out.println("digitCount = " + digitCount(-731));
        System.out.println("countDigit(7) = " + countDigit(-731, 7));
    }
}