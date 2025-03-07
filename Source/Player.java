/*------------------------------------------------------ 
  Program:      Mensch Ärgere Dich Nicht
  Class:        mainGame
 -------------------------------------------------------  
  Date:         24.11.2020
------------------------------------------------------*/ 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Player {
  // Attributes -------------------------------
  int offset;
  String strPlayerName;
  
  // Constuctor -------------------------------
  Player(){
  }
  // Methods ----------------------------------
  // Setze Spielernamen
  void setPlayerName(int cntPlayer) throws IOException {
    // Abfrage User-Eingabe
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("Spieler " + (cntPlayer + 1) + ": ");
    strPlayerName = br.readLine();
  }
  
  // Lese Spielernamen
  String getPlayerName() {
    return strPlayerName;
  }
  
  // Setzte Spieler-Offset
  void setOffset(int cntPlayer) {
    if (cntPlayer < 3) offset = cntPlayer * 20;
    else offset = (cntPlayer) * 10;
  }
  
  // Lese Spieler-Offset
  int getOffset() {
    return offset;
  }
  
  // Würfeln
  int roll_dice() {
    Random rnd = new Random();      // #### Prüfen, ob korrekt implementiert. Gegebenenfalls im Konstruktor definieren. ####
    int cntEyes = rnd.nextInt(6);   // Zufallszahl zwischen 0 und 5 -> 6 Werte
    cntEyes = cntEyes + 1;      // Augenzahl zwischen 1 und 6.
    return cntEyes;
  }
}
