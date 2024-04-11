package features.task1_modifications.banks;

public class BankWait extends ABank {
    public static final int NTEST = 10000;
    private final int[] accounts;
    private long ntransacts = 0;

    public BankWait(int n, int initialBalance) {
        accounts = new int[n];
        int i;
        for (i = 0; i < accounts.length; i++)
            accounts[i] = initialBalance;
        ntransacts = 0;
    }

    public void transfer(int from, int to, int amount) {
        try {
            synchronized (this) {
                while (accounts[from] < amount) {
                    wait();
                }
                accounts[from] -= amount;
                accounts[to] += amount;
                ntransacts++;
                if (ntransacts % NTEST == 0) test();
                notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test() {
        int sum = 0;
        for (int i = 0; i < accounts.length; i++)
            sum += accounts[i];
        System.out.println("Transactions:" + ntransacts
                + " Sum: " + sum);
    }

    public int size() {
        return accounts.length;
    }

}