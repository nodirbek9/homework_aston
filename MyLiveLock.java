package ru.aston.homework.MultiThread;

public class MyLiveLock {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (lock1) {
                    System.out.println("Поток 1 удерживает lock1, пытается получить lock2");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2) {
                        System.out.println("Поток 1 удерживает lock1 и lock2");
                    }
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (lock2) {
                    System.out.println("Поток 2 удерживает lock2, пытается получить lock1");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock1) {
                        System.out.println("Поток 2 удерживает lock1 и lock2");
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
