package core.playground.Threads;

import java.lang.*;


class childThread extends Thread{

    public void run(){
        for(int i=0; i<5; i++){
            System.out.println("In child thread, with name" + Thread.currentThread().getName() );
        }

    }
}
public class ThreadYield {

    public static void main(String args[]){
        childThread childT = new childThread(); //thread is in NEW state here, code is yet to run and hasn't started to execute
        System.out.println(childT.getState());

        childT.start();
        System.out.println(childT.getState());
        childT.setPriority(8);   //setting high priority to notify scheduler
        System.out.println(childT.getPriority());
         /*
             after start, thread is in RUNNABLE. At this stage, thread is running or ready to run at any instant of time and this
            is responsibility of thread scheduler to give this thread time to run.
         */

        System.out.println("Main thread starts, pausing it");
        Thread.yield();
        for(int i=0; i<50000; i++){
                //added very minute delay to prevent CPU to execute next line
        }
        System.out.println("Again got control of main thread");
    }

}
