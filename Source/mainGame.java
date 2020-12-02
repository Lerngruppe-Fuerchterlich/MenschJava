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
    Player Player_1 = null;   // Spieler 1
    Piece  Piece_1  = null;   // Figuren, Spieler 1
    Player Player_2 = null;   // Spieler 2
    Piece  Piece_2  = null;   // Figuren, Spieler 2
    Player Player_3 = null;   // Spieler 3
    Piece  Piece_3  = null;   // Figuren, Spieler 3
    Player Player_4 = null;   // Spieler 4
    Piece  Piece_4  = null;   // Figuren, Spieler 4
    
    // Initialisieren der Spieler
    if (numOfPlayers <= 4 && numOfPlayers >= 2){
      if (numOfPlayers >= 2){
        Player_1 = new Player();
        Player_1.setPlayerName(1);  
        Player_1.setOffset(1);
        Piece_1 = new Piece();

        Player_2 = new Player();
        Player_2.setPlayerName(2);  
        Player_2.setOffset(2);
        Piece_2 = new Piece();
      }
      if (numOfPlayers >= 3){
        Player_3 = new Player();
        Player_3.setPlayerName(3);
        Player_3.setOffset(3);
        Piece_3 = new Piece();
      }
      if(numOfPlayers == 4){
        Player_4 = new Player();
        Player_4.setPlayerName(4);
        Player_4.setOffset(4);
        Piece_4 = new Piece();
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
    int cntDice;                      // Würfelzahl
    boolean InputCheck;               // Prüfen der Spielereingabe
    
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
          System.out.println("\nSpieler '" + curPlayerName + "' ist am Zug!\nWürfeln Sie indem Sie eine beliebige Taste drücken...");
          br.readLine();
          cntDice = Player_1.roll_dice();
          System.out.println("Sie haben eine " + cntDice + " gewürfelt. Aktuell befinden sich Ihre Figuren auf nachfolgenden Positionen:");
          outPiecePositions(Player_1, Piece_1);
          System.out.println("Welche Figur möchten Sie auf dem Spielfeld verrücken?");
          do{
            strContainer = br.readLine();
            InputCheck = checkPiecePosition(strContainer, Piece_1, cntDice);
          }while(InputCheck != true);
          break;
        }
        case 2:{
          curPlayerName = Player_2.getPlayerName();
          System.out.println("\nSpieler '" + curPlayerName + "' ist am Zug!\nWürfeln Sie indem Sie eine beliebige Taste drücken...");
          br.readLine();
          cntDice = Player_2.roll_dice();
          System.out.println("Sie haben eine " + cntDice + " gewürfelt. Aktuell befinden sich Ihre Figuren auf nachfolgenden Positionen:");
          outPiecePositions(Player_2, Piece_2);
          System.out.println("Welche Figur möchten Sie auf dem Spielfeld verrücken?");
          break;
        }
        case 3:{          
          curPlayerName = Player_3.getPlayerName();
          System.out.println("\nSpieler '" + curPlayerName + "' ist am Zug!\nWürfeln Sie indem Sie eine beliebige Taste drücken...");
          br.readLine();
          cntDice = Player_3.roll_dice();
          System.out.println("Sie haben eine " + cntDice + " gewürfelt. Aktuell befinden sich Ihre Figuren auf nachfolgenden Positionen:");
          outPiecePositions(Player_3, Piece_3);
          System.out.println("Welche Figur möchten Sie auf dem Spielfeld verrücken?");
          strContainer = br.readLine();
          break;
        }
        case 4:{
          curPlayerName = Player_4.getPlayerName();
          System.out.println("\nSpieler '" + curPlayerName + "' ist am Zug!\nWürfeln Sie indem Sie eine beliebige Taste drücken...");
          br.readLine();
          cntDice = Player_4.roll_dice();
          System.out.println("Sie haben eine " + cntDice + " gewürfelt. Aktuell befinden sich Ihre Figuren auf nachfolgenden Positionen:");
          outPiecePositions(Player_4, Piece_4);
          System.out.println("Welche Figur möchten Sie auf dem Spielfeld verrücken?");
          strContainer = br.readLine();
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

  // Ausgabe Figurpositionen
  public static void outPiecePositions(Player Player, Piece Piece){
    int[] cntPiece = Piece.getPiecePositions();
    int offset = Player.getOffset();
    String[] strPiecePosition = new String[4];

    for(int i = 0; i <= 3; i++){
      if(cntPiece[i] == -1){
        strPiecePosition[i] = "Basis";
      }
      else if(cntPiece[i] > -1 && cntPiece[i] <= 39){
        cntPiece[i] = cntPiece[i] + offset;
        if (cntPiece[i] > 39){
          cntPiece[i] = cntPiece[i] - 39;
        }
        strPiecePosition[i] = String.valueOf(cntPiece[i]);
      }
    }
    System.out.println(strPiecePosition[0] + " | " + strPiecePosition[1] + " | " + strPiecePosition[2] + " | " + strPiecePosition[3]);
  }

  // Prüfen ob Figur bewegt werden kann
  public static boolean checkPiecePosition(String strPiece, Piece Piece, int cntDice){
    int[] curPiecePosition = Piece.getPiecePositions();
    int cntPiece;

    // Prüfen ob eine korrekte Figur ausgewählt wurde
    if(strPiece != "1" || strPiece !="2" || strPiece !="3" || strPiece != "4"){
      System.out.println("Ihre Eingabe ist nicht korrekt. Bitte wählen Sie eine korrekte Figur.");
      return false;
    }
    else{
      cntPiece = Integer.parseInt(strPiece);
    }

    // Prüfen ob Figur sich noch in der Basis befindet und ob eine 6 gewürfelt wurde
    if(curPiecePosition[cntPiece] == -1 && cntDice != 6){
      System.out.println("Ihre sich in der Basis befindliche Figur kann nicht auf das Spielfeld gerückt werden, da Sie keine '6' gewürfelt haben.");
      return false;
    }
    else if(curPiecePosition[cntPiece] == -1 && cntDice == 6){
      return true;
    }

    // Prüfen ob Figur die Anzahl der gewürfelten Felder gehen kann
    if(curPiecePosition[cntPiece] + cntDice > 43){  // Prüfen, ob gewählte Figur genug freie Schrittplätze besitzt
      System.out.println("Ihre Figur kann nicht auf die Position bewegt werden. Bitte wählen Sie eine andere Figur.");
      return false;
    }
    else if(curPiecePosition[cntPiece] + cntDice <= 43){  // Prüfen, ob die gewählte Position bereits von einer anderen eigenen Figur belegt ist
      //###########################
      //###########################
      //###########################   Als nächstes fertigzustellen!
      //###########################
      //###########################
    }
  }

  // Bewegen der Figuren
  public static void MovePiece(){

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