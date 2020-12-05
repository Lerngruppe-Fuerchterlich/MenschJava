/*------------------------------------------------------ 
  Program:      Mensch �rgere Dich Nicht
  Class:        GamefieldPlayerAppearance
 -------------------------------------------------------  
  Date:         24.11.2020
------------------------------------------------------*/

import         com.diogonunes.jcolor.Ansi;
import         com.diogonunes.jcolor.AnsiFormat;
import         com.diogonunes.jcolor.Attribute;
import static  com.diogonunes.jcolor.Ansi.*;
import static  com.diogonunes.jcolor.Attribute.*;
import static  com.diogonunes.jcolor.AnsiFormat.*;

public class GamefieldPlayerAppearance{
  // Attributes -------------------------------
    
  GamefieldColors c = new GamefieldColors();
  
  int i = 0;
  int[]  position  = new int[] {-1,-1,-1,-1};  
  //Color   color    = WHITE;        
  AnsiFormat   color    = c.WHITE;        
  AnsiFormat[] target   = new AnsiFormat[] {c.BG_TARGET,c.BG_TARGET,c.BG_TARGET,c.BG_TARGET}; 
  AnsiFormat[] start    = new AnsiFormat[] {c.BG_START, c.BG_START, c.BG_START, c.BG_START};
  // Attributes -------------------------------
  // Constructor ------------------------------
    GamefieldPlayerAppearance(AnsiFormat cl){
      color = cl;
      for (int i = 0; i < 4; i++) {
        start[i] = color;
      }
    };
  // Constructor ------------------------------
  // Methods ---------------------------------- 
    void setPosition (int [] positionArray) {
      for (i = 0; i < 4 ; i++) {
        // Check if deactivated
        if (position [i] != -42) position[i] = positionArray[i];
      } 
    }
  
    void deactivate () {
        position[0] = -42; 
        position[1] = -42;
        position[2] = -42;
        position[3] = -42;
    }   
  
    void placeOnGamefield (AnsiFormat [] GamefieldArray) {
    // Set Position as Offset - Free Value:
     // 0-39 is field
     // -1 start field
     // 40-43 target field
     for (i = 0; i < 4; i++) {
        if (position[i] >= 0) {                                              
          if (position[i] <=39)      
               GamefieldArray [     position[i]] = color;
          else target         [43 - position[i]] = color;  
        } else { 
          if (position[i] == -42) {   // deactiviated players
                 start [i] = c.BG_START;    
                 target[i] = c.BG_TARGET;
          } else start [i] = color;
        }
    }
  }
  // Methods ----------------------------------
}
