import java.util.HashMap;
import java.util.Scanner;

public class Game {
    HashMap<String,Integer> locationMap; 
    char[][] board; 

    String column, row;

    public Game(){
        locationMap = new HashMap<>();
        board = new char[][]{{'-','-','-'},{'-','-','-'},{'-','-','-'}};

        locationMap.put("left",0);
        locationMap.put("top",0);
        locationMap.put("middle", 1);
        locationMap.put("right",2);
        locationMap.put("bottom", 1);

        displayBoard();
        promptForInput("column");
        promptForInput("row");

    }

    /** prompts for the users to select the cordinates on the game board using plain english
     * @param type takes in a string of "row" or "column" to specifify qhich question to ask
     */
    private void promptForInput(String type){
        Scanner input = new Scanner(System.in);
        String inputWord;

        String rowQuestion = "Select column (Left, Middle, Right): ";
        String columnQuestion = "Select column (Top, Middle, Bottom): ";
        String question = type.equals("row") ? rowQuestion : columnQuestion;

        do {
            System.out.print(question);
            inputWord = input.next().toLowerCase();

            if(locationMap.get(inputWord) == null) {
                System.out.println("Invalid entry please try again!");
            } else {
                 if(type.equals("row")) {
                    row = inputWord;
                    
                 } else {
                    column = inputWord;
                    
                 }
            }
        } while (locationMap.get(inputWord) == null);


    }

    /** outputs the current state of the game board */
    private void displayBoard(){
        for(int c = 0; c < 3; c++){
            for(int r = 0; r < 3; r++) {
                System.out.print("|");
                System.out.print(board[c][r]);
            }
            System.out.print("|");
            System.out.println("");
        }
    }

}
