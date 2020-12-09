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
    static int i = 0;                                                  // Counter 
    AnsiFormat[]      GamefieldArray = new AnsiFormat[40];             // Gamefield position array
    int[]             GamefieldNo    = new int[40];                    // Gamefield position array
    //GamefieldElement  element        = new GamefieldElement()[11][11];
    static int        numberOfPlayers  = 0;                            // Absolute number of players
   
  // -> other classes  

    // Class for generation of beautiful console interface                                
    GamefieldPrinter print             = new GamefieldPrinter();
    GamefieldColors c                  = new GamefieldColors();

    // Class for default player appearance on gamefield                   
    GamefieldPlayerAppearance player_1 = new GamefieldPlayerAppearance    (c.RED_T); 
    GamefieldPlayerAppearance player_2 = new GamefieldPlayerAppearance   (c.BLUE_T);
    GamefieldPlayerAppearance player_3 = new GamefieldPlayerAppearance (c.YELLOW_T); 
    GamefieldPlayerAppearance player_4 = new GamefieldPlayerAppearance  (c.GREEN_T);
    
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
    void resetGamefieldNo () { for (int i = 0; i < 40 ; i++) { GamefieldNo [i] = i; }}
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
    resetGamefieldNo();
    resetGamefield_Jcolor();      
    // Place all players on the gamefield, using the positions                
    player_1.placeOnGamefield (GamefieldArray, GamefieldNo); 
    player_2.placeOnGamefield (GamefieldArray, GamefieldNo); 
    player_3.placeOnGamefield (GamefieldArray, GamefieldNo);
    player_4.placeOnGamefield (GamefieldArray, GamefieldNo); 
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
    print.genericPrint_Jcolor(player_1.start[0],0,player_1.start[1],0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,GamefieldArray[8],GamefieldNo[8],GamefieldArray[9],GamefieldNo[9],GamefieldArray[10],GamefieldNo[10],c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,player_2.start[0],0,player_2.start[1],0);  
    print.emptyline_Jcolor ();
    print.genericPrint_Jcolor(player_1.start[2],0,player_1.start[3],0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,GamefieldArray[7],GamefieldNo[7],player_2.target[0],40,GamefieldArray[11],GamefieldNo[11],c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,player_2.start[2],0,player_2.start[3],0); 
    print.emptyline_Jcolor();
    // -------------------------------
    print.genericPrint_Jcolor(c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,GamefieldArray[6],GamefieldNo[6],player_2.target[1],41,GamefieldArray[12],GamefieldNo[12],c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0);   
    print.emptyline_Jcolor();    
    print.genericPrint_Jcolor(c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,GamefieldArray[5],GamefieldNo[5],player_2.target[2],42,GamefieldArray[13],GamefieldNo[13],c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0);
    print.emptyline_Jcolor();
    // ----------------------    
    print.genericPrint_Jcolor(GamefieldArray[0],GamefieldNo[0],GamefieldArray[1],GamefieldNo[1],GamefieldArray[2],GamefieldNo[2],GamefieldArray[3],GamefieldNo[3],GamefieldArray[4],GamefieldNo[4],player_2.target[3],43,GamefieldArray[14],GamefieldNo[14],GamefieldArray[15],GamefieldNo[15],GamefieldArray[16],GamefieldNo[16],GamefieldArray[17],GamefieldNo[17],GamefieldArray[18],GamefieldNo[18]);  
    print.emptyline_Jcolor();
    print.genericPrint_Jcolor(GamefieldArray[39],GamefieldNo[39],player_1.target[0],40,player_1.target[1],41,player_1.target[2],42,player_1.target[3],43,c.BG_GAMEFIELD,0,player_3.target[3],43,player_3.target[2],42,player_3.target[1],41,player_3.target[0],40,GamefieldArray[19],GamefieldNo[19]);
    print.emptyline_Jcolor();      
    print.genericPrint_Jcolor(GamefieldArray[38],GamefieldNo[38],GamefieldArray[37],GamefieldNo[37],GamefieldArray[36],GamefieldNo[36],GamefieldArray[35],GamefieldNo[35],GamefieldArray[34],GamefieldNo[34],player_4.target[3],43,GamefieldArray[24],GamefieldNo[24],GamefieldArray[23],GamefieldNo[23],GamefieldArray[22],GamefieldNo[22],GamefieldArray[21],GamefieldNo[21],GamefieldArray[20],GamefieldNo[20]);   
    print.emptyline_Jcolor();
    // ----------------------    
    print.genericPrint_Jcolor(c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,GamefieldArray[33],GamefieldNo[33],player_4.target[2],42,GamefieldArray[25],GamefieldNo[25],c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0); 
    print.emptyline_Jcolor();   
    print.genericPrint_Jcolor(c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,GamefieldArray[32],GamefieldNo[32],player_4.target[1],41,GamefieldArray[26],GamefieldNo[26],c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0); 
    print.emptyline_Jcolor();
    // ----------------------  
    print.genericPrint_Jcolor(player_4.start[0],0,player_4.start[1],0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,GamefieldArray[31],GamefieldNo[31],player_4.target[0],40,GamefieldArray[27],GamefieldNo[27],c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,player_3.start[0],0,player_3.start[1],0); 
    print.emptyline_Jcolor();        
    print.genericPrint_Jcolor(player_4.start[2],0,player_4.start[3],0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,GamefieldArray[30],GamefieldNo[30],GamefieldArray[29],GamefieldNo[29],GamefieldArray[28],GamefieldNo[28],c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,player_3.start[2],0,player_3.start[3],0);   
    System.out.print("\n");
    print.printBorderBottom(); 

    System.out.print("\n\n\n");
    print.printBorderTop();
    //print.genericPrint(element); 

  }
  // Methods ----------------------------------
  
}
