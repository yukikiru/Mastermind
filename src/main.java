import mastermindGame.game;
import tools.text;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

    public static void main(String[] args){

        //set up main game object
        game mainGame = new game();
        //mainGame.setGuess(game.code.red, game.code.green, game.code.blue, game.code.yellow);
        System.out.println("Welcome to Mastermind!");
        System.out.println("Available colours are:");
        System.out.println(text.ANSI_BLUE + text.ANSI_BULLET +" b = Blue " + text.ANSI_BLUE + text.ANSI_BULLET + text.ANSI_RESET);
        System.out.println(text.ANSI_RED + text.ANSI_BULLET +" r = Red " + text.ANSI_RED + text.ANSI_BULLET + text.ANSI_RESET);
        System.out.println(text.ANSI_YELLOW + text.ANSI_BULLET +" y = Yellow " + text.ANSI_YELLOW + text.ANSI_BULLET + text.ANSI_RESET);
        System.out.println(text.ANSI_PURPLE + text.ANSI_BULLET +" p = Purple " + text.ANSI_PURPLE + text.ANSI_BULLET + text.ANSI_RESET);
        System.out.println(text.ANSI_GREEN + text.ANSI_BULLET +" g = Green " + text.ANSI_GREEN + text.ANSI_BULLET + text.ANSI_RESET);
        System.out.println(text.ANSI_WHITE + text.ANSI_BULLET +" w = White " + text.ANSI_WHITE + text.ANSI_BULLET + text.ANSI_RESET);
        //main loop
        while(mainGame.getTurn_() < 12){
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
}

