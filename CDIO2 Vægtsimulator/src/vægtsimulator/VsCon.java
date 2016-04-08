package vægtsimulator;

import java.io.*; 
import java.net.*;
import java.util.Scanner;

public class VsCon {
    static ServerSocket listener;
    static double brutto=0;
    static double tara=0;
    static String inline;
    static String indtDisp= "";
    static int portdst = 8000;
    static Socket sock;
    static BufferedReader instream;
    static DataOutputStream outstream;
    static boolean rm20flag = false;
    public static void main(String[] args) throws IOException{
        listener = new ServerSocket(portdst); 
            System.out.println("Venter på connection på port " + portdst );
            System.out.println("Indtast eventuel portnummer som 1. argument");
            System.out.println("på kommando linien for andet portnr");
        sock = listener.accept(); 
        instream = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        outstream = new DataOutputStream(sock.getOutputStream());
        
        printmenu();
        try{
            while (!(inline = instream.readLine().toUpperCase()).isEmpty()){ 
            	if (inline.startsWith("RM")){
            		
            		
            		String rm1 = inline.substring(8);
            		rm1 = rm1.substring(0, rm1.indexOf('"'));
            	
            		int a = inline.indexOf('&');
            		String rm2 = inline.substring(a);
            		rm2 = rm2.substring(0, rm2.indexOf('"'));
            		outstream.writeBytes("RM20 B"+"\r\n");
            		
            		Scanner skanner = new Scanner(System.in);
            		System.out.print(rm1+": ");
            		
            		String rm3 = skanner.nextLine();
            		            		
            		if (rm2.substring(1).startsWith("3")) {
            			try {
            				Integer.parseInt(rm3);
            				outstream.writeBytes("RM20 A \""+rm3+"\"\r\n");
            			} catch (NumberFormatException e){
            				System.out.println("FEJL: Kun tal understøttes");
            				outstream.writeBytes("RM20 C"+"\r\n");
            			}
            		}
            		
            		else 
            			outstream.writeBytes("RM20 A \""+rm3+"\"\r\n");
            		skanner.close();
            	}
            	
                else if (inline.startsWith("D")){
                	
                    if (inline.equals("DW"))
                    {
                    	
                    	indtDisp=(inline.substring(2, inline.length()));
                        printmenu();
                        outstream.writeBytes("DW A"+"\r\n");
                    }
                    
                    else {
                        String s = inline;
                        if (s.substring(2).startsWith("\""))
                        	s = s.substring(s.indexOf('"')+1, s.length());
                        else 
                        	s = s.substring(2, s.length());
                        if (s.length() <= 7)
                        	s = s.substring(0, s.indexOf('"'));
                        else
                        	s = s.substring(0, 7);
                        
                        indtDisp = s;
                        printmenu();
                        outstream.writeBytes("D A"+"\r\n");
                    }
                }

                else if (inline.startsWith("T")){
                    outstream.writeBytes("T S      " + (tara) + " kg "+"\r\n");		
                    tara=brutto;
                    printmenu();
                }
            	
                else if (inline.startsWith("S")){
                    printmenu();
                    outstream.writeBytes("S S      " + (brutto-tara)+ " kg "  +"\r\n");
                }

                else if (inline.startsWith("B")){ 
                    String temp= inline.substring(2,inline.length());
                    brutto = Double.parseDouble(temp);
                    printmenu();
                    outstream.writeBytes("DB"+"\r\n");
                }

                else if ((inline.startsWith("Q"))){
                    System.out.println("");
                    System.out.println("Program stoppet Q modtaget pa com port");
                    System.in.close();
                    System.out.close();
                    instream.close();
                    outstream.close();
                    System.exit(0);
                }

                else if ((inline.startsWith("P"))) {
                	String s = inline;
                    if (s.substring(5).startsWith("\""))
                    	s = s.substring(s.indexOf('"')+1, s.length());
                    else 
                    	s = s.substring(5, s.length());
                    
                    if (s.length() <= 30)
                    	s = s.substring(0, s.indexOf('"'));
                    else
                    	s = s.substring(0, 30);
                    
                    indtDisp = s;
                    printmenu();
                    outstream.writeBytes("P111 A"+"\r\n");
                	
                }
                

                else 
                { 
                    printmenu();
                    outstream.writeBytes("ES"+"\r\n");
                }
            }
        }
        catch (Exception e){
            System.out.println("Exception: "+e.getMessage());
        }
    }
    public static void printmenu(){
        for (int i=0;i<2;i++)
        	System.out.println("                                                 ");
        System.out.println("*************************************************");
        System.out.println("Netto: " + (brutto-tara)+ " kg"                   );
        System.out.println("Instruktionsdisplay: " +  indtDisp    );
        System.out.println("*************************************************");
        System.out.println("                                                 ");
        System.out.println("                                                 ");
        System.out.println("Debug info:                                      ");
        System.out.println("Hooked up to " + sock.getInetAddress()            );
        System.out.println("Brutto: " + (brutto)+ " kg"                       );
        System.out.println("Streng modtaget: "+inline)                         ;
        System.out.println("                                                 ");
        System.out.println("Denne vægt simulator lytter på ordrene           ");
        System.out.println("S, T, D 'TEST', DW, RM20 8 .... , B og Q         ");
        System.out.println("på kommunikationsporten.                         ");
        System.out.println("******")						     ;
        System.out.println("Tast T for tara (svarende til knaptryk på vægt)") ;
        System.out.println("Tast B for ny brutto (svarende til at belastningen på vægt ændres)");
        System.out.println("Tast Q for at afslutte program program");
        System.out.println("Indtast (T/B/Q for knaptryk / brutto ændring / quit)");
        System.out.print  ("Tast her: ");
    }
}
