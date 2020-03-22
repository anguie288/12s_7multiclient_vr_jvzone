
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException; 



public class id12s_7multiserver_vr_jvzone {
 
        public static void main(String argv[]) throws Exception {
 
                ServerSocket ssocket = new ServerSocket(6789);
 
                int cnum = 0; 
                //String cactual = "id "+cnum;         
 
                while (true) {    // true for connection, or boolean
                
                HandlerReader h = new HandlerReader("id "+cnum);
                Socket cs = ssocket.accept(); 
                MyServerThSwitcher th = new MyServerThSwitcher(h, cs);
 
                Thread t = new Thread(th);
                t.start(); cnum++; 
                }
        }
 }

 
class HandlerReader {
 
    String instr;  String cid;
    BufferedReader insr = new BufferedReader(new InputStreamReader(System.in));

    public HandlerReader(String cid){this.cid =cid;}
    // reading and writing 
    synchronized public boolean readerbundle(Socket cs) {
        try {

                BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
                DataOutputStream out =new DataOutputStream(cs.getOutputStream());

                String cmsg = in.readLine();
          
                if (cmsg == null || cmsg.equals("exit")) {
                    return false;}  // if client exits, close connection

                
                if (cmsg != null) {
                    System.out.println("client "+cid+": "+cmsg);}
                
                    instr = insr.readLine() + "\n";

                    out.writeBytes(instr);

                    return true;

            } catch (SocketException e) {System.out.println("Disconnected");return false;} 
              catch (Exception e) {e.printStackTrace();return false;}
    }
} 
 
class MyServerThSwitcher extends Thread {
 
                HandlerReader h;
                Socket serverthscons; 
 
                public MyServerThSwitcher(HandlerReader h, Socket serverthscons) {
                            this.h = h;
                            this.serverthscons = serverthscons;}
 
                @Override
                public void run() {
 
                while (h.readerbundle(serverthscons)==true) {
                    try {
                        // give chance to other threads, sleep thread
                        Thread.sleep(7000); System.out.println("thread true");
                        } catch (InterruptedException ex) {ex.printStackTrace();}  
                }
 
                try {serverthscons.close();} catch (IOException ex) {ex.printStackTrace(); }
                } 
}
 
