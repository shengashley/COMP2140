import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LastNameFirstNameA1Q2 {
    private static int blockSize = 0;
    private static int gridRow = 0;
    private static int gridCol = 0;
    private static boolean isInteger = false;
    private static String [][] board;
    private static final String inputBlockSize = "Please input block size:";


    static class Sudoku {
        Sudoku(String [] input){
            int count = 0 ;
            board = new String [gridRow][gridCol];
            for(int i = 0 ; i< gridRow;i++){
                for(int j=0 ;j < gridCol;j++){
                    board[i][j] = input[count];
                    count++;
                }
            }
            try {
                solve(0, 0);
            }catch (Exception e){
                //e.printStackTrace();
            }

        }

        Sudoku(){
            board = new String [gridRow][gridCol];
            for(int i = 0 ; i< gridRow;i++){
                for(int j=0 ;j < gridCol;j++){
                    board[i][j] = "-";
                }
            }
            try {
                solve(0, 0);
            }catch (Exception e){
                //e.printStackTrace();
            }
        }


        void solve(int row, int col) throws Exception {
            if (row > gridRow - 1)
                System.out.println("Solution found");
            if (!board[row][col].equals("-")) {
                    solveNext(row, col);
                } else {
                    for (int i = 1; i < getGridRow() + 1; i++) {
                        String num = Integer.toString(i);
                        if (checkBlock(row, col, num) && checkRow(row, num) && checkCol(col, num)) {
                            board[row][col] = num;
                            solveNext(row, col);
                        }
                    }
                    board[row][col] = "-";
                }
            }

        void solveNext(int row, int col)throws Exception {

            if(col < gridCol-1)
                solve(row, col+1);
            else
                solve(row+1,0);
        }

        /** Checks if num is an acceptable value for the given row */
        boolean checkRow(int row, String num){
            for(int i = 0 ; i < gridCol ; i++){
                if(board[row][i].equals(num)){
                    return false;
                }
            }
                   return true;
        }

        /** Checks if num is an acceptable value for the given column */
        boolean checkCol( int col, String num )
        {
            for( int i = 0; i < gridRow; i++ ) {
                if (board[i][col].equals(num)) {
                    return false;
                }
            }
                   return true ;
        }

        /** Checks if num is an acceptable value for the block around row and col */
        boolean checkBlock( int row, int col, String num )
        {
            row = (row / blockSize) * blockSize ;
            col = (col / blockSize) * blockSize ;

            for( int i = 0; i < blockSize; i++ ) {
                for (int j = 0; j < blockSize; j++) {
                    if (board[row + i][col + j].equals(num)) {
                        return false;
                    }
                }
            }

            return true ;
        }
    }

    static void setGridRow(int blockSize){
        gridRow = blockSize * blockSize;
    }

    static void setGridCol(int blockSize){
        gridCol = blockSize * blockSize;
    }

    static int getGridRow(){
        return gridRow;
    }

    static int getGridCol(){
        return gridCol;
    }

    static void printPuzzlePrompt(){
        System.out.println("Please input (" + getGridRow() +"x" +getGridCol()+") grid puzzle:");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(inputBlockSize);

        //Make sure user input an integer for block
        while (!isInteger) {
            if (scanner.hasNextInt()) {
                blockSize = scanner.nextInt();
                break;
            }
            System.out.println(inputBlockSize);
            scanner.nextLine();
        }

        //set grid row
         setGridRow(blockSize);
        //set total col
        setGridCol(blockSize);


        printPuzzlePrompt();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boolean isFinished = false;

        while(!isFinished) {
            try {

                   String inputString = reader.readLine();
                   String[] result =  inputString.split(" ");

                   if(result.length != gridRow * gridCol) {
                       /**If the user-provided board doesn’t contain a valid number of
                        entries, or contains a number that is invalid for the board size, ignore the user input and start
                        with an empty 9 x 9  **/
                       blockSize = 3;
                       setGridRow(blockSize);
                       setGridCol(blockSize);
                       Sudoku sudoku = new Sudoku();
                       isFinished = true;
                   }else {

                       if(!isCorrectPuzzleFormat(result)){
                           printPuzzlePrompt();
                           reader = new BufferedReader(new InputStreamReader(System.in));
                       }else {
                           Sudoku sudoku = new Sudoku(result);
                           isFinished = true;
                       }
                   }
                /** print result **/
                for(int i = 0 ; i< gridRow;i++){
                    for(int j=0 ;j < gridCol;j++){
                        System.out.print(board[i][j]);
                    }
                    System.out.println();
                }

            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    /** Check the if it is valid format from user input **/
    private static boolean isCorrectPuzzleFormat(String[] inputString){
        //find the number and -
        String regex = "[+]?[0-9-]+";
        //only number
        String regex_num = "[+]?[0-9]+";
        Pattern p = Pattern.compile(regex);
        Pattern pNum = Pattern.compile(regex_num);

        for(int i=0; i< inputString.length; i++){
           Matcher m = p.matcher(inputString[i]);
           /**If find other than number and "-" then return false**/
           if( !m.find() || !m.group().equals(inputString[i]))
               return false;
           else {
               Matcher m_num = pNum.matcher(inputString[i]);
               if( m_num.find() && m_num.group().equals(inputString[i])) {
                   /**if the number is smaller than 0 or larger than grid number then return false **/
                    if(Integer.parseInt(inputString[i]) <= 0 || Integer.parseInt(inputString[i])>(blockSize*blockSize))
                        return false;
               }
           }
        }
        return true;
    }
}
