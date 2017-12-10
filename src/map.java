public class map {


    public static final int MAX_HEIGHT = 20;
    public static final int MIN_HEIGHT = 10;
    public static final int MAX_WIDTH = 20;
    public static final int MIN_WIDTH = 10;
    public static final int ship = 1;
    public static final int explode = 0;

    private int height;
    private int width;
    private boolean boardHasBeenSet = false;
    private boolean started = false;
    private boolean ended = false;
    private int[][] board;


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }




    public boolean setHeight(final int setHeight) {
        if (setHeight >= MIN_HEIGHT && setHeight <= MAX_HEIGHT) {
            this.height = setHeight;
            return true;
        }
        return false;
    }

    public boolean setWidth(final int setWidth) {
        if (setWidth >= MIN_WIDTH && setWidth <= MAX_WIDTH) {
            this.width = setWidth;
            return true;
        }
        return false;
    }
    public boolean setBoard(final int setHeight, final int setWidth) {
        if (setHeight(setHeight) && setWidth(setWidth)) {
            boardHasBeenSet = true;
            board = new int[width][height];
            return true;
        }
        return false;
    }

    public boolean setShip(final int x1, final int y1, final int x2, final int y2) {
        if (!boardHasBeenSet) {
            return false;
        }

        //test in/out of bound;
        if (x1 > width || x2 > width || y1 > height || y2 > height) {
            return false;
        }
        // test whether diagonal;
        if ((x1 != x2) && (y1 != y2)) {
            return false;
        }
        //test if the position has no ships on it;
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (board[i][j] == 1) {
                    return false;
                }
            }
        }

        // if valid, then place the ship in the position;
        // class 'ship' needed;
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                board[i][j] = ship;
            }
        }
        started = true;
        return true;
    }

    public boolean shoot(final int x, final int y) {
        if (!started) {
            return false;
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


	/* get winner ? need a player class*/

	/* how to cover the board? the covered board should correspond to the board having ships on it*/

	/* create numbers of ships; cover the board; show the board*/


}
