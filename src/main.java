import mastermindGame.game;
import tools.text;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

    public static void main(String[] args){
        boolean mono = false;
        //set up main game object
        game mainGame = new game();
        //mainGame.setGuess(game.code.red, game.code.green, game.code.blue, game.code.yellow);
        System.out.println("Welcome to Mastermind!");
        mono = monoCheck(mainGame);
        System.out.println("Available colours are:");
        printColours(mono);
        //main loop
        while(mainGame.getTurn_() < 13){
            System.out.println("Turn " + mainGame.getTurn_());
            System.out.println("-----------");
            getGuess(mainGame);
            mainGame.compare();
            mainGame.printGuesses();
            mainGame.nextTurn();
            if(mainGame.isGameOver())
                break;
        }
        if(mainGame.isWin()){
            System.out.println("You cracked the code! Congrats!");
        }
        else{
            System.out.println("Game Over! You couldn't crack the code.");
            System.out.println("The master code was:");
            mainGame.printMaster();
        }

    }
    //This function will get the guess from the user, parse the string and then set the guess to the game object
    public static void getGuess(game g){
        Scanner guess = new Scanner(System.in);

        //array that will hold colours to add to the guess
        ArrayList<game.code> myCode = new ArrayList<>();
        //check variable for user input
        boolean check = true;
        //Add more user input checks later
        while(check){
            boolean wrongChar = false;
            //Main scanner input
            System.out.println("Please enter your guess [format: ybpr]:");
            String myGuess = guess.nextLine();
            String errorMessage = new String();
            if(myGuess.length() == 4){
                for(int i = 0; i < myGuess.length(); i ++){
                    switch (myGuess.charAt(i)){
                        case 'b':
                            myCode.add(game.code.blue);
                            check = false;
                            break;
                        case 'r':
                            myCode.add(game.code.red);
                            check = false;
                            break;
                        case 'y':
                            myCode.add(game.code.yellow);
                            check = false;
                            break;
                        case 'p':
                            myCode.add(game.code.purple);
                            check = false;
                            break;
                        case 'g':
                            myCode.add(game.code.green);
                            check = false;
                            break;
                        case 'w':
                            myCode.add(game.code.white);
                            check = false;
                            break;
                        default:
                            errorMessage= "Only enter a valid colour";
                            wrongChar = true;
                            break;
                            //will need to add another check to break the parsing loop
                    }
                }
            }
            else{
                System.out.println("Your code must be 4 pegs.");
            }
            if(wrongChar){
                check = true;
                System.out.println(errorMessage);
            }

        }
        g.setGuess(myCode.get(0),myCode.get(1),myCode.get(2),myCode.get(3));
    }

    public static void printColours(boolean mono){
        if(!mono){
            System.out.println(text.ANSI_BLUE + text.ANSI_BULLET +" b = Blue " + text.ANSI_BLUE + text.ANSI_BULLET + text.ANSI_RESET);
            System.out.println(text.ANSI_RED + text.ANSI_BULLET +" r = Red " + text.ANSI_RED + text.ANSI_BULLET + text.ANSI_RESET);
            System.out.println(text.ANSI_YELLOW + text.ANSI_BULLET +" y = Yellow " + text.ANSI_YELLOW + text.ANSI_BULLET + text.ANSI_RESET);
            System.out.println(text.ANSI_PURPLE + text.ANSI_BULLET +" p = Purple " + text.ANSI_PURPLE + text.ANSI_BULLET + text.ANSI_RESET);
            System.out.println(text.ANSI_GREEN + text.ANSI_BULLET +" g = Green " + text.ANSI_GREEN + text.ANSI_BULLET + text.ANSI_RESET);
            System.out.println(text.ANSI_WHITE + text.ANSI_BULLET +" w = White " + text.ANSI_WHITE + text.ANSI_BULLET + text.ANSI_RESET);
        }
        else{
            System.out.println("B = Blue");
            System.out.println("R = Red");
            System.out.println("Y = Yellow");
            System.out.println("P = Purple");
            System.out.println("G = Green");
            System.out.println("W = White");
        }
    }

    public static boolean monoCheck(game g){
        System.out.println("-----------");
        boolean check = true;
        while(check){
            System.out.println("Select colour or mono mode: \n[if your terminal can't display \"Colour\" in colour, use monochrome]");
            System.out.println("c - "+text.ANSI_BLUE+"C"+text.ANSI_GREEN+"o"+text.ANSI_RED+"l"+text.ANSI_PURPLE+"o"+
                    text.ANSI_YELLOW+"u"+text.ANSI_CYAN+"r"+text.ANSI_RESET);
            System.out.println("m - Monochrome");
            Scanner m = new Scanner(System.in);
            String isMono = m.nextLine();
            switch (isMono.toLowerCase()){
                case "c":
                    g.setMonochrome_(false);
                    return false;
                case "m":
                    g.setMonochrome_(true);
                    return true;
                default:
                    System.out.println("Please only enter c for colour or m for monochrome\n");
                    break;
            }
        }

        return true;
    }
}

