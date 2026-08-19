class Device {

    public void runDiagnostic() {
        System.out.println("Device diagnostic");
    }
}

class Laptop extends Device {

    @Override
    public void runDiagnostic() {
        System.out.println("Laptop diagnostic");
    }
}

class Printer extends Device {

    @Override
    public void runDiagnostic() {
        System.out.println("Printer diagnostic");
    }

    public void cleanPrintHead() {
        System.out.println("Printer print head cleaned");
    }
}

class Router extends Device {

    @Override
    public void runDiagnostic() {
        System.out.println("Router diagnostic");
    }
}

public class DeviceInspectionSystem {

    public static void main(String[] args) {

        Device[] devices = {
            new Laptop(),
            new Printer(),
            new Router(),
            new Printer()
        };

        for (Device device : devices) {
            device.runDiagnostic();

            if (device instanceof Printer printer) {
                printer.cleanPrintHead();
            }
        }
    }
}