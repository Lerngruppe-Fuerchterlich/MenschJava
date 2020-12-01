/*------------------------------------------------------ 
  Program:      Mensch Ärgere Dich Nicht
  Class:        Gamefield
 -------------------------------------------------------  
  Date:         24.11.2020
------------------------------------------------------*/
import        org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class Gamefield {
  // Attributes -------------------------------
  // -> own
     
    // Others
    static int i = 0; 
  
    // Gamefield Settings
    GamefieldPrinter print = new GamefieldPrinter();
    Color[] GamefieldArray = new Color[40];
  
    // Player Settings 
    static int numberOfPlayers = 0;
   
    // -> other classes                                                       
    GamefieldPlayerAppearance player_1 = new GamefieldPlayerAppearance    (RED); 
    GamefieldPlayerAppearance player_2 = new GamefieldPlayerAppearance   (BLUE);
    GamefieldPlayerAppearance player_3 = new GamefieldPlayerAppearance (YELLOW); 
    GamefieldPlayerAppearance player_4 = new GamefieldPlayerAppearance  (GREEN);
    
  // Attributes -------------------------------
    
  // Constructor ------------------------------
     // Default-Constructor
     Gamefield(int numOfPlayers){
       AnsiConsole.systemInstall();
       numberOfPlayers = numOfPlayers;
       resetGamefield();
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
      resetGamefield();                       
      player_1.placeOnGamefield (GamefieldArray);  
      player_2.placeOnGamefield (GamefieldArray); 
      player_3.placeOnGamefield (GamefieldArray);
      player_4.placeOnGamefield (GamefieldArray); 
    
      // Start Printing         
      System.out.print("\n ");
      for (int i = 0; i < 56; i++) {System.out.print(print.border_top);}
       
      // ----------------------
      print.genericPrint(player_1.start[0],player_1.start[1],BLACK,BLACK,GamefieldArray[8],GamefieldArray[9],GamefieldArray[10],BLACK,BLACK,player_2.start[0],player_2.start[1]);  
      print.emptyline ();
      print.genericPrint(player_1.start[2],player_1.start[3],BLACK,BLACK,GamefieldArray[7],player_2.target[0],GamefieldArray[11],BLACK,BLACK,player_2.start[2],player_2.start[3]); 
      print.emptyline();
      // -------------------------------
      print.genericPrint(BLACK,BLACK,BLACK,BLACK,GamefieldArray[6],player_2.target[1],GamefieldArray[12],BLACK,BLACK,BLACK,BLACK);   
      print.emptyline();    
      print.genericPrint(BLACK,BLACK,BLACK,BLACK,GamefieldArray[5],player_2.target[2],GamefieldArray[13],BLACK,BLACK,BLACK,BLACK);
      print.emptyline();
      // ----------------------    
      print.genericPrint(GamefieldArray[0],GamefieldArray[1],GamefieldArray[2],GamefieldArray[3],GamefieldArray[4],player_2.target[3],GamefieldArray[14],GamefieldArray[15],GamefieldArray[16],GamefieldArray[17],GamefieldArray[18]);  
      print.emptyline();
      print.genericPrint(GamefieldArray[39],player_1.target[0],player_1.target[1],player_1.target[2],player_1.target[3],BLACK,player_3.target[3],player_3.target[2],player_3.target[1],player_3.target[0],GamefieldArray[19]);
      print.emptyline();      
      print.genericPrint(GamefieldArray[38],GamefieldArray[37],GamefieldArray[36],GamefieldArray[35],GamefieldArray[34],player_4.target[3],GamefieldArray[24],GamefieldArray[23],GamefieldArray[22],GamefieldArray[21],GamefieldArray[20]);   
      print.emptyline();
      // ----------------------    
      print.genericPrint(BLACK,BLACK,BLACK,BLACK,GamefieldArray[33],player_4.target[2],GamefieldArray[25],BLACK,BLACK,BLACK,BLACK); 
      print.emptyline();   
      print.genericPrint(BLACK,BLACK,BLACK,BLACK,GamefieldArray[32],player_4.target[1],GamefieldArray[26],BLACK,BLACK,BLACK,BLACK); 
      print.emptyline();
      // ----------------------  
      print.genericPrint(player_4.start[0],player_4.start[1],BLACK,BLACK,GamefieldArray[31],player_4.target[0],GamefieldArray[27],BLACK,BLACK,player_3.start[0],player_3.start[1]); 
      print.emptyline();        
      print.genericPrint(player_4.start[2],player_4.start[3],BLACK,BLACK,GamefieldArray[30],GamefieldArray[29],GamefieldArray[28],BLACK,BLACK,player_3.start[2],player_3.start[3]); 
    
      System.out.print("\n ");
      for (int i = 0; i < 56; i++) {System.out.print(print.border_bottom);} 
      AnsiConsole.systemUninstall(); 
     }
       
     void resetGamefield () {
      for (int i = 0; i < 4; i++) {
        player_1.start[i]  = WHITE;
        player_2.start[i]  = WHITE;     
        player_3.start[i]  = WHITE;
        player_4.start[i]  = WHITE;
      
        player_1.target[i] = WHITE;
        player_2.target[i] = WHITE;
        player_3.target[i] = WHITE;
        player_4.target[i] = WHITE;
      }
      for (int i = 0; i < 40; i++) {
         GamefieldArray[i] = WHITE;   
      }
    }
  
  // Methods ----------------------------------
  
  
  
}
