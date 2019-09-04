package com.leh.jvm;

/**
 * @Auther: leh
 * @Date: 2019/9/4 14:54
 * @Description:
 * 查看执行线程的快照信息
 *
 * jps
 * jstack 244836
 */
public class DeadLockDemo1 {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();


    /**
     * 不一定会导致死锁，因为你没有做循环，只做一次操作，万一 thread1跑的快，瞬间就做完所有操作了，也不会阻塞thread2.
     */
    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (lock1) {
                try {
                    System.out.println("thread1 start");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock2) {
                    System.out.println("thread1 end");
                }

            }

        }, "thread1").start();


        new Thread(() -> {
            synchronized (lock2) {
                try {
                    System.out.println("thread2 start");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock1) {
                    System.out.println("thread2 end");
                }
            }

        }, "thread2").start();

        System.out.println("------------the end");
    }

    /*

        Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.171-b11 mixed mode):

            "DestroyJavaVM" #13 prio=5 os_prio=0 tid=0x00000000031f3800 nid=0x1de70 waiting on condition [0x0000000000000000]
               java.lang.Thread.State: RUNNABLE

            "thread2" #12 prio=5 os_prio=0 tid=0x000000001e09d800 nid=0x3fb24 waiting for monitor entry [0x000000001eb3e000]
               java.lang.Thread.State: BLOCKED (on object monitor)
                    at com.leh.jvm.DeadLockDemo1.lambda$main$1(DeadLockDemo1.java:47)
                    - waiting to lock <0x000000076b390f08> (a java.lang.Object)
                    - locked <0x000000076b390f18> (a java.lang.Object)
                    at com.leh.jvm.DeadLockDemo1$$Lambda$2/1831932724.run(Unknown Source)
                    at java.lang.Thread.run(Thread.java:748)

            "thread1" #11 prio=5 os_prio=0 tid=0x000000001e099000 nid=0x3c040 waiting for monitor entry [0x000000001ea3f000]
               java.lang.Thread.State: BLOCKED (on object monitor)
                    at com.leh.jvm.DeadLockDemo1.lambda$main$0(DeadLockDemo1.java:29)
                    - waiting to lock <0x000000076b390f18> (a java.lang.Object)
                    - locked <0x000000076b390f08> (a java.lang.Object)
                    at com.leh.jvm.DeadLockDemo1$$Lambda$1/990368553.run(Unknown Source)
                    at java.lang.Thread.run(Thread.java:748)

            "Service Thread" #10 daemon prio=9 os_prio=0 tid=0x000000001de01000 nid=0xd128 runnable [0x0000000000000000]
               java.lang.Thread.State: RUNNABLE

            "C1 CompilerThread2" #9 daemon prio=9 os_prio=2 tid=0x000000001ddf9800 nid=0x3cbcc waiting on condition [0x0000000000000000]
               java.lang.Thread.State: RUNNABLE

            "C2 CompilerThread1" #8 daemon prio=9 os_prio=2 tid=0x000000001dd99000 nid=0x3cf20 waiting on condition [0x0000000000000000]
               java.lang.Thread.State: RUNNABLE

            "C2 CompilerThread0" #7 daemon prio=9 os_prio=2 tid=0x000000001dd95800 nid=0x3e414 waiting on condition [0x0000000000000000]
               java.lang.Thread.State: RUNNABLE

            "Monitor Ctrl-Break" #6 daemon prio=5 os_prio=0 tid=0x000000001dd92000 nid=0x2fb4c runnable [0x000000001e43e000]
               java.lang.Thread.State: RUNNABLE
                    at java.net.SocketInputStream.socketRead0(Native Method)
                    at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
                    at java.net.SocketInputStream.read(SocketInputStream.java:171)
                    at java.net.SocketInputStream.read(SocketInputStream.java:141)
                    at sun.nio.cs.StreamDecoder.readBytes(StreamDecoder.java:284)
                    at sun.nio.cs.StreamDecoder.implRead(StreamDecoder.java:326)
                    at sun.nio.cs.StreamDecoder.read(StreamDecoder.java:178)
                    - locked <0x000000076b4cf0d8> (a java.io.InputStreamReader)
                    at java.io.InputStreamReader.read(InputStreamReader.java:184)
                    at java.io.BufferedReader.fill(BufferedReader.java:161)
                    at java.io.BufferedReader.readLine(BufferedReader.java:324)
                    - locked <0x000000076b4cf0d8> (a java.io.InputStreamReader)
                    at java.io.BufferedReader.readLine(BufferedReader.java:389)
                    at com.intellij.rt.execution.application.AppMainV2$1.run(AppMainV2.java:64)

            "Attach Listener" #5 daemon prio=5 os_prio=2 tid=0x000000001c9e0000 nid=0x2cc50 waiting on condition [0x0000000000000000]
               java.lang.Thread.State: RUNNABLE

            "Signal Dispatcher" #4 daemon prio=9 os_prio=2 tid=0x000000001dd50800 nid=0x27b88 runnable [0x0000000000000000]
               java.lang.Thread.State: RUNNABLE

            "Finalizer" #3 daemon prio=8 os_prio=1 tid=0x00000000032e9000 nid=0x3ef34 in Object.wait() [0x000000001dd3e000]
               java.lang.Thread.State: WAITING (on object monitor)
                    at java.lang.Object.wait(Native Method)
                    - waiting on <0x000000076b208ed0> (a java.lang.ref.ReferenceQueue$Lock)
                    at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:143)
                    - locked <0x000000076b208ed0> (a java.lang.ref.ReferenceQueue$Lock)
                    at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:164)
                    at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:212)

            "Reference Handler" #2 daemon prio=10 os_prio=2 tid=0x00000000032e3000 nid=0x11e08 in Object.wait() [0x000000001dc3f000]
               java.lang.Thread.State: WAITING (on object monitor)
                    at java.lang.Object.wait(Native Method)
                    - waiting on <0x000000076b206bf8> (a java.lang.ref.Reference$Lock)
                    at java.lang.Object.wait(Object.java:502)
                    at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
                    - locked <0x000000076b206bf8> (a java.lang.ref.Reference$Lock)
                    at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)

            "VM Thread" os_prio=2 tid=0x000000001c997000 nid=0x1cf74 runnable

            "GC task thread#0 (ParallelGC)" os_prio=0 tid=0x0000000003208800 nid=0x13e10 runnable

            "GC task thread#1 (ParallelGC)" os_prio=0 tid=0x000000000320a800 nid=0x4524 runnable

            "GC task thread#2 (ParallelGC)" os_prio=0 tid=0x000000000320c000 nid=0x2604 runnable

            "GC task thread#3 (ParallelGC)" os_prio=0 tid=0x000000000320e800 nid=0x5860 runnable

            "VM Periodic Task Thread" os_prio=2 tid=0x000000001de5d800 nid=0x2d1a8 waiting on condition

            JNI global references: 317


            Found one Java-level deadlock:
            =============================
            "thread2":
              waiting to lock monitor 0x000000001c99bb08 (object 0x000000076b390f08, a java.lang.Object),
              which is held by "thread1"
            "thread1":
              waiting to lock monitor 0x000000001c99e398 (object 0x000000076b390f18, a java.lang.Object),
              which is held by "thread2"

            Java stack information for the threads listed above:
            ===================================================
            "thread2":
                    at com.leh.jvm.DeadLockDemo1.lambda$main$1(DeadLockDemo1.java:47)
                    - waiting to lock <0x000000076b390f08> (a java.lang.Object)
                    - locked <0x000000076b390f18> (a java.lang.Object)
                    at com.leh.jvm.DeadLockDemo1$$Lambda$2/1831932724.run(Unknown Source)
                    at java.lang.Thread.run(Thread.java:748)
            "thread1":
                    at com.leh.jvm.DeadLockDemo1.lambda$main$0(DeadLockDemo1.java:29)
                    - waiting to lock <0x000000076b390f18> (a java.lang.Object)
                    - locked <0x000000076b390f08> (a java.lang.Object)
                    at com.leh.jvm.DeadLockDemo1$$Lambda$1/990368553.run(Unknown Source)
                    at java.lang.Thread.run(Thread.java:748)

            Found 1 deadlock.

     */


}
