import Model.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    Deque<Player> players;
    Board gameboard;

    public TicTacToeGame() {
        initializeGame();
    }

    public void initializeGame() {
        players = new LinkedList<>();
        PlayingPieceX crossPiece = new PlayingPieceX();
        Player player1 = new Player("player1", crossPiece);
        PlayingPieceO circlePiece = new PlayingPieceO();
        Player player2 = new Player("player2", circlePiece);

        players.add(player1);
        players.add(player2);

        gameboard = new Board(3);
    }

    public String startGame() {
        boolean noWinner = true;
        while (noWinner) {
            Player playerTurn = players.removeFirst();
            gameboard.printBoard();
            List<Pair<Integer, Integer>> freeCells = gameboard.getFreeCells();
            if (freeCells.isEmpty()) {
                noWinner = false;
                continue;
            }
            System.out.println("Enter the position of " + playerTurn.name);
            Scanner sc = new Scanner(System.in);
            String pos = sc.nextLine();
            String[] s = pos.split(",");
            int rowNum = Integer.valueOf(s[0]);
            int colNum = Integer.valueOf(s[1]);

            boolean pieceAdded = gameboard.addPiece(rowNum, colNum, playerTurn.playingPiece);
            if (!pieceAdded) {
                System.out.println("Incorrect position choosen");
                players.addFirst(playerTurn);
                continue;
            }
            players.addLast(playerTurn);

            boolean winner = isThereWinner(rowNum, colNum, playerTurn.playingPiece.pieceType);
            if (winner) {
                return playerTurn.name;
            }
        }
        return "tie";
    }

    public boolean isThereWinner(int row, int column, PieceType pieceType) {

        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        //need to check in row
        for (int i = 0; i < gameboard.size; i++) {

            if (gameboard.board[row][i] == null || gameboard.board[row][i].pieceType != pieceType) {
                rowMatch = false;
            }
        }

        //need to check in column
        for (int i = 0; i < gameboard.size; i++) {

            if (gameboard.board[i][column] == null || gameboard.board[i][column].pieceType != pieceType) {
                columnMatch = false;
            }
        }

        //need to check diagonals
        for (int i = 0, j = 0; i < gameboard.size; i++, j++) {
            if (gameboard.board[i][j] == null || gameboard.board[i][j].pieceType != pieceType) {
                diagonalMatch = false;
            }
        }

        //need to check anti-diagonals
        for (int i = 0, j = gameboard.size - 1; i < gameboard.size; i++, j--) {
            if (gameboard.board[i][j] == null || gameboard.board[i][j].pieceType != pieceType) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;

    }
}
