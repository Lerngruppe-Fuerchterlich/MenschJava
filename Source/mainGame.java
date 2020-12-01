/*------------------------------------------------------ 
  Program:      Mensch Ärgere Dich Nicht
  Class:        mainGame
 -------------------------------------------------------  
  Date:         24.11.2020
------------------------------------------------------*/ 

// Imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Hauptspielverzeichnis
public class mainGame {

  public static void main(String[] args) throws IOException{
    // Attributes -------------------------------
    int [] testArr1 = new int[] {1,3,5,7}; 
    int [] testArr2 = new int[] {-1,-1,42,10};
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   // String Input Reader

    // Methods ----------------------------------
    //Clear Terminal
    clrTerminal();

    // Abfrage der Spieleranzahl, Festlegung der Spielernamen
    System.out.println("Mensch Ärgere Dich Nicht!");

    // Abfrage wie viele Spieler am Spiel teilnehmen (2-4 Spieler)
    int numOfPlayers = numOfPlayers();
    Player Player_1 = null;
    Player Player_2 = null;
    Player Player_3 = null;
    Player Player_4 = null;
    
    // Initialisieren der Spieler
    if (numOfPlayers <= 4 && numOfPlayers >= 2){
      if (numOfPlayers >= 2){
        Player_1 = new Player();
        Player_1.setPlayerName(1);  
        Player_1.setOffset(1);

        Player_2 = new Player();
        Player_2.setPlayerName(2);  
        Player_2.setOffset(2);
      }
      if (numOfPlayers >= 3){
        Player_3 = new Player();
        Player_3.setPlayerName(3);
        Player_3.setOffset(3);
      }
      if(numOfPlayers == 4){
        Player_4 = new Player();
        Player_4.setPlayerName(4);
        Player_4.setOffset(4);
      }
    }
    // Wenn Initialisierung fehltschlägt, beende Spiel und gib Fehlermeldung aus.
    else{      
      System.out.println("Es ist ein unerwarteter Fehler aufgetreten. Das Spiel wird beendet.\n\nDrücken Sie eine beliebige Taste...");
      br.readLine();
    }

    System.out.println("Das Spiel beginnt. Viel Erfolg!");     
    Gamefield gamefield = new Gamefield(numOfPlayers);
    //gamefield.resetGamefield(); // Spielfeldreset, alle Figuren entfernen, alle Felder weiß
    //gamefield.show();           // Anzeigen des Spielfeldes
    //gamefield.setPlayerPosition(1,testArr1);  // Aktualisieren der Figurpositionen Spieler 1
    //gamefield.setPlayerPosition(2,testArr2);  // Aktualisieren der Figurpositionen Spieler 2
    gamefield.show(testArr1, testArr1, testArr2, testArr2); // Anzeigen + aktualisieren des Spielfeldes für alle Spieler
    
    // Initiiere Spielvariablen
    boolean gameFinished = false;     // Bool'sche Variable zur Überprüfung, ob das Spiel beendet ist
    int curPlayer = 0;                // Spielerlaufvariable
    int oldPlayer;                    // Alter Spieler
    String curPlayerName;             // Spielername
    String strContainer = "";         // String Container (Dynamische Benutzereingabe)
    boolean gameRoutineStart = false; // Spielroutine, initialer Start
    
    // Starte Spielroutine
    do{
      // Spielroutine, initialer Start + Wahl nächster Spieler
      if (gameRoutineStart == false){
        curPlayer = 1;
        gameRoutineStart = true;
      }
      else{
        oldPlayer = curPlayer;
        if(oldPlayer == numOfPlayers){
          curPlayer = 1;
        }
        else{
          curPlayer = oldPlayer + 1;
        }
      }

      // Spieler ist am Zug
      switch(curPlayer){
        case 1:{
          curPlayerName = Player_1.getPlayerName();
          //outNewRoundInit(curPlayerName);
          break;
        }
        case 2:{
          curPlayerName = Player_2.getPlayerName();
          break;
        }
        case 3:{          
          curPlayerName = Player_3.getPlayerName();
          break;
        }
        case 4:{
          curPlayerName = Player_4.getPlayerName();
          break;
        }
        default:{
          System.out.println("Es ist ein unerwarteter Fehler aufgetreten. Das Spiel wird beendet.\n\nDrücken Sie eine beliebige Taste...");
          br.readLine();
          return;
        }
      }

    }while (gameFinished != true);
  }
  
  // Abfrage der Spieleranzahl
  public static int numOfPlayers() throws IOException {
    int numPlayers = 0;
    String strContainer = "";
    boolean bCompare = false;
    bCompare = false;
    
    System.out.print("Bitte gebe die Anzahl der Spieler ein (2-4 Spieler): ");
    
    do {
      // Abfrage User-Eingabe
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      System.out.print("Anzahl Spieler: ");
      strContainer = br.readLine();
      
      // Auswerten User-Eingabe
      if(strContainer.equals("2") || strContainer.equals("3") || strContainer.equals("4")) {
        numPlayers = Integer.parseInt(strContainer);
        System.out.println("Das spiel wird mit " + numPlayers + " Spielern gespielt!");
        bCompare = true;
      }
      else {
        System.out.println("Falsche Eingabe, bitte wiederholen Sie Ihre Eingabe. Eingabe: " + strContainer);
      } 
    }while(bCompare != true);
    
    return numPlayers;
  }

  // 
  public static void outNewRoundInit(String inputName){
    System.out.println(" Spieler");
  }

  // Methode zur Bereinigung der Console
  public static void clrTerminal(){
    try{
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
    catch(Exception e){
      System.out.println(e);
    }
  }
}
