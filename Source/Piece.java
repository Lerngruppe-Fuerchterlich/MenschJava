public class Piece {
	// Variablen
	int[] cntPiece = new int[4];

	// Constructor
	Piece(){
		// Setzen der initialen Positionen der Figuren
		cntPiece[0] = -1;
		cntPiece[1] = -1;
		cntPiece[2] = -1;
		cntPiece[3] = -1;
	}

	// Setze Position der bewegten Figur
	void setPiecePosition(int numPiece, int cntDice){
		// Variablen
		int CurPosition;

		CurPosition = cntPiece[numPiece];
		cntPiece[numPiece] = CurPosition + cntDice;
	}

	int getPiecePositiopn(int numPiece){
		return cntPiece[numPiece];		
	}
}
