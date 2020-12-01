/*------------------------------------------------------ 
  Program:      Mensch Ärgere Dich Nicht
  Class:        GamefieldPlayerAppearance
 -------------------------------------------------------  
  Date:         24.11.2020
------------------------------------------------------*/
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class GamefieldPlayerAppearance {
  // Attributes -------------------------------  
    int i = 0;
    int[]  position  = new int[] {-1,-1,-1,-1};  
    Color   color    = WHITE;        
    Color[] target   = new Color[] {WHITE,WHITE,WHITE,WHITE}; 
    Color[] start    = new Color[] {WHITE,WHITE,WHITE,WHITE};
  // Attributes -------------------------------
  // Constructor ------------------------------
    GamefieldPlayerAppearance(Color cl){
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
  
    void placeOnGamefield (Color [] GamefieldArray) {
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
          if (position[i] == -42) {  
                 start [i] = WHITE;    
                 target[i] = WHITE;
          } else start [i] = color;
        }
    }
  }
  // Methods ----------------------------------
}
