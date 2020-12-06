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
    // int [] testArr1 = new int[] {1,3,5,7}; 
    // int [] testArr2 = new int[] {-1,-1,42,10};
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   // String Input Reader
    Player[] Player = new Player[4]; // Spieler
    Piece[] Piece = new Piece[4];   // Figuren

    // Methods ----------------------------------
    //Clear Terminal
    clrTerminal();

    // Abfrage der Spieleranzahl, Festlegung der Spielernamen
    System.out.println("Mensch Ärgere Dich Nicht!");

    // Abfrage wie viele Spieler am Spiel teilnehmen (2-4 Spieler)
    int numOfPlayers = numOfPlayers();

    // Initialisieren der Spieler
    for (int i = 0; i <= numOfPlayers - 1; i++){
      Player[i] = new Player();
      Piece[i] = new Piece();
      Player[i].setOffset(i);
      Player[i].setPlayerName(i);
    }

    System.out.println("Das Spiel beginnt. Viel Erfolg!");     
    Gamefield gamefield = new Gamefield(numOfPlayers);
    //gamefield.resetGamefield(); // Spielfeldreset, alle Figuren entfernen, alle Felder weiß
    gamefield.show();           // Anzeigen des Spielfeldes
    //gamefield.setPlayerPosition(1,testArr1);  // Aktualisieren der Figurpositionen Spieler 1
    //gamefield.setPlayerPosition(2,testArr2);  // Aktualisieren der Figurpositionen Spieler 2
    //gamefield.show(testArr1, testArr1, testArr2, testArr2); // Anzeigen + aktualisieren des Spielfeldes für alle Spieler
    
    // Initiiere Spielvariablen
    boolean gameFinished = false;                           // Bool'sche Variable zur Überprüfung, ob das Spiel beendet ist
    int curPlayer = 0;                                      // Spielerlaufvariable
    int oldPlayer;                                          // Alter Spieler
    String curPlayerName;                                   // Spielername
    String strContainer = "";                               // String Container (Dynamische Benutzereingabe)
    boolean gameRoutineStart = false;                       // Spielroutine, initialer Start
    int cntDice;                                            // Würfelzahl
    boolean InputCheck;                                     // Prüfen der Spielereingabe
    boolean[] InitRollDice = {true, true, true, true};      // Initiales Würfeln (3 Versuche wenn keine Spieler im Feld)
    
    // Starte Spielroutine
    do{
      // Spielroutine, initialer Start + Wahl nächster Spieler
      if (gameRoutineStart == false){
        curPlayer = 0;
        gameRoutineStart = true;
      }
      else{
        oldPlayer = curPlayer;
        if(oldPlayer == numOfPlayers - 1){
          curPlayer = 0;
        }
        else{
          curPlayer = oldPlayer + 1;
        }
      }

      // Spieler ist am Zug
      curPlayerName = Player[curPlayer].getPlayerName();
      System.out.println("\nSpieler '" + curPlayerName + "' ist am Zug!");
      // Hier fehlt die Abfrage, ob alle Figuren in der Basis sind, damit 3 Mal gewürfelt werden kann.
      InitRollDice[curPlayer] = CheckBase(Piece[curPlayer], InitRollDice[curPlayer]);
      //cntDice = RollDice(Player[curPlayer], InitRollDice[curPlayer]);
      cntDice = 6;
      if(cntDice != 0){
        outPiecePositions(Player[curPlayer], Piece[curPlayer]);
        System.out.println("Wählen Sie eine Figur.");
        do{
          strContainer = br.readLine();
          InputCheck = checkPiecePosition(strContainer, Piece[curPlayer], cntDice);
        }while(InputCheck != true);
        updatePiecePositions(strContainer, Piece[curPlayer], cntDice);
        checkEnemies(Piece, numOfPlayers, curPlayer);
        outPiecePositions(Player[curPlayer], Piece[curPlayer]);
        br.readLine();
        gamefield.setPlayerPosition(curPlayer + 1, Piece[curPlayer].getPiecePositions());  // Update Spielfeld
        gamefield.show();
      }
    }while (gameFinished != true);
    br.readLine();  // Verhindern, dass cmd-Window geschlossen wird.
  }
  
  // ############################################################################################################################

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

  // Spieler Würfeln
  public static int RollDice(Player Player, boolean InitRollDice) throws IOException{
    int cntDice = 0;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   // String Input Reader

    if (InitRollDice == true){
      System.out.println("Würfeln Sie indem Sie eine beliebige Taste drücken...");
      for(int i = 0; i < 3; i++){
        br.readLine();
        cntDice = Player.roll_dice();
        if(cntDice == 6 && i < 3){
          System.out.println("Sie haben eine " + cntDice + " gewürfelt. Sie dürfen eine Figur auf das Spielfeld rücken.");
          return cntDice;
        }
        else if (i < 2){
          System.out.println("Sie haben eine " + cntDice + " gewürfelt. Versuchen Sie es erneut.");
        }
        else if (i < 3){
          System.out.println("Sie haben eine " + cntDice + " gewürfelt. Sie  haben keine freien Versuche mehr.");
          return 0;
        }
      }
    }
    else{ 
      System.out.println("Würfeln Sie indem Sie eine beliebige Taste drücken...");
      br.readLine();
      cntDice = Player.roll_dice();
      System.out.println("Sie haben eine " + cntDice + " gewürfelt.");
    }
    return cntDice;
  }

  // Prüfen ob eigene Basis voll ist
  public static boolean CheckBase(Piece Piece, boolean InitRollDice){
    int[] curPlayerPieces = Piece.getPiecePositions();

    for (int i = 0; i <= 3; i++){
      if (curPlayerPieces[i] != -1){
        return false;
      }
    }
    return true;
  }

  // Ausgabe Figurpositionen
  public static void outPiecePositions(Player Player, Piece Piece){
    int[] cntPiece = Piece.getPiecePositions();
    int offset = Player.getOffset();
    String[] strPiecePosition = new String[4];
    
    System.out.println("Ihre Figuren befinden sich an nachfolgenden Positionen.");
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
    int numPiece;

    // Prüfen ob eine korrekte Figur ausgewählt wurde
    if(strPiece.equals("1")){
      numPiece = Integer.parseInt(strPiece) - 1;
    }
    else if(strPiece.equals("2")){
      numPiece = Integer.parseInt(strPiece) - 1;
    }
    else if(strPiece.equals("3")){
      numPiece = Integer.parseInt(strPiece) - 1;
    }
    else if(strPiece.equals("4")){
      numPiece = Integer.parseInt(strPiece) - 1;
    }
    else{
      System.out.println("Ihre Eingabe ist nicht korrekt. Bitte wählen Sie eine korrekte Figur.");
      return false;
    }

    // Prüfen ob Figur sich noch in der Basis befindet und ob eine 6 gewürfelt wurde
    if(curPiecePosition[numPiece] == -1 && cntDice != 6){
      System.out.println("Ihre sich in der Basis befindliche Figur kann nicht auf das Spielfeld gerückt werden, da Sie keine '6' gewürfelt haben.");
      return false;
    }
    else if(curPiecePosition[numPiece] == -1 && cntDice == 6){
      return true;
    }

    // Prüfen ob Figur die Anzahl der gewürfelten Felder gehen kann
    if(curPiecePosition[numPiece] + cntDice > 43){  // Prüfen, ob gewählte Figur genug freie Schrittplätze besitzt
      System.out.println("Ihre Figur kann nicht auf die Position bewegt werden. Bitte wählen Sie eine andere Figur.");
      return false;
    }
    else if(curPiecePosition[numPiece] + cntDice <= 43){  // Prüfen, ob die gewählte Position bereits von einer anderen eigenen Figur belegt ist
      for(int i = 0; i <= 3; i++){
        if(i != numPiece){
          if(curPiecePosition[i] == (curPiecePosition[numPiece] + cntDice)){
            System.out.println("Ihre eigene Figur ist bereits auf der Position. Bitte wählen Sie eine andere Figur.");
            return false;
          }
          else{
            return true;
          }
        }
      }
    }
    System.out.println("Ein unerwarteter Fehler ist aufgetreten.");
    return false;
  }

  // Aktualisierung der Figurposition
  public static void updatePiecePositions(String strPiece, Piece Piece, int cntDice){
    int numPiece = Integer.parseInt(strPiece) - 1;
    Piece.setPiecePosition(numPiece, cntDice);
  }

  // Prüfen ob gegnerische Figuren auf der aktualisierten Position sind.
  public static void checkEnemies(Piece[] Piece, int numOfPlayers, int curPlayer){
    int[] curPlayerPiecePositions = Piece[curPlayer].getPiecePositions();
    int[] enemyPlayerPiecePositions = new int[4];
    
    for (int i = 0; i < numOfPlayers; i++){ // Routine zum Durchlauf aller Figuren
      if(i != curPlayer){
        enemyPlayerPiecePositions = Piece[i].getPiecePositions(); //  Überschreiben der gegnerischen Position
        for (int k = 0; k < 4; k++){
          for(int l = 0; l < 4; l++){
            if(curPlayerPiecePositions[k] == enemyPlayerPiecePositions[l]){
              Piece[l].resetPiecePosition(l); // Liegt eine gegnerische Figur auf dem Platz erfolgt ein Figurpositionsreset auf Position "-1"
              return; // Interrupt, damit keine weiteren Figurpositionen resettet werden
            }
          }
        }
      }
    }
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