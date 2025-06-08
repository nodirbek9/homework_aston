package ru.aston.homework.MultiThread;

public class ThreadExample {

    private static final Object lock = new Object();
    private static boolean isThread_1_turn = true;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (!isThread_1_turn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("1");
                    isThread_1_turn = false;
                    lock.notify();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (isThread_1_turn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("2");
                    isThread_1_turn = true;
                    lock.notify();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
