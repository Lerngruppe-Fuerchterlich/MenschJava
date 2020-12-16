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
  public void setPiecePosition(int numPiece, int cntDice, int offset){
    // Variablen
    int CurPosition;

    CurPosition = cntPiece[numPiece];
    if (CurPosition == -1){
      cntPiece[numPiece] = offset;                // Aus dem Haus raus -> auf Start Position
    }
    else{
      if(CurPosition + cntDice <= 39){
        cntPiece[numPiece] = CurPosition + cntDice; // Sonst -> Augenzahl gehen
      }
      else{
        cntPiece[numPiece] = CurPosition + cntDice - 40;
      }

    }
  }

  public void setPiecePosition(int numPiece, int cntDice){
    // Variablen
    int CurPosition;

    CurPosition = cntPiece[numPiece];
    if (CurPosition == -1){
      cntPiece[numPiece] = 0;                // Aus dem Haus raus -> auf Start Position
    }
    else{
      cntPiece[numPiece] = CurPosition + cntDice; // Sonst -> Augenzahl gehen
    }
  }

  public void resetPiecePosition(int numPiece){
    int resetPosition = -1;

    cntPiece[numPiece] = resetPosition;
  }
  // Absolute Positionsausgabe
  public int[] getPiecePositions(){
    return cntPiece;
  }
  // Relative Positionsausgabe
  public int[] getPiecePositions(int offset){
    int[] CurPosition = new int[4];

    
    for(int i = 0; i < 4; i++){
      CurPosition[i] = cntPiece[i];
      if(CurPosition[i] != -1 && CurPosition[i] < 40){
        CurPosition[i] = cntPiece[i] + offset;
        if(CurPosition[i] > 39){
          CurPosition[i] = CurPosition[i] - 40;
        }
      }
    }

    return CurPosition;
  }
}
