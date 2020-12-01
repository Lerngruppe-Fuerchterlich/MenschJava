import        org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class GamefieldPrinter {
                                
  Color[] printArray = new Color[11];
  
  String border_right  = " +";  
  String border_left   = " + ";
  String border_top    = "+";
  String border_bottom = "+";
  
  GamefieldPrinter (){ /* */ };
  
  void setBorderRight  (String appearance) {border_right  = appearance;}
  void setBorderLeft   (String appearance) {border_left   = appearance;}
  void setBorderTop    (String appearance) {border_top    = appearance;}
  void setBorderBottom (String appearance) {border_bottom = appearance;}
  
  void genericPrint(Color p1,Color p2,Color p3,Color p4,Color p5,Color p6,Color p7,Color p8,Color p9,Color p10,Color p11) {
         System.out.print("\n" + border_left);
         setPrintArr (p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11);
         printLine (printArray);       
         System.out.print(border_right);
     }
  void printLine (Color [] array) {
        for (int i = 0; i < array.length; i++) {
          if (array[i] != BLACK) fig(array[i]);
          else                   skipfieldspacing();
          if (i != array.length-1) spacing();
        }
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
  void fig (Color color) 
          {System.out.print(ansi().fg(color).bg(color).a("XX").reset());}
     
     void spacing ()          {System.out.print("   ");}
     void skipfieldspacing () {System.out.print("  ");}
     
    void emptyline ()
         {genericPrint(BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK);}        
  
}
