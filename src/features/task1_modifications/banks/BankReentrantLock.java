package features.task1_modifications.banks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankReentrantLock extends ABank {
    public static final int NTEST = 10000;
    private final int[] accounts;
    private long ntransacts = 0;
    private Lock bankLock = new ReentrantLock();

    public BankReentrantLock(int n, int initialBalance){
        accounts = new int[n];
        int i;
        for (i = 0; i < accounts.length; i++)
            accounts[i] = initialBalance;
        ntransacts = 0;
    }

    public void transfer(int from, int to, int amount) {
        bankLock.lock();
        try {
            if (accounts[from] < amount) return;
            accounts[from] -= amount;
            accounts[to] += amount;
            ntransacts++;
            if (ntransacts % NTEST == 0) test();
        } finally {
            bankLock.unlock();
        }
    }

    public void test(){
        int sum = 0;
        for (int account : accounts) sum += account;
        System.out.println("Transactions:" + ntransacts
                + " Sum: " + sum);
    }
    public int size(){
        return accounts.length;
    }
}