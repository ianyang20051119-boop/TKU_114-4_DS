import java.util.Arrays;

final class InventorySnapshot {

    private final String warehouseId;
    private final int[] quantities;

    public InventorySnapshot(String warehouseId, int[] quantities) {
        this.warehouseId = warehouseId;

        if (quantities == null) {
            this.quantities = new int[0];
        } else {
            this.quantities = Arrays.copyOf(quantities, quantities.length);
        }
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public int[] getQuantities() {
        return Arrays.copyOf(quantities, quantities.length);
    }

    public int totalQuantity() {
        int total = 0;

        for (int quantity : quantities) {
            total += quantity;
        }

        return total;
    }

    public int outOfStockCount() {
        int count = 0;

        for (int quantity : quantities) {
            if (quantity == 0) {
                count++;
            }
        }

        return count;
    }
}

public class InventorySnapshotPractice {

    public static void main(String[] args) {

        int[] quantities = {5, 0, 3, 0};

        InventorySnapshot snapshot =
                new InventorySnapshot("W001", quantities);

        System.out.println("倉庫編號：" + snapshot.getWarehouseId());
        System.out.println("總數量：" + snapshot.totalQuantity());
        System.out.println("缺貨品項：" + snapshot.outOfStockCount());

        quantities[0] = 100;

        System.out.println("修改原陣列後總數量：" + snapshot.totalQuantity());
    }
}