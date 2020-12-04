/*------------------------------------------------------ 
  Program:      Mensch Aergere Dich Nicht
  Class:        GamefieldPrinter
 -------------------------------------------------------  
  Date:         24.11.2020
------------------------------------------------------*/

import        com.diogonunes.jcolor.Ansi;
import        com.diogonunes.jcolor.AnsiFormat;
import        com.diogonunes.jcolor.Attribute;
import static com.diogonunes.jcolor.Ansi.*;
import static com.diogonunes.jcolor.Attribute.*;
import static com.diogonunes.jcolor.AnsiFormat.*;

public class GamefieldPrinter {
  // Attributes -------------------------------                             
  //Color[] printArray = new Color[11];                           
  AnsiFormat[] printArray_Jcolor = new AnsiFormat[11];
  GamefieldColors c = new GamefieldColors();
  
  String border_right  = " +";  
  String border_left   = " + ";
  String border_top    = "+";
  String border_bottom = "+";

  // Color Definitions
  // -> use like "fig_Jcolor(c.RED);"
  
  // Attributes -------------------------------
  // Constructor ------------------------------
  GamefieldPrinter (){ /* */ };
  // Constructor ------------------------------

  // Methods ----------------------------------
  void setBorderRight  (String appearance) {border_right  = appearance;}
  void setBorderLeft   (String appearance) {border_left   = appearance;}
  void setBorderTop    (String appearance) {border_top    = appearance;}
  void setBorderBottom (String appearance) {border_bottom = appearance;}

  void printBorderTop ()    {for (int i = 0; i < 28; i++) {
    //System.out.print(border_top);
    skipfieldspacing();
  }}
  void printBorderBottom () {for (int i = 0; i < 28; i++) {
    //System.out.print(border_bottom);
    skipfieldspacing();
  }}
  
 
  void genericPrint_Jcolor(AnsiFormat p1j,AnsiFormat p2j,AnsiFormat p3j,AnsiFormat p4j,AnsiFormat p5j,AnsiFormat p6j,AnsiFormat p7j,AnsiFormat p8j,AnsiFormat p9j,AnsiFormat p10j,AnsiFormat p11j) {
    //System.out.print("\n" + border_left);
    System.out.print("\n");
    skipfieldspacing();
    setPrintArr_Jcolor (p1j,p2j,p3j,p4j,p5j,p6j,p7j,p8j,p9j,p10j,p11j);
    printLine_Jcolor(printArray_Jcolor);       
    //System.out.print(border_right);
    skipfieldspacing();
  }

  void printLine_Jcolor (AnsiFormat [] array) {
    for (int i = 0; i < array.length; i++) {
      if (array[i] != c.BG_GAMEFIELD) fig_Jcolor(array[i]);
      else                   skipfieldspacing();
      if (i != array.length-1) spacing();
    }
   }
  
  
      void setPrintArr_Jcolor (AnsiFormat p1j,AnsiFormat p2j,AnsiFormat p3j,AnsiFormat p4j,AnsiFormat p5j,AnsiFormat p6j,AnsiFormat p7j,AnsiFormat p8j,AnsiFormat p9j,AnsiFormat p10j,AnsiFormat p11j) {
        printArray_Jcolor[0]  = p1j;    
        printArray_Jcolor[1]  = p2j;
        printArray_Jcolor[2]  = p3j;
        printArray_Jcolor[3]  = p4j;
        printArray_Jcolor[4]  = p5j;
        printArray_Jcolor[5]  = p6j;
        printArray_Jcolor[6]  = p7j;
        printArray_Jcolor[7]  = p8j;
        printArray_Jcolor[8]  = p9j; 
        printArray_Jcolor[9]  = p10j;
        printArray_Jcolor[10] = p11j;
       }
 
  void fig_Jcolor (AnsiFormat color) 
     {System.out.print(colorize("XX", color));} 

  void spacing ()          {
    //System.out.print("   ");
    System.out.print(colorize("XXX", c.BG_GAMEFIELD));  
  }
  void skipfieldspacing () {
    //System.out.print("  ");
    System.out.print(colorize("XX", c.BG_GAMEFIELD));  
  }
   
  void emptyline_Jcolor () 
      {genericPrint_Jcolor(c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD,c.BG_GAMEFIELD);}
  // Methods ----------------------------------
}
