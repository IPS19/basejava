package com.urise.webapp;

public class DeadLock {
    public static void main(String[] args) {
        final Object resource1 = "resource1";
        final Object resource2 = "resource2";


        Thread t1 = new Thread() {
            public void run() {
                deadLock(resource1, resource2);
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                deadLock(resource2, resource1);
            }
        };

        t1.start();
        t2.start();
    }

    static void deadLock(Object resourceOne, Object resourceTwo) {
        synchronized (resourceOne) {
            System.out.println(Thread.currentThread().getName() + " locked  " + resourceOne);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (resourceTwo) {
                System.out.println(Thread.currentThread().getName() + " locked  " + resourceOne);
            }
        }
    }
}