class DigitalWallet {

    private String walletId;
    private String owner;
    private double balance;
    private int transactionCount;

    public DigitalWallet(String walletId, String owner) {
        this.walletId = walletId;
        this.owner = owner;
        this.balance = 0;
        this.transactionCount = 0;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }

        balance += amount;
        transactionCount++;
        return true;
    }

    public boolean pay(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;
        transactionCount++;
        return true;
    }

    public boolean refund(double amount) {
        if (amount <= 0) {
            return false;
        }

        balance += amount;
        transactionCount++;
        return true;
    }

    public double getBalance() {
        return balance;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    @Override
    public String toString() {
        return "錢包編號：" + walletId
                + "，持有人：" + owner
                + "，餘額：" + balance
                + "，交易次數：" + transactionCount;
    }
}

public class DigitalWalletSystem {

    public static void main(String[] args) {

        DigitalWallet wallet =
                new DigitalWallet("W001", "王小明");

        System.out.println("=== 初始狀態 ===");
        System.out.println(wallet);

        System.out.println("\n=== 正常儲值 1000 ===");
        System.out.println("結果：" + wallet.deposit(1000));
        System.out.println(wallet);

        System.out.println("\n=== 正常付款 300 ===");
        System.out.println("結果：" + wallet.pay(300));
        System.out.println(wallet);

        System.out.println("\n=== 餘額不足付款 1000 ===");
        System.out.println("結果：" + wallet.pay(1000));
        System.out.println(wallet);

        System.out.println("\n=== 負數付款 -100 ===");
        System.out.println("結果：" + wallet.pay(-100));
        System.out.println(wallet);

        System.out.println("\n=== 退款 200 ===");
        System.out.println("結果：" + wallet.refund(200));
        System.out.println(wallet);

        System.out.println("\n=== 最終結果 ===");
        System.out.println("餘額：" + wallet.getBalance());
        System.out.println("交易次數：" + wallet.getTransactionCount());
    }
}