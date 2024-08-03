import java.util.Scanner;


public class SudokuSolver
{
    private static final int GRID_SIZE = 9;
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int[][] board = new int[GRID_SIZE][GRID_SIZE];
        System.out.println("Enter the elemens to solve...");
        System.out.println("\nEnter '0' for unknown values...");
        for(int i = 0; i < GRID_SIZE; i++)
        {
            for(int j = 0; j < GRID_SIZE; j++)
            {
                board[i][j] = input.nextInt();
            }
        }
        System.out.println("Input...");
        printBoard(board);
        if(solveSudoku(board))
        {
            System.out.println("Solved Successfully  :)");
            printBoard(board);
        }
        else
        {
            System.out.println("Unsolvable board  :(");
        }

        input.close();
    }
    private static void printBoard(int[][] board)
    {
        System.out.println(" -----------------------");
        for (int row = 0; row < GRID_SIZE; row++)
        {
            if (row % 3 == 0 && row != 0)
            {
                System.out.println("|-------|-------|-------|");
            }
            for (int column = 0; column < GRID_SIZE; column++)
            {
                if (column % 3 == 0)
                {
                    System.out.print("| ");
                }
                System.out.print(board[row][column] == 0 ? " " : board[row][column]);
                System.out.print(" ");
            }
            System.out.println("|");
        }
        System.out.println(" -----------------------");
    }


    private static boolean isNumberInRow(int[][] board, int Number, int row)
    {
        for(int i = 0; i < GRID_SIZE; i++)
        {
            if(board[row][i] == Number)
            {
                return true;
            }
        }
        return false;
    }
    private static boolean isNumberInColumn(int[][] board, int Number, int column)
    {
        for(int i = 0; i < GRID_SIZE; i++)
        {
            if(board[i][column] == Number)
            {
                return true;
            }
        }
        return false;
    }
    private static boolean isNumberInLocalBox(int[][] board, int Number, int row, int column)
    {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;
        for(int i = localBoxRow; i < localBoxRow + 3; i++)
        {
            for(int j = localBoxColumn; j < localBoxColumn + 3; j++)
            {
                if(board[i][j] == Number)
                {
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean isValidPlacement(int[][] board, int Number, int row, int column)
    {
        return !isNumberInRow(board, Number, row) &&
                !isNumberInColumn(board, Number, column) &&
                !isNumberInLocalBox(board, Number, row, column);
    }
    private static boolean solveSudoku(int[][] board)
    {
        for(int row = 0; row < GRID_SIZE; row++)
        {
            for(int column = 0; column < GRID_SIZE; column++)
            {
                if(board[row][column] == 0)
                {
                    for(int numberToTry = 1; numberToTry <= 9; numberToTry++)
                    {
                        if(isValidPlacement(board, numberToTry, row, column))
                        {
                            board[row][column] = numberToTry;

                            if(solveSudoku(board))
                            {
                                return true;
                            }
                            else
                            {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}