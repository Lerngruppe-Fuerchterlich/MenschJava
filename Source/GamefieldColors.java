
import        com.diogonunes.jcolor.Ansi;
import        com.diogonunes.jcolor.AnsiFormat;
import        com.diogonunes.jcolor.Attribute;
import static com.diogonunes.jcolor.Ansi.*;
import static com.diogonunes.jcolor.Attribute.*;
import static com.diogonunes.jcolor.AnsiFormat.*;

public class GamefieldColors {

    static AnsiFormat RED    = new AnsiFormat(RED_BACK(),    RED_TEXT());
    static AnsiFormat YELLOW = new AnsiFormat(YELLOW_BACK(), YELLOW_TEXT());
    static AnsiFormat BLUE   = new AnsiFormat(BLUE_BACK(),   BLUE_TEXT());
    static AnsiFormat GREEN  = new AnsiFormat(GREEN_BACK(),  GREEN_TEXT());
    static AnsiFormat BLACK  = new AnsiFormat(BACK_COLOR(0,0,0),  TEXT_COLOR(0,0,0));
    static AnsiFormat GRAY   = new AnsiFormat(BACK_COLOR(100,100,100),  TEXT_COLOR(100,100,100));
    static AnsiFormat WHITE  = new AnsiFormat(BACK_COLOR(255,255,255),  TEXT_COLOR(255,255,255));

    static AnsiFormat RED_T    = new AnsiFormat(RED_BACK(),    TEXT_COLOR(244,121,128));
    static AnsiFormat YELLOW_T = new AnsiFormat(YELLOW_BACK(), YELLOW_TEXT());
    static AnsiFormat BLUE_T   = new AnsiFormat(BLUE_BACK(),   BLUE_TEXT());
    static AnsiFormat GREEN_T  = new AnsiFormat(GREEN_BACK(),  GREEN_TEXT());
    static AnsiFormat GRAY_T   = new AnsiFormat(BACK_COLOR(100,100,100),  TEXT_COLOR(50,50,50));
    static AnsiFormat WHITE_T  = new AnsiFormat(BACK_COLOR(255,255,255),  TEXT_COLOR(140,140,140));

    AnsiFormat BG_FIELD = WHITE_T;
    AnsiFormat BG_GAMEFIELD = BLACK;
    AnsiFormat BG_START     = GRAY;
    AnsiFormat BG_TARGET    = GRAY_T;

    GamefieldColors() {};
}
