package Model;

import java.util.ArrayList;
import java.util.List;



public class Board {

    public int size;
    public PlayingPiece[][] board;

    public Board(int n){
        this.size = n;
        this.board = new PlayingPiece[n][n];
    }

    public boolean addPiece(int row, int col, PlayingPiece playingPiece){
        if(board[row][col] != null)
            return false;
        board[row][col] = playingPiece;
        return true;
    }

    public List<Pair<Integer,Integer>> getFreeCells(){
        List<Pair<Integer,Integer>> freeCells = new ArrayList<>();
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                if(board[i][j]==null) {
                    Pair<Integer, Integer> rowColumn = new Pair<>(i, j);
                    freeCells.add(rowColumn);
                }
            }
        }
        return freeCells;
    }

    public void printBoard(){
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                if(board[i][j]!=null) {
                    System.out.print(board[i][j].pieceType.name() + "   ");
                }else {
                    System.out.print("   ");
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }


}
