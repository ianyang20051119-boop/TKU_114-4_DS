class WalletTransaction {

    private int sequence;
    private String type;
    private double amount;
    private String description;

    public WalletTransaction(int sequence, String type, double amount, String description) {
        this.sequence = sequence;
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    public int getSequence() {
        return sequence;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "序號：" + sequence
                + "，類型：" + type
                + "，金額：" + amount
                + "，說明：" + description;
    }
}

class WalletTransactionSystem {

    private String walletId;
    private String owner;
    private double balance;
    private WalletTransaction[] transactions;
    private int transactionCount;

    public WalletTransactionSystem(String walletId, String owner, double balance, int capacity) {
        this.walletId = walletId;
        this.owner = owner;
        this.balance = Math.max(balance, 0);
        this.transactions = new WalletTransaction[Math.max(capacity, 0)];
        this.transactionCount = 0;
    }

    public boolean deposit(double amount) {
        if (amount <= 0 || !canAddTransaction()) {
            return false;
        }

        balance += amount;
        addTransaction("DEPOSIT", amount, "儲值");
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance || !canAddTransaction()) {
            return false;
        }

        balance -= amount;
        addTransaction("WITHDRAW", amount, "付款");
        return true;
    }

    public boolean addTransferRecord(String type, double amount, String description) {
        if (amount <= 0 || !canAddTransaction()) {
            return false;
        }

        addTransaction(type, amount, description);
        return true;
    }

    private boolean canAddTransaction() {
        return transactionCount < transactions.length;
    }

    private void addTransaction(String type, double amount, String description) {
        transactions[transactionCount] =
                new WalletTransaction(
                        transactionCount + 1,
                        type,
                        amount,
                        description
                );
        transactionCount++;
    }

    public WalletTransaction findTransaction(int sequence) {
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getSequence() == sequence) {
                return transactions[i];
            }
        }

        return null;
    }

    public double totalByType(String type) {
        double total = 0;

        if (type == null) {
            return 0;
        }

        for (int i = 0; i < transactionCount; i++) {
            if (type.equals(transactions[i].getType())) {
                total += transactions[i].getAmount();
            }
        }

        return total;
    }

    public double getBalance() {
        return balance;
    }

    public String statement() {
        String result = "錢包編號：" + walletId
                + "\n持有人：" + owner
                + "\n餘額：" + balance
                + "\n交易紀錄：\n";

        for (int i = 0; i < transactionCount; i++) {
            result += transactions[i] + "\n";
        }

        return result;
    }
}

public class WalletHistoryManager {

    public static boolean transferTo(
            WalletTransactionSystem source,
            WalletTransactionSystem target,
            double amount) {

        if (source == null || target == null) {
            return false;
        }

        if (source == target || amount <= 0) {
            return false;
        }

        if (source.getBalance() < amount) {
            return false;
        }

        if (source.findTransaction(source.totalByType("TRANSFER_OUT") > 0 ? 1 : 0) == null) {
            return transferProcess(source, target, amount);
        }

        return transferProcess(source, target, amount);
    }

    private static boolean transferProcess(
            WalletTransactionSystem source,
            WalletTransactionSystem target,
            double amount) {

        if (!hasSpace(source) || !hasSpace(target)) {
            return false;
        }

        if (!source.withdraw(amount)) {
            return false;
        }

        if (!target.deposit(amount)) {
            source.deposit(amount);
            return false;
        }

        return true;
    }

    private static boolean hasSpace(WalletTransactionSystem wallet) {
        return wallet.findTransaction(999999) == null;
    }

    public static void main(String[] args) {

        WalletTransactionSystem wallet1 =
                new WalletTransactionSystem("W001", "王小明", 1000, 5);

        WalletTransactionSystem wallet2 =
                new WalletTransactionSystem("W002", "李小華", 500, 5);

        wallet1.deposit(300);
        wallet1.withdraw(100);

        System.out.println("=== 轉帳 ===");
        System.out.println("結果：" + transferTo(wallet1, wallet2, 400));

        System.out.println("\n=== 查詢交易 ===");
        System.out.println(wallet1.findTransaction(1));

        System.out.println("\n=== 指定類型總額 ===");
        System.out.println("Wallet 1 DEPOSIT：" +
                wallet1.totalByType("DEPOSIT"));

        System.out.println("\n=== Wallet 1 Statement ===");
        System.out.println(wallet1.statement());

        System.out.println("=== Wallet 2 Statement ===");
        System.out.println(wallet2.statement());
    }
}