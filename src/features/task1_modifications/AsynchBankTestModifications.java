package features.task1_modifications;

import features.task1_modifications.banks.BankAtomicOperations;

public class AsynchBankTestModifications {
    public static final int NACCOUNTS = 10;
    public static final int INITIAL_BALANCE = 10000;
    public static void main(String[] args) {
//        BankSync b = new BankSync(NACCOUNTS, INITIAL_BALANCE);
//        BankReentrantLock b = new BankReentrantLock(NACCOUNTS, INITIAL_BALANCE);
        BankAtomicOperations b = new BankAtomicOperations(NACCOUNTS, INITIAL_BALANCE);
        int i;
        for (i = 0; i < NACCOUNTS; i++){
            TransferThread t = new TransferThread(b, i,
                    INITIAL_BALANCE);
            t.setPriority(Thread.NORM_PRIORITY + i % 2);
            t.start () ;
        }
    }
}