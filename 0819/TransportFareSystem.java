abstract class Transport {
    protected String routeName;

    public Transport(String routeName) {
        this.routeName = routeName;
    }

    public abstract int calculateFare(int distance);

    public String getRouteName() {
        return routeName;
    }
}

class Bus extends Transport {

    public Bus(String routeName) {
        super(routeName);
    }

    @Override
    public int calculateFare(int distance) {
        return 15 + distance * 2;
    }
}

class Taxi extends Transport {

    public Taxi(String routeName) {
        super(routeName);
    }

    @Override
    public int calculateFare(int distance) {
        return 85 + distance * 10;
    }
}

public class TransportFareSystem {

    public static void main(String[] args) {

        Transport[] transports = {
            new Bus("台北車站－淡水"),
            new Taxi("台北車站－信義"),
            new Bus("板橋－台北車站"),
            new Taxi("桃園－中壢")
        };

        int[] distances = {10, 5, 8, 12};

        for (int i = 0; i < transports.length; i++) {
            int fare = transports[i].calculateFare(distances[i]);

            System.out.println(
                "交通工具：" + transports[i].getClass().getSimpleName()
                + "，路線：" + transports[i].getRouteName()
                + "，距離：" + distances[i] + " 公里"
                + "，票價：" + fare + " 元"
            );
        }
    }
}