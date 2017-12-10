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
    private boolean boardHasBeenSet = false;
    private boolean started = false;
    private boolean ended = false;
    private int[][] board;
    private int[][] hittedboard;


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public map(int inputHeight, int inputWidth) throws Exception {
        setBoard(inputHeight, inputWidth);
    }




    public boolean setHeight(final int setHeight) throws Exception {
        if (setHeight >= MIN_HEIGHT && setHeight <= MAX_HEIGHT) {
            this.height = setHeight;
            return true;
        }
        throw new Exception("Height out of Range(10-20)!");
    }

    public boolean setWidth(final int setWidth) throws Exception {
        if (setWidth >= MIN_WIDTH && setWidth <= MAX_WIDTH) {
            this.width = setWidth;
            return true;
        }
        throw new Exception("Width out of Range(10-20)!");
    }

    public void setBoard(final int setHeight, final int setWidth) throws Exception{
        if (setHeight(setHeight) && setWidth(setWidth)) {
            boardHasBeenSet = true;
            board = new int[width][height];
            hittedboard = new int[width][height];
            Arrays.fill(board, -1);
            Arrays.fill(board, -1);
            return;
        }
        throw new Exception("SetBoard ERROR !");
    }

    public void setShip(final int x1, final int y1, final int x2, final int y2) throws Exception {
        if (!boardHasBeenSet) {
            throw new Exception("The Board has been already setted");
        }

        //test in/out of bound;
        if (x1 > width || x2 > width || y1 > height || y2 > height) {
            String message = "The ship is out of bound (" + width + ", " + height + ") !";
            throw new Exception(message);
        }
        // test whether diagonal;
        if ((x1 != x2) && (y1 != y2)) {
            throw new Exception("Your ship is neither Horizonal nor Vertical!");
        }

        // if valid, then place the ship in the position;
        // class 'ship' needed;
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if(board[i][j] == empty_space) {
                    board[i][j] = ship;
                } else {
                    throw new Exception("There is already ship(s) in there, please check your map!");
                }

            }
        }
        started = true;
    }

    public boolean shoot(final int x, final int y) throws Exception{
        if (!started) {
            throw new Exception("You cannot shoot before all ships are setted and both player are ready!")
        }
        if (board[x][y] == ship) {
            board[x][y] = explode;
            return true;
        }
        return false;
    }

    public boolean getWinner() {
        if (started != false) {
            return false;
        }
        for (int i = 0; i <= width; i++) {
            for (int j = 0; j <= height; j++) {
                if (board[i][j] == 1) {
                    return false;
                }
            }
        }
        ended = true;
        return true;

    }


    public boolean show() {
        if (ended) {
            return true;
        }
        return false;
    }

    public synchronized map getMap() {
        return this;
    }


	/* get winner ? need a player class*/

	/* how to cover the board? the covered board should correspond to the board having ships on it*/

	/* create numbers of ships; cover the board; show the board*/


}
