/*------------------------------------------------------ 
  Program:      Mensch Aergere Dich Nicht
  Class:        GamefieldElement
 -------------------------------------------------------  
  Date:         24.11.2020
------------------------------------------------------*/

import        com.diogonunes.jcolor.Ansi;
import        com.diogonunes.jcolor.AnsiFormat;
import        com.diogonunes.jcolor.Attribute;
import static com.diogonunes.jcolor.Ansi.*;
import static com.diogonunes.jcolor.Attribute.*;
import static com.diogonunes.jcolor.AnsiFormat.*;

public class GamefieldElement {
    
    AnsiFormat fieldColor;
    int        fieldNumber;

    GamefieldElement () {}

    setGamefieldElement (AnsiFormat color) {
        fieldColor = color;
        fieldNumber = 0;
    }
    setGamefieldElement (AnsiFormat color, int number) {
        fieldColor = color;
        fieldNumber = number;
    }

}
