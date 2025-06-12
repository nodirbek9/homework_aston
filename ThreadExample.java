package ru.aston.homework.MultiThread;

public class ThreadExample {

    private static final Object LOCK = new Object();
    private static boolean isThread1Turn = true;

    public static void main(String[] args) {
        Thread thread1 = createThread("1", true);
        Thread thread2 = createThread("2", false);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    private static Thread createThread(String message, boolean isFirstThread) {
        return new Thread(() -> {
            while (true) {
                synchronized (LOCK) {
                    while (isThread1Turn != isFirstThread) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            e.printStackTrace();
                        }
                    }
                    System.out.println(message);
                    isThread1Turn = !isFirstThread;
                    LOCK.notify();
                }
            }
        });
    }
}
