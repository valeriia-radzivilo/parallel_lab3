package features.task1_modifications.banks;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;

public class BankAtomicOperations extends ABank {
    public static final int NTEST = 10000;
    private final AtomicIntegerArray accounts;
    private final AtomicLong ntransacts = new AtomicLong(0);

    public BankAtomicOperations(int n, int initialBalance) {
        accounts = new AtomicIntegerArray(new int[n]);
        for (int i = 0; i < accounts.length(); i++) accounts.set(i, initialBalance);
    }

    public void transfer(int from, int to, int amount) {
        if (accounts.get(from) < amount) return;
        accounts.addAndGet(from, -amount);
        accounts.addAndGet(to, amount);
        ntransacts.incrementAndGet();
        if (ntransacts.get() % NTEST == 0) test();
    }


    public void test() {
        int sum = 0;
        for (int i = 0; i < accounts.length(); i++) {
            sum += accounts.get(i);
        }
        System.out.println("Transactions:" + ntransacts + " Sum: " + sum);

    }

    public int size() {
        return accounts.length();
    }
}