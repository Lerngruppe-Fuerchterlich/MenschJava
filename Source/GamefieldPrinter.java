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
  int[]        numberArr         = new int[11];
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
  
 
  void genericPrint_Jcolor(AnsiFormat p1j, int n1,AnsiFormat p2j,int n2,AnsiFormat p3j,int n3,AnsiFormat p4j,int n4,AnsiFormat p5j,int n5,AnsiFormat p6j,int n6,AnsiFormat p7j,int n7,AnsiFormat p8j,int n8,AnsiFormat p9j,int n9,AnsiFormat p10j,int n10,AnsiFormat p11j,int n11) {
    //System.out.print("\n" + border_left);
    System.out.print("\n");
    skipfieldspacing();
    setNumberArr       (n1, n2, n3, n4, n5, n6 ,n7, n8, n9, n10, n11);
    setPrintArr_Jcolor (p1j,p2j,p3j,p4j,p5j,p6j,p7j,p8j,p9j,p10j,p11j);
    printLine_Jcolor(printArray_Jcolor, numberArr);       
    //System.out.print(border_right);
    skipfieldspacing();
  }

  void printLine_Jcolor (AnsiFormat [] array, int [] numberArray) {
    for (int i = 0; i < array.length; i++) {
      if (array[i] != c.BG_GAMEFIELD) fig_Jcolor(array[i], numberArray[i]);
      else                   skipfieldspacing();
      if (i != array.length-1) spacing();
    }
   }
  
   void setNumberArr (int n1, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, int n10, int n11) {
      numberArr[0] = n1;
      numberArr[1] = n2;
      numberArr[2] = n3;
      numberArr[3] = n4;
      numberArr[4] = n5;
      numberArr[5] = n6;
      numberArr[6] = n7;
      numberArr[7] = n8;
      numberArr[8] = n9;
      numberArr[9] = n10;
      numberArr[10] = n11;
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
 
  void fig_Jcolor (AnsiFormat color, int number) {
      //System.out.print(colorize("XX", color));
      if (number < 10) System.out.print(colorize("0" + Integer.toString(number), color));
      else             System.out.print(colorize(      Integer.toString(number), color));
    } 

  void spacing ()          {
    //System.out.print("   ");
    System.out.print(colorize("XXX", c.BG_GAMEFIELD));  
  }
  void skipfieldspacing () {
    //System.out.print("  ");
    System.out.print(colorize("XX", c.BG_GAMEFIELD));  
  }
   
  void emptyline_Jcolor () 
      {genericPrint_Jcolor(c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0,c.BG_GAMEFIELD,0);}
  // Methods ----------------------------------
}
