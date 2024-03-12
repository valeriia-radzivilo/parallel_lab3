package features.task2;

import features.task2.Consumer;
import features.task2.Drop;

public class Main {
    public static void main(String[] args) {
        Drop drop = new Drop();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
        }

}