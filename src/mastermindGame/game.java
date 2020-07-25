package mastermindGame;
import tools.text;
import java.util.ArrayList;
import java.util.Random;

public class game {
    public static enum code{green,white, red, blue, yellow, purple}
    public static enum peg{red, white}
    private int turn_;
    private int pegs_;
    private code[] master;
    private code[][] guess;
    private peg[][] conf;
    private boolean gameOver_;
    private boolean win_;
    private boolean monochrome_;

    public game(){
        master = new code[4];
        guess = new code[12][4];
        conf = new peg[12][4];
        turn_ = 1;
        gameOver_ = false;
        monochrome_ = false;
        pegs_ = 0;
        setMaster();
    }

    private code randomColour(){
        Random random = new Random();
        code[] codes = code.values();
        return codes[random.nextInt(codes.length)];
    }

    public void setMaster(){
        for(int i = 0; i<master.length; i++){
            master[i] = randomColour();
        }
    }

    public void setMonochrome_(boolean mono){
        monochrome_ = mono;
    }

    public void setGuess(code a, code b, code c, code d){
        guess[turn_-1][0] = a;
        guess[turn_-1][1] = b;
        guess[turn_-1][2] = c;
        guess[turn_-1][3] = d;
    }

    public void nextTurn(){
        if(turn_ < 13)
        turn_++;
    }

    public int getTurn_(){
        return turn_;
    }

    public boolean isGameOver(){
        return gameOver_;
    }

    public boolean isWin(){
        return win_;
    }

    public void checkCode(int t){
        pegs_ = 0;
        ArrayList<Integer> redIndex = new ArrayList<>();
        ArrayList<Integer> whiteIndex = new ArrayList<>();
        //Red Peg check and set
        for(int i = 0; i<guess[t-1].length;i++){
            if(guess[t-1][i] == master[i]){
                conf[t-1][pegs_] = peg.red;
                pegs_++;
                redIndex.add(i);
            }
        }
        //White Peg check and set
        if(redIndex.size() != 4) {
            for (int i = 0; i < guess[t - 1].length; i++) {
                if(!redIndex.contains(i)) {
                    for (int j = 0; j < master.length; j++) {
                        if (!whiteIndex.contains(j) && !redIndex.contains(j)) {
                            if (guess[t - 1][i] == master[j]) {
                                conf[t - 1][pegs_] = peg.white;
                                pegs_++;
                                whiteIndex.add(j);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    public void compare(){
        int check = 0;
        checkCode(turn_);
        for(int i = 0; i < pegs_; i++){
            if(conf[turn_-1][i].equals(peg.red))
                check++;
        }
        if(check == 4) {
            gameOver_ = true;
            win_ = true;
        }
    }

    //Monochrome it up
    public String printPegs(int t){
        String p = new String();
        int count = 0;
        for(int i = 0; i < 4; i++){
            if(conf[t][i] != null){
                count++;
            }
        }
        for(int i = 0; i < count; i++){
            if(conf[t][i] == peg.red && !monochrome_)
                p+= text.ANSI_RED + text.ANSI_BULLET + text.ANSI_RESET;
            else if(conf[t][i] == peg.red && monochrome_)
                p+= "R";
            else if(conf[t][i] == peg.white && monochrome_)
                p+= "W";
            else
                p+= text.ANSI_WHITE + text.ANSI_BULLET + text.ANSI_RESET;
        }
        return p;
    }
    //Coloured output
    public void printGuesses(){
        for(int j = 0; j < turn_; j++){
            String codeGuess = new String();
            for(int i = 0; i<guess[j].length;i++){
                switch (guess[j][i]){
                    case purple:
                        if(!monochrome_)
                            codeGuess += text.ANSI_PURPLE + text.ANSI_BULLET + text.ANSI_RESET;
                        else
                            codeGuess += "P";
                        break;
                    case red:
                        if(!monochrome_)
                            codeGuess += text.ANSI_RED + text.ANSI_BULLET + text.ANSI_RESET;
                        else
                            codeGuess += "R";
                        break;
                    case blue:
                        if(!monochrome_)
                            codeGuess += text.ANSI_BLUE + text.ANSI_BULLET + text.ANSI_RESET;
                        else
                            codeGuess += "B";
                        break;
                    case yellow:
                        if(!monochrome_)
                            codeGuess += text.ANSI_YELLOW + text.ANSI_BULLET + text.ANSI_RESET;
                        else
                            codeGuess += "Y";
                        break;
                    case white:
                        if(!monochrome_)
                            codeGuess += text.ANSI_WHITE + text.ANSI_BULLET + text.ANSI_RESET;
                        else
                            codeGuess += "W";
                        break;
                    case green:
                        if(!monochrome_)
                            codeGuess += text.ANSI_GREEN + text.ANSI_BULLET + text.ANSI_RESET;
                        else
                            codeGuess += "G";
                        break;
                }
            }
            System.out.println((j+1)+": "+codeGuess + "    " + printPegs(j));
        }
    }

    public void printMaster() {
        String codeGuess = new String();
        for (int i = 0; i < master.length; i++) {
            switch (master[i]) {
                case purple:
                    if(!monochrome_)
                        codeGuess += text.ANSI_PURPLE + text.ANSI_BULLET + text.ANSI_RESET;
                    else
                        codeGuess += "P";
                    break;
                case red:
                    if(!monochrome_)
                        codeGuess += text.ANSI_RED + text.ANSI_BULLET + text.ANSI_RESET;
                    else
                        codeGuess += "R";
                    break;
                case blue:
                    if(!monochrome_)
                        codeGuess += text.ANSI_BLUE + text.ANSI_BULLET + text.ANSI_RESET;
                    else
                        codeGuess += "B";
                    break;
                case yellow:
                    if(!monochrome_)
                        codeGuess += text.ANSI_YELLOW + text.ANSI_BULLET + text.ANSI_RESET;
                    else
                        codeGuess += "Y";
                    break;
                case white:
                    if(!monochrome_)
                        codeGuess += text.ANSI_WHITE + text.ANSI_BULLET + text.ANSI_RESET;
                    else
                        codeGuess += "W";
                    break;
                case green:
                    if(!monochrome_)
                        codeGuess += text.ANSI_GREEN + text.ANSI_BULLET + text.ANSI_RESET;
                    else
                        codeGuess += "G";
                    break;
            }
        }
        System.out.println(codeGuess);
    }
}