package com.example.demo;

public class ReadThead extends Thread {
    private int threadid;
    private ReadString reads;
    private  Object lock;
    private final int Count=3;
    public static volatile int flag=0;

    public ReadThead(int threadid, ReadString reads, Object lock) {
        this.threadid = threadid;
        this.reads = reads;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true){
            synchronized (lock){
                if ((flag%Count)==threadid){
                    reads.read (threadid, flag);
                    flag++;
                    lock.notifyAll ();
                }else {
                    try {
                        lock.wait ();
                    } catch (InterruptedException e) {
                        e.printStackTrace ();
                    }
                }
            }
        }
    }
}
