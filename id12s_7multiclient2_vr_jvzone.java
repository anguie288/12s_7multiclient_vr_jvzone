






import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket; 


public class id12s_7multiclient2_vr_jvzone {
 
                public static void main(String argv[]) throws Exception {
                        
                        String instr;
                        String answer;
 
                        BufferedReader incl =new BufferedReader(new InputStreamReader(System.in));
                        
                        Socket csocket = new Socket("localhost", 6789);
 
                        while (true) {  // fast reading and writing
                            
                            DataOutputStream out =new DataOutputStream(csocket.getOutputStream());
                            BufferedReader in = new BufferedReader(new InputStreamReader(csocket.getInputStream()));
 
                            instr = incl.readLine();
 
                            out.writeBytes(instr + '\n');
 
                            if (instr.equals("exit")) {
                                break;}
 
                            answer = in.readLine();
 
                            System.out.println("FROM SERVER: " + answer);
 
                        }
                        csocket.close();
                }
 }