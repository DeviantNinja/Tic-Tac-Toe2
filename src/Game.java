import java.util.HashMap;
import java.util.Scanner;

public class Game {
    HashMap<String,Integer> locationMap; 
    char[][] board; 

    String column, row;
    char currentPlayer;

    public Game(){
        locationMap = new HashMap<>();

        locationMap.put("left",0);
        locationMap.put("top",0);
        locationMap.put("middle", 1);
        locationMap.put("right",2);
        locationMap.put("bottom", 2);

        board = new char[][]{{'-','-','-'},
                             {'-','-','-'},
                             {'-','-','-'}};

        boolean gameOver = false;
        int turnCount = 0;

        char playerOne = 'x';
        char playerTwo = 'o';
        
        boolean activePlayer = true;


        while(!gameOver){
            turnCount++;
            
            currentPlayer = activePlayer ? playerOne : playerTwo;

            boolean validPosition = false;
            
            while(!validPosition) {
                displayBoard();
                int[] playerPositionChoice = getPlayerPositionChoice();
                validPosition = updateBoard(playerPositionChoice[1],playerPositionChoice[0]);
            }



            if(checkWinner(board)){
                displayBoard();
                gameOver = true;
                System.out.println(currentPlayer + " is the winner!");
                break;
            } 

            if(turnCount == 9) {
                gameOver = true;
                System.out.println("The game ended in a draw!");
                break;
            }
            
            activePlayer = !activePlayer;

        }
    }

    /** prompts for the users to select the cordinates on the game board using plain english
     * @param type takes in a string of "row" or "column" to specifify qhich question to ask
     */
    private int[] getPlayerPositionChoice(){
        Scanner in = new Scanner(System.in);
        String input;

        int[] PlayerBoardPostion = new int[2];

        boolean validInput = false;

        while(!validInput) {
            System.out.print("Please choose column (Left, Middle, Right): ");
            input = in.next().toLowerCase();

            if(input.equals("left") || input.equals("middle") || input.equals("right")) {
                validInput = true;
                PlayerBoardPostion[0] = locationMap.get(input);
                break;
            }

            System.out.println("Invalid entry, please try again!");
        }

        validInput = false;

        while(!validInput) {
            System.out.print("Please choose row (Top, Middle, Bottom): ");
            input = in.next().toLowerCase();

            if(input.equals("top") || input.equals("middle") || input.equals("bottom")) {
                validInput = true;
                PlayerBoardPostion[1] = locationMap.get(input);
                break;
            }

            System.out.println("Invalid entry, please try again!");
        }

        return PlayerBoardPostion;
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

    private boolean updateBoard(int column, int row){

        if(board[column][row] == '-'){
           
            board[column][row] = currentPlayer;
            return true;

        }

        System.out.println("Postion is already in use! Please Choose Again");
        return false;

    }

    private boolean checkWinner(char[][] board){

        for(int i = 0;i <3;i++) {
            if(board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if(board[i][0] == '-' || board[i][1] == '-' || board[i][2] == '-' ){
                    break;
                } 
                
                return true;
            }

            if(board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                if(board[0][i] == '-' || board[1][i] == '-' || board[2][i] == '-' ){
                    break;
                } 
                
                return true;
            }

        }

        if(board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
           if(board[0][0] == '-' || board[1][1] == '-'|| board[2][2] == '-'){
                return false;
           }
            return true;
        } 


        if(board[2][0] == board[1][1] && board[1][1] == board[0][2]) {
            if(board[2][0] == '-' || board[1][1] == '-'|| board[0][2] == '-'){
                 return false;
            }
             return true;
         } 


        return false;
    }
}