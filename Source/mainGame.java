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
import java.lang.invoke.StringConcatFactory;

// Hauptspielverzeichnis
public class mainGame {

  // Attributes -------------------------------


  // Methods ----------------------------------

  public static void main(String[] args) throws IOException{

    BufferedReader br     = new BufferedReader(new InputStreamReader(System.in));   // String Input Reader
    Player[]       Player = new Player[4];  // Spieler
    Piece[]        Piece  = new Piece[4];   // Figuren

    // Initiiere Spielvariablen
    boolean   gameFinished = false;                           // Bool'sche Variable zur Überprüfung, ob das Spiel beendet ist
    int       curPlayer = 0;                                  // Spielerlaufvariable
    int       oldPlayer;                                      // Alter Spieler
    String    curPlayerName;                                  // Spielername
    String    strContainer = "";                              // String Container (Dynamische Benutzereingabe)
    boolean   gameRoutineStart = false;                       // Spielroutine, initialer Start
    int       cntDice;                                        // Würfelzahl
    boolean   InputCheck;                                     // Prüfen der Spielereingabe
    boolean[] InitRollDice = {true, true, true, true};        // Initiales Würfeln (3 Versuche wenn keine Spieler im Feld)
    int       curPiece;

    
    clrTerminal(); //Clear Terminal
    System.out.println("Mensch Ärgere Dich Nicht!");

    // Abfrage der Spieleranzahl, Festlegung der Spielernamen
    // Abfrage wie viele Spieler am Spiel teilnehmen (2-4 Spieler)
    int numOfPlayers = numOfPlayers();

    // Initialisieren der Spieler
    for (int i = 0; i <= numOfPlayers - 1; i++){

      Player[i] = new Player();
      Piece[i]  = new Piece();

      Player[i].setOffset(i);
      Player[i].setPlayerName(i);

    }
    // Spielstart
    System.out.println("Das Spiel beginnt. Viel Erfolg!");     
    Gamefield gamefield = new Gamefield(numOfPlayers);
    gamefield.show();           // Anzeigen des Spielfeldes
      
    // Spielroutine ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    do{
      // Spielroutine, initialer Start + Wahl nächster Spieler
      if (gameRoutineStart == false){        
        curPlayer = 0;
        gameRoutineStart = true;
      }
      else{
        oldPlayer = curPlayer;
        if (oldPlayer == numOfPlayers - 1) curPlayer = 0; 
        else                               curPlayer = oldPlayer + 1;
      }
      System.out.println("\n\nAktueller Spieler: " + curPlayer);

      // Spieler ist am Zug
      curPlayerName = Player[curPlayer].getPlayerName();
      System.out.println("\nSpieler '" + curPlayerName + "' ist am Zug!");

      // Hier fehlt die Abfrage, ob alle Figuren in der Basis sind, damit 3 Mal gewürfelt werden kann.
      InitRollDice[curPlayer] = CheckBase(Piece[curPlayer], InitRollDice[curPlayer]);
      //cntDice = RollDice(Player[curPlayer], InitRollDice[curPlayer]);
      cntDice = 6;  // ##### TEST #####

      if (cntDice != 0) {
        //outPiecePositions(Player[curPlayer], Piece[curPlayer]); // Brauchen wir theoretisch nicht mehr
        System.out.println("Wählen Sie eine Figur.");
        
        do { // Prüfen ob eine korrekte Eingabe erfolgt ist
          strContainer = br.readLine();
          curPiece     = Integer.parseInt(strContainer) - 1;
          InputCheck   = checkPiecePosition(curPiece/*strContainer*/, Piece[curPlayer], cntDice, Player[curPlayer]);
        } while (InputCheck != true);
        
        updatePiecePositions(curPiece, Piece[curPlayer], cntDice,Player[curPlayer]);    // Aktualisieren der Spielerposition

        //checkEnemies(Piece, numOfPlayers, curPlayer, Player);   // Prüfen ob gegnerische Figuren auf der aktualisierten Position sind

        updatePieceGamefield(curPlayer, curPiece, Piece[curPlayer], Player[curPlayer], gamefield); // calc with offset
        
        clrTerminal();

        gamefield.show();
      }
    }while (gameFinished != true);
    // Spielroutine ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    br.readLine();  // Verhindern, dass cmd-Window geschlossen wird.
  }
  
  // ############################################################################################################################

  // Abfrage der Spieleranzahl
  public static int numOfPlayers() throws IOException {
    int     numPlayers = 0;
    String  strContainer = "";
    boolean bCompare = false;

    //bCompare = false; Kann weg, oder???
    
    System.out.print("Bitte gebe die Anzahl der Spieler ein (2-4 Spieler): ");
    
    do {
      // Abfrage User-Eingabe
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      System.out.print("Anzahl Spieler: ");
      strContainer = br.readLine();
      numPlayers = Integer.parseInt(strContainer);
      // Auswerten User-Eingabe
      if(numPlayers >= 2 && numPlayers <= 4) {
        System.out.println("Das spiel wird mit " + numPlayers + " Spielern gespielt!");
        bCompare = true;
      }
      else {
        System.out.println("Falsche Eingabe, bitte wiederholen Sie Ihre Eingabe. Eingabe: " + strContainer);
      } 
    } while (bCompare != true);
    
    return numPlayers;
  }

  // Spieler Würfeln
  public static int RollDice(Player Player, boolean InitRollDice) throws IOException{
    
    int            cntDice = 0;
    BufferedReader br      = new BufferedReader(new InputStreamReader(System.in));   // String Input Reader

    if (InitRollDice == true){
      System.out.println("Würfeln Sie indem Sie eine beliebige Taste drücken...");
      for(int i = 0; i < 3; i++){
        br.readLine();
        cntDice = Player.roll_dice();
        if(cntDice == 6 && i < 3){
          System.out.println ("Sie haben eine " + cntDice + " gewürfelt. Sie dürfen eine Figur auf das Spielfeld rücken.");
          return cntDice;
        }
        else if (i < 2){
          System.out.println ("Sie haben eine " + cntDice + " gewürfelt. Versuchen Sie es erneut.");
        }
        else if (i < 3){
          System.out.println ("Sie haben eine " + cntDice + " gewürfelt. Sie  haben keine freien Versuche mehr.");
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
      if (curPlayerPieces[i] != -1) return false;
    }
    return true;
  }

  // Ausgabe Figurpositionen
  public static void outPiecePositions(Player Player, Piece Piece){
    int[]    cntPiece         = Piece.getPiecePositions();
    int      offset           = Player.getOffset();
    String[] strPiecePosition = new String[4];
    
    System.out.println("Ihre Figuren befinden sich an nachfolgenden Positionen.");
    for(int i = 0; i <= 3; i++){
      if(cntPiece[i] == -1){
        strPiecePosition[i] = "Basis";
      }
      else if(cntPiece[i] > -1 && cntPiece[i] <= 39){
       // cntPiece[i] = cntPiece[i] + offset; war doppelt. Kann weg. Ok?
        if (cntPiece[i] > 39){
          cntPiece[i] = cntPiece[i] - 39;
        }
        strPiecePosition[i] = String.valueOf(cntPiece[i]);
      }
    }
    System.out.println(strPiecePosition[0] + " | " + strPiecePosition[1] + " | " + strPiecePosition[2] + " | " + strPiecePosition[3]);
  }

  // Prüfen ob Figur bewegt werden kann
  public static boolean checkPiecePosition(int numPiece /*String strPiece*/, Piece Piece, int cntDice, Player Player){
    int[] curPiecePosition = Piece.getPiecePositions();
    //int numPiece;
    int offset = Player.getOffset();

    // Ich habe den String durch einen integer ersetzt und damit ein paar Zeilen code eleminiert. Ok?

    /* Prüfen ob eine korrekte Figur ausgewählt wurde 
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
    }*/
    if (numPiece < 0 || numPiece > 3) {
      System.out.println("Ihre Eingabe ist nicht korrekt. Bitte wählen Sie eine korrekte Figur.");
      return false;
    }
    // Prüfen ob Figur sich noch in der Basis befindet und ob eine 6 gewürfelt wurde
    if(curPiecePosition[numPiece] == -1 && cntDice != 6){
      System.out.println("Ihre sich in der Basis befindliche Figur kann nicht auf das Spielfeld gerückt werden, da Sie keine '6' gewürfelt haben.");
      return false;
    }
    else if(curPiecePosition[numPiece] == -1 && cntDice == 6){
      // Abfrage, ob sich auf dem Startfeld bereits eine Figur befindet
      for(int i = 0; i < 4; i++){
        if (curPiecePosition[i] == offset){
          System.out.println("Sie können keine Neue Figur auf das Feld holen. Sie haben bereits dort eine Figur platziert.");
          return false;
        }
      }
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
  public static void updatePiecePositions(int curPiece, Piece Piece, int cntDice, Player Player){
    Piece.setPiecePosition(curPiece, cntDice, Player.getOffset());
  }

  // Prüfen ob gegnerische Figuren auf der aktualisierten Position sind. ### AKTUELL NICHT IN BENUTZUNG, DA NOCH NICHT FERTIG! ###
  public static void checkEnemies(Piece[] Piece, int numOfPlayers, int curPlayer, Player[] Player){
    int[] curPlayerPiecePositions   = Piece[curPlayer].getPiecePositions();
    int[] enemyPlayerPiecePositions = new int[numOfPlayers];
    int[] offset                    = new int[numOfPlayers];

    for(int i = 0; i < (numOfPlayers- 1); i++){
      offset[i] = Player[i].getOffset();
    }

    // BENÖTIGT UPDATE DA SPIELÜBERLAUF Position > 39 NICHT BERÜCKSICHTIGT!
    for (int i = 0; i < numOfPlayers; i++){ // Routine zum Durchlauf aller Figuren
      if(i != curPlayer){
        enemyPlayerPiecePositions = Piece[i].getPiecePositions(); //  Überschreiben der gegnerischen Position
        for (int k = 0; k < 4; k++){
          for(int l = 0; l < 4; l++){
            if((curPlayerPiecePositions[k] + offset[k]) == (enemyPlayerPiecePositions[l] + offset[l]) && curPlayerPiecePositions[k] != -1 && enemyPlayerPiecePositions[l] != -1){
              Piece[l].resetPiecePosition(l); // Liegt eine gegnerische Figur auf dem Platz erfolgt ein Figurpositionsreset auf Position "-1"
              return; // Interrupt, damit keine weiteren Figurpositionen resettet werden
            }
          }
        }
      }
    }
  }

  // Aktualisieren des Spielfeldes
  public static void updatePieceGamefield(int curPlayer, int curPiece, Piece Piece, Player Player, Gamefield gamefield){
    int[] curPiecePosition = Piece.getPiecePositions();
    int   curPlayerOffset  = Player.getOffset();
    
    //System.out.println(curPiecePosition[0] + " | " + curPiecePosition[1] + " | " + curPiecePosition[2] + " | " + curPiecePosition[3]);

    
    if(curPiecePosition[curPiece] <= 39){  // Spielfeld besitzt 39 Spielfelder (Ohne Zielfelder)
      // War doppelt und kann weg..
      // Anpassen der Spielfeldpositionen auf absolute Werte und nicht relativ zum Spieler
      /*if((curPiecePosition[curPiece] + curPlayerOffset) > 39 ){
        curPiecePosition[curPiece] = (curPiecePosition[curPiece] + curPlayerOffset) - 39;
      }
      else{
        curPiecePosition[curPiece] = curPiecePosition[curPiece] + curPlayerOffset;
      }*/
    }
    else{
      // ##### Zielausgabe fehlt! #####
    }
    
    //System.out.println(curPiecePosition[0] + " | " + curPiecePosition[1] + " | " + curPiecePosition[2] + " | " + curPiecePosition[3]);
    
    gamefield.setPlayerPosition(curPlayer + 1, curPiecePosition);  // Update Spielfeld
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