package com.chess.engine.pieces;

import com.chess.engine.Color;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece {

    private final static int[] OFFSETS = {8, 16, 7, 9};

    public Pawn(int position, Color pieceAlliance) {
        super(PieceType.PAWN, position, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentOffset: OFFSETS) {

            int destinationCoordinate = this.position + currentOffset * this.getPieceColor().getDirection();

            if(!BoardUtils.isValidTileCoordinate(destinationCoordinate)) {
                continue;
            }

            //For a pawn to move forward normally, one space
            if(currentOffset == 8 && !board.getTile(destinationCoordinate).isTileOccupied()) {
                legalMoves.add(new Move.MajorMove(board, this, destinationCoordinate));
            }

            //If its the first move of a pawn, a pawn can move 2 spaces forward
            else if (currentOffset == 16 && this.isFirstMove() &&
                    (BoardUtils.SECOND_ROW[this.position]) && (this.getPieceColor().isBlack()) ||
                    (BoardUtils.SEVENTH_ROW[this.position]) && (this.getPieceColor().isWhite()) &&
                    (!board.getTile(destinationCoordinate).isTileOccupied() ) &&
                    (!board.getTile(this.position + this.getPieceColor().getDirection() * 8).isTileOccupied())){

                    legalMoves.add(new Move.MajorMove(board, this, destinationCoordinate));

            }

            //Pawn attacking diagonally
            else if (currentOffset == 7 &&
                    !((this.getPieceColor() == Color.WHITE && BoardUtils.EIGHTH_COLUMN[this.position]) ||
                    (this.getPieceColor() == Color.BLACK && BoardUtils.FIRST_COLUMN[this.position]))) {

                if(board.getTile(destinationCoordinate).isTileOccupied()) {
                    Piece pieceBeingAttacked = board.getTile(destinationCoordinate).getPiece();

                    if(this.getPieceColor() != pieceBeingAttacked.getPieceColor()) {
                        legalMoves.add(new Move.MajorMove(board, this, destinationCoordinate));
                    }


                }

            }

            else if (currentOffset == 9 &&
                    !((this.getPieceColor() == Color.WHITE && BoardUtils.FIRST_COLUMN[this.position]) ||
                    (this.getPieceColor() == Color.BLACK && BoardUtils.EIGHTH_COLUMN[this.position]))) {

                    if(board.getTile(destinationCoordinate).isTileOccupied()) {
                    Piece pieceBeingAttacked = board.getTile(destinationCoordinate).getPiece();

                        if(this.getPieceColor() != pieceBeingAttacked.getPieceColor()) {
                        legalMoves.add(new Move.MajorMove(board, this, destinationCoordinate));
                        }

                    }
            }

        }



        return Collections.unmodifiableList(legalMoves);
    }

    @Override
    public String toString() {
        return PieceType.PAWN.toString();
    }
}
