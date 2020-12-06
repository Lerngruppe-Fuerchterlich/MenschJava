/*------------------------------------------------------ 
  Program:      Mensch Aergere Dich Nicht
  Class:        GamefieldPrinter
 -------------------------------------------------------  
  Date:         24.11.2020
------------------------------------------------------*/

public class Piece {
  // Attributes -------------------------------
  int[] cntPiece = new int[4];

  // Constuctor -------------------------------
  Piece(){
    // Setzen der initialen Positionen der Figuren
    cntPiece[0] = -1;
    cntPiece[1] = -1;
    cntPiece[2] = -1;
    cntPiece[3] = -1;
  }
  // Methods ----------------------------------
  // Setze Position der bewegten Figur
  public void setPiecePosition(int numPiece, int cntDice){
    // Variablen
    int CurPosition;

    CurPosition = cntPiece[numPiece];
    if (CurPosition == -1){
      cntPiece[numPiece] = 0;
    }
    else{
      cntPiece[numPiece] = CurPosition + cntDice;
      System.out.println(CurPosition + " | " + cntDice);
    }
  }

  public void resetPiecePosition(int numPiece){
    int resetPosition = -1;

    cntPiece[numPiece] = resetPosition;
  }

  public int[] getPiecePositions(){
    return cntPiece;
  }
}
