package com.urise.webapp;

public class DeadLock {
    public static void main(String[] args) {
        final Object resource1 = "resource1";
        final Object resource2 = "resource2";
        Thread t1 = new Thread() {
            public void run() {
                synchronized (resource1) {
                    System.out.println("Thread t1: locked resource 1");
                    try {
                        sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (resource2) {
                        System.out.println("Thread t1: locked resource 2");
                    }
                }
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                synchronized (resource2) {
                    System.out.println("Thread t2: locked resource 2");
                    try {
                        sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (resource1) {
                        System.out.println("Thread t2: locked resource 1");
                    }
                }
            }
        };

        t1.start();
        t2.start();
    }
}

