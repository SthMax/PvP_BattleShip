import java.util.Arrays;

public class map {


    private static final int MAX_HEIGHT = 20;
    private static final int MIN_HEIGHT = 10;
    private static final int MAX_WIDTH = 20;
    private static final int MIN_WIDTH = 10;
    private static final int empty_space = -1;
    private static final int ship = 1;
    private static final int explode = 0;

    private int height;
    private int width;
    private int shipSpacesCount = 0;
    private boolean boardHasBeenSet = false;
    private boolean started = false;
    private boolean ended = false;
    private int[][] board;
    private int[][] hittedboard;

    public static final int shipLimit = 5;
    public static final int shipSpaces = 15;


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public map(int inputHeight, int inputWidth) throws Exception {
        setBoard(inputHeight, inputWidth);
    }


    public void setBoard(final int setHeight, final int setWidth) throws Exception{
        if (setHeight >= MIN_HEIGHT && setHeight <= MAX_HEIGHT
                && setWidth >= MIN_WIDTH && setWidth <= MAX_WIDTH) {
            boardHasBeenSet = true;
            board = new int[width][height];
            hittedboard = new int[width][height];
            Arrays.fill(board, -1);
            Arrays.fill(board, -1);
            return;
        }
        throw new Exception("SetBoard ERROR !");
    }

    public void setShip(final int x1, final int y1, final int x2, final int y2, final int shipLength) throws Exception {
        if (Math.abs(x2-x1) + Math.abs(y2-y1) + 1 != shipLength) {
            throw new Exception("The Length of the ship you need to set is not correct!");
        }

        if (!boardHasBeenSet) {
            throw new Exception("The Board has been already set");
        }

        //test in/out of bound;
        if (x1 > width || x2 > width || y1 > height || y2 > height) {
            String message = "The ship is out of bound (" + width + ", " + height + ") !";
            throw new Exception(message);
        }

        // test whether diagonal;
        if ((x1 != x2) && (y1 != y2)) {
            throw new Exception("Your ship is neither Horizontal nor Vertical!");
        }

        // test if it has too much ships
        if (shipSpacesCount + shipLength > shipSpaces) {
            throw new Exception("There are too many ships!");
        }

        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if(board[i-1][j-1] != empty_space) {
                    throw new Exception("There is already ship(s) in there, please check your map!");
                }
            }
        }

        // if valid, then place the ship in the position;
        // class 'ship' needed;
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                board[i][j] = ship;
                shipSpacesCount++;
            }
        }
        System.out.println("The ship has been successfully set on "
                + x1 + " " + y1 + " to " + x2 + " " + y2);

        if (shipLength == 1) {
            started = true;
        }
    }


    public boolean shoot(final int x, final int y) throws Exception{
        if (!started) {
            throw new Exception("You cannot shoot before all ships are set and both player are ready!");
        }

        if (x > width || y > height) {
            throw new Exception("This shot is out of map");
        }

        if (board[x-1][y-1] == ship) {
            board[x-1][y-1] = explode;
            return true;
        }
        return false;
    }

    public boolean getWinner() throws Exception {
        if (!started) {
            throw new Exception("There is no winner before a game started!");
        }
        for (int i = 0; i <= width; i++) {
            for (int j = 0; j <= height; j++) {
                if (board[i][j] == ship) {
                    return false;
                }
            }
        }
        ended = true;
        return true;

    }

    public void printMap() {
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] == empty_space) {
                    System.out.print(" ");
                } else if (board[j][i] == ship) {
                    System.out.print("O");
                } else if (board[j][i] == explode) {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }


    public boolean show() {
        if (ended) {
            return true;
        }
        return false;
    }



	/* get winner ? need a player class*/

	/* how to cover the board? the covered board should correspond to the board having ships on it*/

	/* create numbers of ships; cover the board; show the board*/


}
