package features.task2_modifications;

import java.util.Random;



public class Producer implements Runnable {
    private Drop drop;

     int  DATA_SIZE = 100;

    public Producer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        int[] importantInfo = new int[DATA_SIZE];
        Random random = new Random();
        for (int i = 0; i < DATA_SIZE; i++) {
            importantInfo[i] =i+1;
        }

        for (int i = 0;
             i < importantInfo.length;
             i++) {
            drop.put(String.valueOf(importantInfo[i]));
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {}
        }
        drop.put("DONE");
    }
}