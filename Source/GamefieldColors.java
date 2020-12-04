
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

    AnsiFormat BG_GAMEFIELD = BLACK;
    AnsiFormat BG_START     = GRAY;
    AnsiFormat BG_TARGET    = GRAY;

    GamefieldColors() {};
}
