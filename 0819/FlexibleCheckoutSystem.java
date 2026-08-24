interface PricingPolicy {
    double calculatePrice(double originalPrice);
    String getName();
}

class OriginalPricePolicy implements PricingPolicy {

    @Override
    public double calculatePrice(double originalPrice) {
        return originalPrice < 0 ? 0 : originalPrice;
    }

    @Override
    public String getName() {
        return "原價";
    }
}

class VipPricingPolicy implements PricingPolicy {

    @Override
    public double calculatePrice(double originalPrice) {
        if (originalPrice < 0) {
            return 0;
        }

        return originalPrice * 0.85;
    }

    @Override
    public String getName() {
        return "VIP 八五折";
    }
}

class Over2000DiscountPolicy implements PricingPolicy {

    @Override
    public double calculatePrice(double originalPrice) {
        if (originalPrice < 0) {
            return 0;
        }

        if (originalPrice >= 2000) {
            return originalPrice - 300;
        }

        return originalPrice;
    }

    @Override
    public String getName() {
        return "滿 2000 折 300";
    }
}

interface NotificationChannel {
    boolean send(String orderId, String message);
    String getName();
}

class EmailChannel implements NotificationChannel {

    @Override
    public boolean send(String orderId, String message) {
        System.out.println("Email 通知：" + orderId + " - " + message);
        return true;
    }

    @Override
    public String getName() {
        return "Email";
    }
}

class SmsChannel implements NotificationChannel {

    @Override
    public boolean send(String orderId, String message) {
        System.out.println("SMS 通知：" + orderId + " - " + message);
        return true;
    }

    @Override
    public String getName() {
        return "SMS";
    }
}

class ConsoleChannel implements NotificationChannel {

    @Override
    public boolean send(String orderId, String message) {
        System.out.println("Console 通知：" + orderId + " - " + message);
        return true;
    }

    @Override
    public String getName() {
        return "Console";
    }
}

class CheckoutResult {
    private String orderId;
    private double originalPrice;
    private double finalPrice;
    private boolean notificationStatus;

    public CheckoutResult(
            String orderId,
            double originalPrice,
            double finalPrice,
            boolean notificationStatus) {

        this.orderId = orderId;
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.notificationStatus = notificationStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public boolean isNotificationStatus() {
        return notificationStatus;
    }

    public void printResult() {
        System.out.println("訂單編號：" + orderId);
        System.out.println("原始價格：" + originalPrice);
        System.out.println("最終價格：" + finalPrice);
        System.out.println("通知狀態：" + (notificationStatus ? "成功" : "失敗"));
    }
}

public class FlexibleCheckoutSystem {

    public static CheckoutResult checkout(
            String orderId,
            double originalPrice,
            PricingPolicy pricingPolicy,
            NotificationChannel notificationChannel) {

        if (orderId == null || orderId.trim().isEmpty()) {
            return new CheckoutResult(
                    orderId,
                    originalPrice,
                    0,
                    false
            );
        }

        if (pricingPolicy == null || notificationChannel == null) {
            return new CheckoutResult(
                    orderId,
                    originalPrice,
                    0,
                    false
            );
        }

        double validOriginalPrice = originalPrice < 0 ? 0 : originalPrice;

        double finalPrice =
                pricingPolicy.calculatePrice(validOriginalPrice);

        String message =
                "使用 " + pricingPolicy.getName()
                + "，結帳金額：" + finalPrice + " 元";

        boolean notificationStatus =
                notificationChannel.send(orderId, message);

        return new CheckoutResult(
                orderId,
                validOriginalPrice,
                finalPrice,
                notificationStatus
        );
    }

    public static void main(String[] args) {

        PricingPolicy original = new OriginalPricePolicy();
        PricingPolicy vip = new VipPricingPolicy();
        PricingPolicy discount = new Over2000DiscountPolicy();

        NotificationChannel email = new EmailChannel();
        NotificationChannel sms = new SmsChannel();
        NotificationChannel console = new ConsoleChannel();

        CheckoutResult result1 =
                checkout("O001", 1500, original, email);

        CheckoutResult result2 =
                checkout("O002", 2500, vip, sms);

        CheckoutResult result3 =
                checkout("O003", 3000, discount, console);

        CheckoutResult result4 =
                checkout("O004", 1800, vip, email);

        CheckoutResult result5 =
                checkout("O005", 2200, discount, sms);

        CheckoutResult result6 =
                checkout("O006", 1000, original, console);

        System.out.println();
        result1.printResult();

        System.out.println();
        result2.printResult();

        System.out.println();
        result3.printResult();

        System.out.println();
        result4.printResult();

        System.out.println();
        result5.printResult();

        System.out.println();
        result6.printResult();
    }
}