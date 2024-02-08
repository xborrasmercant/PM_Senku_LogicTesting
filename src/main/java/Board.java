public class Board {
    private int[][] boardMatrix;

    public Board () {
        boardMatrix = new int[7][7];
        initBoard();
    }


    public void initBoard() {

        // Fill with pegs
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                boardMatrix[i][j] = 1;
            }
        }

        // Define borders of board
        int[][] borders = new int[][]{ {0, 0}, {0, 1}, {1, 0}, {1, 1}, {0, 5}, {0, 6}, {1, 5}, {1, 6}, {5, 0}, {5, 1}, {6, 0}, {6, 1}, {5, 5}, {5, 6}, {6, 5}, {6, 6}};
        for (int[] pos : borders) {
            boardMatrix[pos[0]][pos[1]] = -1;
        }

        // Set the empty position of the board (center)
        boardMatrix[3][3] = 0;
    }

    public void printBoard() {
        for (int[] row : boardMatrix) {
            for (int cell : row) {
                if (cell == -1) {
                    System.out.print("  ");
                } else {
                    System.out.print(cell + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Board gameBoard = new Board();

        gameBoard.printBoard();
    }
}