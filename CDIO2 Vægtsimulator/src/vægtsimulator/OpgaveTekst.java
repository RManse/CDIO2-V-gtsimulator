package vægtsimulator;
public class OpgaveTekst {}

//Opgave ”Vægtsimulator med Konsol” 
//2016-02-29 

//02324 Videregående programmering, CDIO del2 F2016


//I skal udvikle en vægtsimulator med konsol-brugerinterface(Det sorte dos vindue
//i kommandofortolekeren). Programmet skal simulere en Mettler BBK vægt.

//Vedlagt er et programskelet for vægtsimulatoren, der kan modtage en ordre fra
//f.eks. telnet, hyperterminal eller det program, som I selv har skrevet i 02325.



//Ønsker til implementeringen

//1. 
//Vi har udvalgt 6 kommandoer, som vi ønsker implementeret, disse findes i filen
//(SISC_ Protokol.xlsx). Der findes mange andre kommandoer til vægten.
//Disse ønskes ikke implementeret og bør ikke anvendes i det endelige CDIO projekt.
//Herudover er implementeret kommandoerne  "B" og "Q". Disse findes ikke på den fysiske vægt.
//“B 1.123 kg” ændrer vægtens brutto-belastning og Q afslutter simulatoren. 

//2.
//Simulatoren skal lytte på port 8000. Ved opstart fra kommandolinjen skal
//denne default-værdi kunne overskrives med et vilkårligt port-nummer. 

//3.
//Tilføj funktionalitet så man på vægt-simulatoren kan taste tara (nulstille vægtens visning).

//4.
//Tilføj funktionalitet så man kan ændre (brutto-) belastning på vægten.
//Dette svarer til vægten af det emne, der er placeret på vægten.

//5.
//Implementér mulighed for at indtaste svar efter, at vægten har modtaget ”RM20 8” ordren. 

//6. 
//Overvej hvordan man sikrer, at alle resourcer lukker korrekt ned, når programmet afsluttes.

//7.
//Afprøv programmet fra konsollen. (Vi får brug for dette i til afprøvning i 3 ugers perioden.)




//En tilsvarende funktionalitet kunne opnås ved at implementere systemet med
//en GUI svarende til den ovenfor viste. Dette er dog ikke formålet med denne opgave.

//Den rigtige fysiske vægt vil være tilstede i lab. i øvelsestimerne. En oversigt
//over vægtens kommandoer kan findes i dokumentet SISC_CMD.gSlides, og er en mere
//præcis beskrivelse, hvor alle karakterer og skilletegn kan ses, disse findes i SISC_Protokol.xlsx
//Husk at i tvivlstilfælde, er det den fysiske vægt, som er referencen. Det er den jeres simulator skal efterligne.     



//Aflevering: Forventninger/Procedure:

//Som sædvanlig afleveres et projekt, som er eksporteret fra Eclipse.
//Projektet indeholder rapporten i pdf format. (timeregnskab osv. som I er vant til)
//En væsentlig del af rapporten bør være hvilke kommandoer, som I har implementeret og testet, herunder løsningsovervejelser.
