interface DeliveryMethod {
    double calculateFee(double distance, double amount);

    String getEstimate();
}

class HomeDelivery implements DeliveryMethod {

    @Override
    public double calculateFee(double distance, double amount) {
        if (distance < 0) {
            distance = 0;
        }

        return 100 + distance * 10;
    }

    @Override
    public String getEstimate() {
        return "預估 1-2 天送達";
    }
}

class ConvenienceStoreDelivery implements DeliveryMethod {

    @Override
    public double calculateFee(double distance, double amount) {
        if (amount >= 1000) {
            return 0;
        }

        return 60;
    }

    @Override
    public String getEstimate() {
        return "預估 2-3 天可取貨";
    }
}

class SelfPickup implements DeliveryMethod {

    @Override
    public double calculateFee(double distance, double amount) {
        return 0;
    }

    @Override
    public String getEstimate() {
        return "預估當日即可自取";
    }
}

class OrderService {
    private DeliveryMethod deliveryMethod;

    public OrderService(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public double calculateDeliveryFee(double distance, double amount) {
        return deliveryMethod.calculateFee(distance, amount);
    }

    public String getDeliveryEstimate() {
        return deliveryMethod.getEstimate();
    }
}

public class DeliveryStrategySystem {

    public static void main(String[] args) {

        double distance = 10;
        double amount = 800;

        OrderService order = new OrderService(
            new HomeDelivery()
        );

        System.out.println("宅配");
        System.out.println("運費：" +
            order.calculateDeliveryFee(distance, amount) + " 元");
        System.out.println(order.getDeliveryEstimate());

        order.setDeliveryMethod(new ConvenienceStoreDelivery());

        System.out.println();
        System.out.println("超商取貨");
        System.out.println("運費：" +
            order.calculateDeliveryFee(distance, amount) + " 元");
        System.out.println(order.getDeliveryEstimate());

        order.setDeliveryMethod(new SelfPickup());

        System.out.println();
        System.out.println("自取");
        System.out.println("運費：" +
            order.calculateDeliveryFee(distance, amount) + " 元");
        System.out.println(order.getDeliveryEstimate());
    }
}