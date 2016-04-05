package vægtsimulator;

import java.io.*; 
import java.net.*;
import java.util.Scanner;

//Den givne klasse til opgaven. Intet virker
//smiley face

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
        listener = new ServerSocket(portdst); // lytteren
            System.out.println("Venter på connection på port " + portdst );
            System.out.println("Indtast eventuel portnummer som 1. argument");
            System.out.println("på kommando linien for andet portnr");
        sock = listener.accept(); // opretter en socket, da ServerSocket snakker med mange klienter
        instream = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        outstream = new DataOutputStream(sock.getOutputStream());
        
        PrintWriter writer = new PrintWriter(outstream); // Så behøver vi ikke skrive cr lf.
        printmenu();
        try{
            while (!(inline = instream.readLine().toUpperCase()).isEmpty()){ //her ventes på input
//            	System.out.println("test 1: "+inline);
//            	writer.println("test 2: "+inline);
            	
            	//In progress
            	if (inline.startsWith("RM")){
            		
            		
//            		RM20 8 "INDTAST NR" "" "&3"
            		
            		//Henter teksten i fra første anførselstegn
            		String rm1 = inline.substring(8);
            		rm1 = rm1.substring(0, rm1.indexOf('"'));
            		//Virker
            		
            		//Henter &1, &2, &3
            		int a = inline.indexOf('&');
            		String rm2 = inline.substring(a);
            		rm2 = rm2.substring(0, rm2.indexOf('"'));
            		//Virker
            		outstream.writeBytes("RM20 B"+"\r\n");
            		
            		Scanner skanner = new Scanner(System.in);
            		System.out.print(rm1+": ");
            		String rm3 = skanner.nextLine();
            		skanner.close();
            		
            		outstream.writeBytes("RM20 A \""+rm3+"\"\r\n");
            		
//            		Putty sekvens:
//            			RM20 8 "TEST" "hej" "&3kg"
//            			RM20 B
//            		*Der indtastes "5555" på vægten*
//            			RM20 A "5555"
            		
//            			RM20 8 "TEST" "" "&1kg"
//            			RM20 B
//            		*Der indtastes "JMD" på vægten*
//            			RM20 A "JMD"
            		
//            			RM20 8 "TEST" "" "&1kg"
//            			RM20 B
//            		*Der trykkes cancel på vægten*
//            			RM20 C
            		
//            			RM20 8 "TEST" "" "&1kg"
//            			RM20 B
//            		*Der trykkes cancel på vægten*
//            			RM20 A ""
            		
//            			RM20 8 "TEST" "" "&2kg"
//            			RM20 B
//            		*Der trykkes "dm" på vægten*
//            			RM20 A "dm"

//			RM20 8 "Her står hvad du ber om" "Her er et gemt preset, den må gerne være tom" "Her står &1, &2, eller &3 og en enhed, fx kg"

            		

            	}
            	
            	//In progress
                else if (inline.startsWith("D")){
                	
                	//Done
                    if (inline.equals("DW"))
                    {
                    	
                    	indtDisp=(inline.substring(1, inline.length()));
                        printmenu();
                        outstream.writeBytes("DW A"+"\r\n");
                    }
                    
                    //Næsten Done
                    else {
                        String s = inline;
                        if (s.substring(2).startsWith("\""))
                        	s = s.substring(s.indexOf('"')+1, s.length());
                        else 
                        	s = s.substring(2, s.length());
                        
                        //SKAL IMPLEMENTERES: Hvis der ikke er " " " i input, skal den stoppe ved s.lenght istedet for at crashe
                        
                        if (s.length() <= 7)
                        	s = s.substring(0, s.indexOf('"'));
                        else
                        	s = s.substring(0, 7);
                        
                        indtDisp = s;
                        printmenu();
                        outstream.writeBytes("D A"+"\r\n");
                    }
                }
            	//Done
                else if (inline.startsWith("T")){
                    outstream.writeBytes("T S      " + (tara) + " kg "+"\r\n");		//HVOR MANGE SPACE?
                    tara=brutto;
                    printmenu();
                }
            	//Done
                else if (inline.startsWith("S")){
                    printmenu();
                    outstream.writeBytes("S S      " + (brutto-tara)+ " kg "  +"\r\n");//HVOR MANGE SPACE?
                }
            	//Done
                else if (inline.startsWith("B")){ //denne ordre findes ikke på en fysisk vægt
                    String temp= inline.substring(2,inline.length());
                    brutto = Double.parseDouble(temp);
                    printmenu();
                    outstream.writeBytes("DB"+"\r\n");
                }
            	//Done
                else if ((inline.startsWith("Q"))){
                    System.out.println("");
                    System.out.println("Program stoppet Q modtaget pa com port");
                    System.in.close();
                    System.out.close();
                    instream.close();
                    outstream.close();
                    System.exit(0);
                }
            	//Done
                else if ((inline.startsWith("P"))) {
                	String p1 = inline.substring(4);
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
