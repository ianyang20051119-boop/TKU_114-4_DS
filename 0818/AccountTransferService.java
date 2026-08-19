class Account {

    private String accountId;
    private String owner;
    private int balance;

    public Account(String accountId, String owner, int balance) {
        this.accountId = accountId;
        this.owner = owner;
        this.balance = Math.max(balance, 0);
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(int amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;
        return true;
    }

    @Override
    public String toString() {
        return "帳戶：" + accountId
                + "，持有人：" + owner
                + "，餘額：" + balance;
    }
}

class TransferService {

    public static boolean transfer(Account source, Account target, int amount) {

        if (source == null || target == null) {
            return false;
        }

        if (source == target) {
            return false;
        }

        if (amount <= 0) {
            return false;
        }

        if (source.getBalance() < amount) {
            return false;
        }

        source.withdraw(amount);
        target.deposit(amount);

        return true;
    }
}

public class AccountTransferService {

    public static void main(String[] args) {

        Account account1 = new Account("A001", "王小明", 1000);
        Account account2 = new Account("A002", "李小華", 500);

        System.out.println("=== 初始狀態 ===");
        System.out.println(account1);
        System.out.println(account2);

        System.out.println("\n=== 成功轉帳 300 ===");
        System.out.println("結果：" +
                TransferService.transfer(account1, account2, 300));
        System.out.println(account1);
        System.out.println(account2);

        System.out.println("\n=== 餘額不足轉帳 2000 ===");
        System.out.println("結果：" +
                TransferService.transfer(account1, account2, 2000));
        System.out.println(account1);
        System.out.println(account2);

        System.out.println("\n=== 同帳戶轉帳 100 ===");
        System.out.println("結果：" +
                TransferService.transfer(account1, account1, 100));
        System.out.println(account1);

        System.out.println("\n=== null 目標轉帳 100 ===");
        System.out.println("結果：" +
                TransferService.transfer(account1, null, 100));
        System.out.println(account1);
    }
}