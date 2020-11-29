/*------------------------------------------------------ 
  Program:      Mensch Ärger Dich Nicht
  Class:        Gamefield
 -------------------------------------------------------  
  Date:         24.11.2020
------------------------------------------------------*/; 
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class Gamefield {
  // Attributes -------------------------------
  // -> own
    static int i = 0;
    Color[] printArray = new Color[11]; 
    static int Player_1_Startfield = 0; 
    static int Player_2_Startfield = 0;
    static int Player_3_Startfield = 0;
    static int Player_4_Startfield = 0;
  
    String border_right  = " +";  
    String border_left   = " + ";
    String border_top    = "+";
    String border_bottom = "+";
    // -> other classes
    
  // Attributes -------------------------------
    
  // Constructor ------------------------------
     // Default-Constructor
     Gamefield(int numOfPlayers){
         AnsiConsole.systemInstall();    
     };
  
  // Constructor ------------------------------
  
  // Methods ----------------------------------
  
     void show (int p1pos, int p2pos, int p3pos, int p4pos) {             
      System.out.print("\n ");
      for (int i = 0; i < 56; i++) {System.out.print(border_top);}
    
      // TO-DO
      // - set offsets (2 player, 3 player, 4 player)
      // - read in array to get figures position
      // - map array values to the grid below:
    
      // ----------------------
      genericPrint(RED,RED,BLACK,BLACK,WHITE,WHITE,WHITE,BLACK,BLACK,BLUE,BLUE);  
      emptyline ();
      genericPrint(RED,RED,BLACK,BLACK,WHITE,BLUE,WHITE,BLACK,BLACK,BLUE,BLUE); 
      emptyline();
      // -------------------------------
      genericPrint(BLACK,BLACK,BLACK,BLACK,WHITE,BLUE,WHITE,BLACK,BLACK,BLACK,BLACK);   
      emptyline();    
      genericPrint(BLACK,BLACK,BLACK,BLACK,WHITE,BLUE,WHITE,BLACK,BLACK,BLACK,BLACK);
      emptyline();
      // ----------------------    
      genericPrint(WHITE,WHITE,WHITE,WHITE,WHITE,BLUE,WHITE,WHITE,WHITE,WHITE,WHITE);  
      emptyline();
      genericPrint(WHITE,RED,RED,RED,RED,BLACK,YELLOW,YELLOW,YELLOW,YELLOW,WHITE);
      emptyline();      
      genericPrint(WHITE,WHITE,WHITE,WHITE,WHITE,GREEN,WHITE,WHITE,WHITE,WHITE,WHITE);   
      emptyline();
      // ----------------------    
      genericPrint(BLACK,BLACK,BLACK,BLACK,WHITE,GREEN,WHITE,BLACK,BLACK,BLACK,BLACK); 
      emptyline();   
      genericPrint(BLACK,BLACK,BLACK,BLACK,WHITE,GREEN,WHITE,BLACK,BLACK,BLACK,BLACK); 
      emptyline();
      // ----------------------  
      genericPrint(GREEN,GREEN,BLACK,BLACK,WHITE,GREEN,WHITE,BLACK,BLACK,YELLOW,YELLOW); 
      emptyline();        
      genericPrint(GREEN,GREEN,BLACK,BLACK,WHITE,WHITE,WHITE,BLACK,BLACK,YELLOW,YELLOW); 
    
      System.out.print("\n ");
      for (int i = 0; i < 56; i++) {System.out.print(border_bottom);} 
      AnsiConsole.systemUninstall(); 
     }
  
     // Overload Method "show"
     void show (int p1pos, int p2pos, int p3pos) {
        show (p1pos, p2pos, p3pos, -42);
     }
     void show (int p1pos, int p2pos) {
        show (p1pos, p2pos, -42, -42);
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
  
  // Methods ----------------------------------
}
