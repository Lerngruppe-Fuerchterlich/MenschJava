/*------------------------------------------------------ 
  Program:      Mensch Ärgere Dich Nicht
  Class:        mainGame
 -------------------------------------------------------  
  Date:         24.11.2020
------------------------------------------------------*/ 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class mainGame {

  // Hauptspielverzeichnis
  public static void main(String[] args) throws IOException{
    // Variables
    
    // Abfrage der Spieleranzahl, Festlegung der Spielernamen
    System.out.println("Mensch Ärgere Dich Nicht!");

    // Abfrage wie viele Spieler am Spiel teilnehmen (2-4 Spieler)
    int numOfPlayers = numOfPlayers();
    
    // Initialisieren der Spieler (Fehler in Spielererstellung, angebliche Mehrfacherstellung gleicher Spieler.
    // Problem muss noch behoben werden.
    switch(numOfPlayers){
      case 4:      
        Player Player_4 = new Player();
        Player_4.setPlayerName(4);
        Player_4.setOffset(4);                                               
      case 3:
        Player Player_3 = new Player();
        Player_3.setPlayerName(3);
        Player_3.setOffset(3);
      case 2:
        Player Player_2 = new Player();
        Player_2.setPlayerName(2);  
        Player_2.setOffset(2);
        Player Player_1 = new Player();
        Player_1.setPlayerName(1);  
        Player_1.setOffset(1);
        break;
      default:
        System.out.println("Spielerinitialisierung fehlgeschlafen. Spiel wird beendet.");
        return;
    }
    
    System.out.println("Das Spiel beginnt. Viel Erfolg!");     
    Gamefield gamefield = new Gamefield(numOfPlayers);
    gamefield.show(1,2,3,4);
    
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
}