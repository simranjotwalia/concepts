package core.playground.Threads;//here use case is suppose we have thousands of messages to send, and we have 4 cores in the CPU, then why not send them in parallel

class Sender{
    public void  send(String message){
        System.out.println("Hi..  Sending message " + message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Bye... message sent");
    }
}

class ThreadForSending extends Thread{

    String message;
    Sender sender;
    public ThreadForSending(String message ,Sender lockObj){
        this.message = message;
        this.sender = lockObj;
    }

    public void run(){
        synchronized (sender){ //here the common lock among the threads is sender object.
            sender.send(message);
        }

    }
}
public class Synchronization {
    public static void main(String args[]){
        Sender sender = new Sender();   //Basically this object acts as a lock for me.
        //  just for playing purpose, try creating a new sender  core.playground.Threads.Sender s2 = new core.playground.Threads.Sender(); and pass to t2
        // as lock is not same, synchronized will not work properly.
        ThreadForSending t1 = new ThreadForSending("Hello world !", sender);
        ThreadForSending t2 = new ThreadForSending("Just do it !", sender);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Done with sending messages");
    }
}
