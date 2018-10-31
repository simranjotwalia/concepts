package core.playground.Threads;
import java.util.*;
class  printOdd implements Runnable {
        Object shared;
        String name;
public printOdd(Object shared, String name){
        this.shared = shared;
        this.name = name;
        }
public void run(){
    synchronized (shared){
        for(int i=1;i<=12;i+=2){

            System.out.println(i + " By thread: " + this.name);
            this.shared.notify();
            try{
                this.shared.wait();
            }catch (Exception e){

            }
        }
    }

}
}

class printEven implements Runnable{
    Object shared;
    String name;
    public printEven(Object shared, String name){
        this.shared = shared;
        this.name = name;
    }
    public void run(){
    synchronized (shared){
        for(int i=2;i<=12;i+=2){
            System.out.println(i + " By thread: " + this.name);
            this.shared.notify();
            try{
                this.shared.wait();
            }catch (Exception e){

            }
        }

    }
    }

}
public class PrintEvenOdd{
    public static void main(String args[]){
        Object sharedObj = new Object();
        Thread t1 = new Thread(new printOdd(sharedObj, "Odd printer"));
        Thread t2 = new Thread(new printEven(sharedObj, "Even printer"));

        t1.start();
        t2.start();
    }



}