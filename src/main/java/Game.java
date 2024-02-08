import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Board gameBoard = new Board();
        Scanner in = new Scanner(System.in);

        gameBoard.printBoard();
        while (true) {
            gameBoard.handleBoardStatus();

            try {
                // SELECT PEG
                System.out.println("Select one peg to move (row col): ");
                int selectedRow = in.nextInt();
                int selectedCol = in.nextInt();
                Cell selectedCell = gameBoard.getBoardMatrix()[selectedRow][selectedCol];

                if (selectedCell.getValue() != 1) {
                    System.out.println("Invalid selection, please select a peg.");
                    continue;
                }

                // TARGET PEG
                System.out.println("Select target position (row col): ");
                int targetRow = in.nextInt();
                int targetCol = in.nextInt();
                Cell targetCell = gameBoard.getBoardMatrix()[targetRow][targetCol];

                if (targetCell.getValue() != 0) {
                    System.out.println("Invalid target, please select an empty cell.");
                    continue;
                }

                // MOVEMENT
                Board.Direction movementDirection = gameBoard.getDirection(selectedCell, targetCell);
                if (movementDirection != null) {
                    gameBoard.handleMovement(selectedCell, targetCell, movementDirection);
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
