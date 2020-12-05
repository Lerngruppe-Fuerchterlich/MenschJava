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
      
        player_1.target[i] = c.RED_T_LIGHT;
        player_2.target[i] = c.BLUE_T_LIGHT;
        player_3.target[i] = c.YELLOW_T_LIGHT;
        player_4.target[i] = c.GREEN_T_LIGHT;
      }
      for (int i = 0; i < 40; i++) {GamefieldArray[i] = c.BG_FIELD;}
    }
  void printGamefield_Jcolor () {
    System.out.print("\n");
    print.printBorderTop();
    // ----------------------
    //print.genericPrint_Jcolor(c.WHITE,c.BLACK,c.GREEN,c.BLUE,c.RED,c.YELLOW,c.BLACK,c.BLACK,c.BLACK,c.BLACK,c.BLACK);  
    print.genericPrint_Jcolor(player_1.start[0],0,player_1.start[1],0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,GamefieldArray[8],8,GamefieldArray[9],9,GamefieldArray[10],10,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,player_2.start[0],0,player_2.start[1],0);  
    print.emptyline_Jcolor ();
    print.genericPrint_Jcolor(player_1.start[2],0,player_1.start[3],0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,GamefieldArray[7],7,player_2.target[0],40,GamefieldArray[11],11,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,player_2.start[2],0,player_2.start[3],0); 
    print.emptyline_Jcolor();
    // -------------------------------
    print.genericPrint_Jcolor(c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,GamefieldArray[6],6,player_2.target[1],41,GamefieldArray[12],12,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0);   
    print.emptyline_Jcolor();    
    print.genericPrint_Jcolor(c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,GamefieldArray[5],5,player_2.target[2],42,GamefieldArray[13],13,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0);
    print.emptyline_Jcolor();
    // ----------------------    
    print.genericPrint_Jcolor(GamefieldArray[0],0,GamefieldArray[1],1,GamefieldArray[2],2,GamefieldArray[3],3,GamefieldArray[4],4,player_2.target[3],43,GamefieldArray[14],14,GamefieldArray[15],15,GamefieldArray[16],16,GamefieldArray[17],17,GamefieldArray[18],18);  
    print.emptyline_Jcolor();
    print.genericPrint_Jcolor(GamefieldArray[39],39,player_1.target[0],40,player_1.target[1],41,player_1.target[2],42,player_1.target[3],43,c.BG_GAMEFIELD,0,player_3.target[3],43,player_3.target[2],42,player_3.target[1],41,player_3.target[0],40,GamefieldArray[19],19);
    print.emptyline_Jcolor();      
    print.genericPrint_Jcolor(GamefieldArray[38],38,GamefieldArray[37],37,GamefieldArray[36],36,GamefieldArray[35],35,GamefieldArray[34],34,player_4.target[3],43,GamefieldArray[24],24,GamefieldArray[23],23,GamefieldArray[22],22,GamefieldArray[21],21,GamefieldArray[20],20);   
    print.emptyline_Jcolor();
    // ----------------------    
    print.genericPrint_Jcolor(c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,GamefieldArray[33],33,player_4.target[2],42,GamefieldArray[25],25,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0); 
    print.emptyline_Jcolor();   
    print.genericPrint_Jcolor(c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,GamefieldArray[32],32,player_4.target[1],41,GamefieldArray[26],26,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0); 
    print.emptyline_Jcolor();
    // ----------------------  
    print.genericPrint_Jcolor(player_4.start[0],0,player_4.start[1],0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,GamefieldArray[31],31,player_4.target[0],40,GamefieldArray[27],27,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,player_3.start[0],0,player_3.start[1],0); 
    print.emptyline_Jcolor();        
    print.genericPrint_Jcolor(player_4.start[2],0,player_4.start[3],0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,GamefieldArray[30],30,GamefieldArray[29],29,GamefieldArray[28],28,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,player_3.start[2],0,player_3.start[3],0);   
    System.out.print("\n");
    print.printBorderBottom(); 
}
  // Methods ----------------------------------
  
}
