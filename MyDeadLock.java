package ru.aston.homework.MultiThread;

public class MyDeadLock {

    private static final Object LOCK1 = new Object();
    private static final Object LOCK2 = new Object();
    private static final int SLEEP_DURATION = 100;

    public static void main(String[] args) {
        Thread thread1 = createThread("Поток 1", LOCK1, LOCK2);
        Thread thread2 = createThread("Поток 2", LOCK2, LOCK1);

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

        private static Thread createThread(String name, Object firstLock, Object secondLock) {
            return new Thread(() -> {
                synchronized (firstLock) {
                    System.out.println(name + " удерживает " + getLockName(firstLock));
                    try {
                        Thread.sleep(SLEEP_DURATION);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                    synchronized (secondLock) {
                        System.out.println(name + " удерживает " + getLockName(secondLock));
                    }
                }
            });
        }
    private static String getLockName(Object lock) {
        if (lock == LOCK1) return "LOCK1";
        if (lock == LOCK2) return "LOCK2";
        return "неизвестный LOCK";
    }
}
