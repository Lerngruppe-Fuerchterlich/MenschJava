/*------------------------------------------------------ 
  Program:      Mensch Ärgere Dich Nicht
  Class:        Gamefield
 -------------------------------------------------------  
  Date:         24.11.2020
------------------------------------------------------*/
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class Gamefield {
  // Attributes -------------------------------
  // -> own
     
    // Others
    static int i = 0;
    Color[] printArray = new Color[11]; 
  
    // Gamefield Settings
    Color[] GamefieldArray = new Color[40];
    String border_right  = " +";  
    String border_left   = " + ";
    String border_top    = "+";
    String border_bottom = "+";
  
    // Player Settings 
    static int numberOfPlayers = 0;
    // Top left  player
    static int[]   Player_1_Position = new int[] {-1,-1,-1,-1};  
           Color   Player_1_Color    = RED;        
           Color[] Player_1_Target   = new Color[] {WHITE,WHITE,WHITE,WHITE}; 
           Color[] Player_1_Start    = new Color[] {WHITE,WHITE,WHITE,WHITE};
    // Top right player
    static int[]   Player_2_Position = new int[] {-1,-1,-1,-1};  
           Color   Player_2_Color    = BLUE;             
           Color[] Player_2_Target   = new Color[] {WHITE,WHITE,WHITE,WHITE}; 
           Color[] Player_2_Start    = new Color[] {WHITE,WHITE,WHITE,WHITE};
    // Bottom right player     
    static int[]   Player_3_Position = new int[] {-1,-1,-1,-1}; 
           Color   Player_3_Color    = YELLOW;            
           Color[] Player_3_Target   = new Color[] {WHITE,WHITE,WHITE,WHITE};
           Color[] Player_3_Start    = new Color[] {WHITE,WHITE,WHITE,WHITE};
    // Bottom left player
    static int[]   Player_4_Position = new int[] {-1,-1,-1,-1};   
           Color   Player_4_Color    = GREEN;              
           Color[] Player_4_Target   = new Color[] {WHITE,WHITE,WHITE,WHITE}; 
           Color[] Player_4_Start    = new Color[] {WHITE,WHITE,WHITE,WHITE};
  
    // -> other classes
    
  // Attributes -------------------------------
    
  // Constructor ------------------------------
     // Default-Constructor
     Gamefield(int numOfPlayers){
       AnsiConsole.systemInstall();
       numberOfPlayers = numOfPlayers;
       resetGamefield();
       switch (numberOfPlayers) {
        case 2: 
          for (i=0;i<4;i++) {
            Player_1_Start[i] = Player_1_Color;
            Player_3_Start[i] = Player_3_Color;
          }
          break;
        case 3: 
          for (i=0;i<4;i++) {
            Player_1_Start[i] = Player_1_Color;
            Player_2_Start[i] = Player_2_Color;
            Player_3_Start[i] = Player_3_Color;
          }
          break;
        case 4: 
          for (i=0;i<4;i++) {
            Player_1_Start[i] = Player_1_Color;
            Player_2_Start[i] = Player_2_Color;
            Player_3_Start[i] = Player_3_Color; 
            Player_4_Start[i] = Player_4_Color;
          }
          break;
        default:
          System.out.println("Fehler");
          break;
        }
     };
  
  // Constructor ------------------------------
  
  // Methods ----------------------------------
  
  
     void setPlayerPosition (int numberOfPlayer, int[] positionArray) {
     
        switch (numberOfPlayer) {
                         
          case 1: 
            Player_1_Position = positionArray;
            break;
          case 2:
            // If 2 players, start "face to face" 
            if (numberOfPlayers > 2) Player_2_Position = positionArray; 
            else                     Player_3_Position = positionArray;
            break;
          case 3:      
            Player_3_Position = positionArray;
            break;
          case 4:     
            Player_4_Position = positionArray;
            break;
          default: 
            System.out.println("Fehler. Ungültige Spielernummer.");
            break;
          }        
     }
     void show () {          
     // Set Position as Offset - Free Value:
     // 0-39 is field
     // -1 start field
     // 40-43 target field
     
      // Reset
      resetGamefield();
     for (i = 0; i < 4; i++) {
            
        if (Player_1_Position[i] >= 0) {
          if (Player_1_Position[i] <=39)
               GamefieldArray [     Player_1_Position[i]] = Player_1_Color;
          else Player_1_Target[43 - Player_1_Position[i]] = Player_1_Color;  
        } else Player_1_Start[i]                          = Player_1_Color; 
      
        if (Player_2_Position[i] >= 0) { 
          if (Player_2_Position[i] <=39)
               GamefieldArray [     Player_2_Position[i]] = Player_2_Color;
          else Player_2_Target[43 - Player_2_Position[i]] = Player_2_Color;          
        } else Player_2_Start[i]                          = Player_2_Color; 
      
        if (Player_3_Position[i] >= 0) {                            
          if (Player_3_Position[i] <=39)
               GamefieldArray [     Player_3_Position[i]] = Player_3_Color; 
          else Player_3_Target[43 - Player_3_Position[i]] = Player_3_Color;    
        } else Player_3_Start[i]                          = Player_3_Color; 
      
        if (Player_4_Position[i] >= 0) {
          if (Player_4_Position[i] <=39)
               GamefieldArray [     Player_4_Position[i]] = Player_4_Color; 
          else Player_4_Target[43 - Player_4_Position[i]] = Player_4_Color;  
        } else Player_4_Start[i]                          = Player_4_Color;   
      }
             
      System.out.print("\n ");
      for (int i = 0; i < 56; i++) {System.out.print(border_top);}
    
      // TO-DO
      // - read in array to get figures position
      // - map array values to the grid below:
    
      // ----------------------
      genericPrint(Player_1_Start[0],Player_1_Start[1],BLACK,BLACK,GamefieldArray[8],GamefieldArray[9],GamefieldArray[10],BLACK,BLACK,Player_2_Start[0],Player_2_Start[1]);  
      emptyline ();
      genericPrint(Player_1_Start[2],Player_1_Start[3],BLACK,BLACK,GamefieldArray[7],Player_2_Target[0],GamefieldArray[11],BLACK,BLACK,Player_2_Start[2],Player_2_Start[3]); 
      emptyline();
      // -------------------------------
      genericPrint(BLACK,BLACK,BLACK,BLACK,GamefieldArray[6],Player_2_Target[1],GamefieldArray[12],BLACK,BLACK,BLACK,BLACK);   
      emptyline();    
      genericPrint(BLACK,BLACK,BLACK,BLACK,GamefieldArray[5],Player_2_Target[2],GamefieldArray[13],BLACK,BLACK,BLACK,BLACK);
      emptyline();
      // ----------------------    
      genericPrint(GamefieldArray[0],GamefieldArray[1],GamefieldArray[2],GamefieldArray[3],GamefieldArray[4],Player_2_Target[3],GamefieldArray[14],GamefieldArray[15],GamefieldArray[16],GamefieldArray[17],GamefieldArray[18]);  
      emptyline();
      genericPrint(GamefieldArray[39],Player_1_Target[0],Player_1_Target[1],Player_1_Target[2],Player_1_Target[3],BLACK,Player_3_Target[3],Player_3_Target[2],Player_3_Target[1],Player_3_Target[0],GamefieldArray[19]);
      emptyline();      
      genericPrint(GamefieldArray[38],GamefieldArray[37],GamefieldArray[36],GamefieldArray[35],GamefieldArray[34],Player_4_Target[3],GamefieldArray[24],GamefieldArray[23],GamefieldArray[22],GamefieldArray[21],GamefieldArray[20]);   
      emptyline();
      // ----------------------    
      genericPrint(BLACK,BLACK,BLACK,BLACK,GamefieldArray[33],Player_4_Target[2],GamefieldArray[25],BLACK,BLACK,BLACK,BLACK); 
      emptyline();   
      genericPrint(BLACK,BLACK,BLACK,BLACK,GamefieldArray[32],Player_4_Target[1],GamefieldArray[26],BLACK,BLACK,BLACK,BLACK); 
      emptyline();
      // ----------------------  
      genericPrint(Player_4_Start[0],Player_4_Start[1],BLACK,BLACK,GamefieldArray[31],Player_4_Target[0],GamefieldArray[27],BLACK,BLACK,Player_3_Start[0],Player_3_Start[1]); 
      emptyline();        
      genericPrint(Player_4_Start[2],Player_4_Start[3],BLACK,BLACK,GamefieldArray[30],GamefieldArray[29],GamefieldArray[28],BLACK,BLACK,Player_3_Start[2],Player_3_Start[3]); 
    
      System.out.print("\n ");
      for (int i = 0; i < 56; i++) {System.out.print(border_bottom);} 
      AnsiConsole.systemUninstall(); 
     }
  
     void genericPrint(Color p1,Color p2,Color p3,Color p4,Color p5,Color p6,Color p7,Color p8,Color p9,Color p10,Color p11) {
         System.out.print("\n" + border_left);
         setPrintArr (p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11);
         printLine (printArray);       
         System.out.print(border_right);
     }
     void setPrintArr (Color p1,Color p2,Color p3,Color p4,Color p5,Color p6,Color p7,Color p8,Color p9,Color p10,Color p11) {
       printArray[0]  = p1;    
       printArray[1]  = p2;
       printArray[2]  = p3;
       printArray[3]  = p4;
       printArray[4]  = p5;
       printArray[5]  = p6;
       printArray[6]  = p7;
       printArray[7]  = p8;
       printArray[8]  = p9; 
       printArray[9]  = p10;
       printArray[10] = p11;
      }
     void printLine (Color [] array) {
        for (int i = 0; i < array.length; i++) {
          if (array[i] != BLACK) fig(array[i]);
          else                   skipfieldspacing();
          if (i != array.length-1) spacing();
        }
     }
     
     void fig (Color color) 
          {System.out.print(ansi().fg(color).bg(color).a("XX").reset());}
     
     void spacing ()          {System.out.print("   ");}
     void skipfieldspacing () {System.out.print("  ");}
     
    void emptyline ()
         {genericPrint(BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK);}        
  
     void resetGamefield () {
      for (int i = 0; i < 4; i++) {
        Player_1_Start[i]  = WHITE;
        Player_2_Start[i]  = WHITE;     
        Player_3_Start[i]  = WHITE;
        Player_4_Start[i]  = WHITE;
      
        Player_1_Target[i] = WHITE;
        Player_2_Target[i] = WHITE;
        Player_3_Target[i] = WHITE;
        Player_4_Target[i] = WHITE;
      }
      for (int i = 0; i < 40; i++) {
         GamefieldArray[i] = WHITE;   
      }
    }
  
  // Methods ----------------------------------
  
  
  
}
