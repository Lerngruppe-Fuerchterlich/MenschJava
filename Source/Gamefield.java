/*------------------------------------------------------ 
  Program:      Mensch Ärgere Dich Nicht
  Class:        Gamefield
 -------------------------------------------------------  
  Date:         24.11.2020
------------------------------------------------------*/

import        com.diogonunes.jcolor.Ansi;
import        com.diogonunes.jcolor.AnsiFormat;
import        com.diogonunes.jcolor.Attribute;
import static com.diogonunes.jcolor.Ansi.*;
import static com.diogonunes.jcolor.Attribute.*;
import static com.diogonunes.jcolor.AnsiFormat.*;

public class Gamefield {
  // Attributes -------------------------------
  // -> own

    // Others
    static int i = 0;                         // Counter 
    AnsiFormat[] GamefieldArray = new AnsiFormat[40];   // Gamefield position array
    static int numberOfPlayers = 0;           // Absolute number of players
   
  // -> other classes  
    // Class for generation of beautiful console interface                                
    GamefieldPrinter print             = new GamefieldPrinter();
    GamefieldColors c                  = new GamefieldColors();
    // Class for default player appearance on gamefield                   
    GamefieldPlayerAppearance player_1 = new GamefieldPlayerAppearance    (c.RED); 
    GamefieldPlayerAppearance player_2 = new GamefieldPlayerAppearance   (c.BLUE);
    GamefieldPlayerAppearance player_3 = new GamefieldPlayerAppearance (c.YELLOW); 
    GamefieldPlayerAppearance player_4 = new GamefieldPlayerAppearance  (c.GREEN);
    
  // Attributes -------------------------------
    
  // Constructor ------------------------------
     // Default-Constructor
     Gamefield(int numOfPlayers){
    
       numberOfPlayers = numOfPlayers;
       resetGamefield_Jcolor();
    
       switch (numberOfPlayers) {
         case 2:
            player_2.deactivate();
            player_4.deactivate();
            break;
          case 3:
            player_4.deactivate();
            break;
          default:
            break;
       }
     }; 
  // Constructor ------------------------------
  
  // Methods ----------------------------------     
     void setPlayerPosition (int numberOfPlayer, int[] positionArray) {
       switch (numberOfPlayer) {
          case 1:  player_1.setPosition(positionArray);
                   break;
          case 2:  // If 2 players, start "face to face"            
                   if (numberOfPlayers > 2) player_2.setPosition(positionArray); 
                   else                     player_3.setPosition(positionArray); 
                   break;              
          case 3:  player_3.setPosition(positionArray);   
                   break;
          case 4:  player_4.setPosition(positionArray); 
                   break;
          default: System.out.println("Fehler. Ungültige Spielernummer.");
                   break;
          }    
     }
     void show (int[] p1_posArr, int[] p2_posArr, int[] p3_posArr, int[] p4_posArr) {
          setPlayerPosition(1, p1_posArr);
          setPlayerPosition(2, p2_posArr);
          setPlayerPosition(3, p3_posArr);
          setPlayerPosition(4, p4_posArr);
          show();
       }
     void show () { 
      // Reset
      //resetGamefield(); 
      resetGamefield_Jcolor();      
      // Place all players on the gamefield, using the positions                
      player_1.placeOnGamefield (GamefieldArray);  
      player_2.placeOnGamefield (GamefieldArray); 
      player_3.placeOnGamefield (GamefieldArray);
      player_4.placeOnGamefield (GamefieldArray); 
      // Start Printing        
      printGamefield_Jcolor();
     }
    void resetGamefield_Jcolor () {
      for (int i = 0; i < 4; i++) {
        player_1.start[i]  = c.GRAY;
        player_2.start[i]  = c.GRAY;     
        player_3.start[i]  = c.GRAY;
        player_4.start[i]  = c.GRAY;
      
        player_1.target[i] = c.GRAY;
        player_2.target[i] = c.GRAY;
        player_3.target[i] = c.GRAY;
        player_4.target[i] = c.GRAY;
      }
      for (int i = 0; i < 40; i++) {GamefieldArray[i] = c.WHITE;}
    }
  void printGamefield_Jcolor () {
    System.out.print("\n");
    print.printBorderTop();
    // ----------------------
    //print.genericPrint_Jcolor(c.WHITE,c.BLACK,c.GREEN,c.BLUE,c.RED,c.YELLOW,c.BLACK,c.BLACK,c.BLACK,c.BLACK,c.BLACK);  
    print.genericPrint_Jcolor(player_1.start[0],player_1.start[1],c.BG_GAMEFIELD,c.BG_GAMEFIELD,GamefieldArray[8],GamefieldArray[9],GamefieldArray[10],c.BG_GAMEFIELD,c.BG_GAMEFIELD,player_2.start[0],player_2.start[1]);  
    print.emptyline_Jcolor ();
    print.genericPrint_Jcolor(player_1.start[2],player_1.start[3],c.BG_GAMEFIELD,c.BG_GAMEFIELD,GamefieldArray[7],player_2.target[0],GamefieldArray[11],c.BG_GAMEFIELD,c.BG_GAMEFIELD,player_2.start[2],player_2.start[3]); 
    print.emptyline_Jcolor();
    // -------------------------------
    print.genericPrint_Jcolor(c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD,GamefieldArray[6],player_2.target[1],GamefieldArray[12],c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD);   
    print.emptyline_Jcolor();    
    print.genericPrint_Jcolor(c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD,GamefieldArray[5],player_2.target[2],GamefieldArray[13],c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD);
    print.emptyline_Jcolor();
    // ----------------------    
    print.genericPrint_Jcolor(GamefieldArray[0],GamefieldArray[1],GamefieldArray[2],GamefieldArray[3],GamefieldArray[4],player_2.target[3],GamefieldArray[14],GamefieldArray[15],GamefieldArray[16],GamefieldArray[17],GamefieldArray[18]);  
    print.emptyline_Jcolor();
    print.genericPrint_Jcolor(GamefieldArray[39],player_1.target[0],player_1.target[1],player_1.target[2],player_1.target[3],c.BG_GAMEFIELD,player_3.target[3],player_3.target[2],player_3.target[1],player_3.target[0],GamefieldArray[19]);
    print.emptyline_Jcolor();      
    print.genericPrint_Jcolor(GamefieldArray[38],GamefieldArray[37],GamefieldArray[36],GamefieldArray[35],GamefieldArray[34],player_4.target[3],GamefieldArray[24],GamefieldArray[23],GamefieldArray[22],GamefieldArray[21],GamefieldArray[20]);   
    print.emptyline_Jcolor();
    // ----------------------    
    print.genericPrint_Jcolor(c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD,GamefieldArray[33],player_4.target[2],GamefieldArray[25],c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD); 
    print.emptyline_Jcolor();   
    print.genericPrint_Jcolor(c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD,GamefieldArray[32],player_4.target[1],GamefieldArray[26],c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD); 
    print.emptyline_Jcolor();
    // ----------------------  
    print.genericPrint_Jcolor(player_4.start[0],player_4.start[1],c.BG_GAMEFIELD,c.BG_GAMEFIELD,GamefieldArray[31],player_4.target[0],GamefieldArray[27],c.BG_GAMEFIELD,c.BG_GAMEFIELD,player_3.start[0],player_3.start[1]); 
    print.emptyline_Jcolor();        
    print.genericPrint_Jcolor(player_4.start[2],player_4.start[3],c.BG_GAMEFIELD,c.BG_GAMEFIELD,GamefieldArray[30],GamefieldArray[29],GamefieldArray[28],c.BG_GAMEFIELD,c.BG_GAMEFIELD,player_3.start[2],player_3.start[3]);   
    System.out.print("\n");
    print.printBorderBottom(); 
}
  // Methods ----------------------------------
  
}
