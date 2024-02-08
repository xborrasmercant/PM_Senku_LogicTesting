import java.util.Scanner;

public class Board {
    private Cell[][] boardMatrix;
    public enum Direction {
        UP, DOWN, RIGHT, LEFT;
    }


    public Board () {
        boardMatrix = new Cell[7][7];
        initBoard();
    }


    public void moveCell(Cell selectedCell, Cell targetCell, Direction movementDirection) {

        switch (movementDirection) {
            case DOWN -> handleMovement(selectedCell, targetCell, selectedCell.getRowPos()+1, selectedCell.getColPos());
            case UP -> handleMovement(selectedCell, targetCell, selectedCell.getRowPos()-1, selectedCell.getColPos()+1);
            case RIGHT -> handleMovement(selectedCell, targetCell, selectedCell.getRowPos(), selectedCell.getColPos()+1);
            case LEFT -> handleMovement(selectedCell, targetCell, selectedCell.getRowPos(), selectedCell.getColPos()-1);
        }
    }

    public void handleMovement(Cell selectedCell, Cell targetCell, int row, int col) {
        selectedCell.setValue(0);
        boardMatrix[row][col].setValue(0); // Empty cell between selected and target cells
        targetCell.setValue(1);
    }

    public Direction getDirection(Cell selectedCell, Cell targetCell) {

        // Check the difference result of selected and target pegs.
        int rowDif = selectedCell.getRowPos() - targetCell.getRowPos(), colDif = selectedCell.getColPos() - targetCell.getColPos();


        if (rowDif > 2 || rowDif < -2 || colDif > 2 || colDif < -2) {
            return null;
        }

        if (colDif == 0) {
            return switch (rowDif) {
                case -2 -> Direction.DOWN;
                case 2 -> Direction.UP;
                default -> null;
            };
        }

        return switch (colDif) {
            case -2 -> Direction.RIGHT;
            case 2 -> Direction.LEFT;
            default -> null;
        };
    }

    public void initBoard() {

        // Fill with pegs
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                boardMatrix[row][col] = new Cell(col, row, 1);
            }
        }

        // Define borders of board
        int[][] borders = new int[][]{ {0, 0}, {0, 1}, {1, 0}, {1, 1}, {0, 5}, {0, 6}, {1, 5}, {1, 6}, {5, 0}, {5, 1}, {6, 0}, {6, 1}, {5, 5}, {5, 6}, {6, 5}, {6, 6}};
        for (int[] pos : borders) {
            boardMatrix[pos[0]][pos[1]].setValue(-1);
        }

        // Set the empty position of the board (center)
        boardMatrix[3][3].setValue(0);
    }
    public void cleanTerminal(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

    }
    public void printBoard() {
        cleanTerminal();

        for (Cell[] row : boardMatrix) {
            for (Cell peg : row) {
                if (peg.getValue() == -1) {
                    System.out.print("  ");
                } else {
                    System.out.print(peg.getValue() + " ");
                }
            }
            System.out.println();
        }
        System.out.println();

    }

    public static void main(String[] args) {
        Board gameBoard = new Board();
        Scanner in = new Scanner(System.in);

        gameBoard.printBoard();
        while (true) {
            try {
                System.out.println("Select one peg to move (row col): ");
                int selectedRow = in.nextInt();
                int selectedCol = in.nextInt();
                Cell selectedCell = gameBoard.boardMatrix[selectedRow][selectedCol];

                if (selectedCell.getValue() != 1) {
                    System.out.println("Invalid selection, please select a peg.");
                    continue;
                }

                System.out.println("Select target position (row col): ");
                int targetRow = in.nextInt();
                int targetCol = in.nextInt();
                Cell targetCell = gameBoard.boardMatrix[targetRow][targetCol];

                if (targetCell.getValue() != 0) {
                    System.out.println("Invalid target, please select an empty cell.");
                    continue;
                }

                Direction movementDirection = gameBoard.getDirection(selectedCell, targetCell);
                if (movementDirection != null) {
                    gameBoard.moveCell(selectedCell, targetCell, movementDirection);
                    gameBoard.printBoard();
                } else {
                    System.out.println("Invalid move, please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please enter valid row and column numbers.");
                in.nextLine(); // Clear the buffer
            }
        }
    }
}