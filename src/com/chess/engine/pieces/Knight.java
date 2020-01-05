package com.chess.engine.pieces;

import com.chess.engine.Color;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Knight extends Piece {

    private final static int[] OFFSETS = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(final int position, final Color pieceAlliance) {
        super(PieceType.KNIGHT, position, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        int destinationCoordinate;
        List<Move> legalMoves = new ArrayList<>();

        for(final int currentOffset: OFFSETS) {

            destinationCoordinate = this.position + currentOffset;

            if(BoardUtils.isValidTileCoordinate(destinationCoordinate)) {

                if(isFirstColumnExclusion(this.position, currentOffset)
                        || isSecondColumnExclusion(this.position, currentOffset)
                        || isSeventhColumnExclusion(this.position, currentOffset)
                        || isEighthColumnExclusion(this.position, currentOffset)) {
                    continue;
                }

                final Tile destinationTile = board.getTile(destinationCoordinate);

                if(!destinationTile.isTileOccupied()) {
                    legalMoves.add(new Move.MajorMove(board, this, destinationCoordinate));
                }
                else {

                    final Piece pieceAtDestination = destinationTile.getPiece();
                    final Color pieceColor = pieceAtDestination.getPieceColor();

                    if(this.pieceAlliance != pieceColor) {
                        legalMoves.add(new Move.AttackMove(board,this, destinationCoordinate, pieceAtDestination));
                    }

                }

            }

        }

        return Collections.unmodifiableList(legalMoves);
    }

    private static boolean isFirstColumnExclusion(int currentPosition, int offset) {

        if (BoardUtils.FIRST_COLUMN[currentPosition] && ((offset == -17) || (offset == -10) || (offset == 6) || (offset == 15))) {
            return true;
        }
        return false;
    }

    private static boolean isSecondColumnExclusion(int currentPosition, int offset) {

        if (BoardUtils.SECOND_COLUMN[currentPosition] && ((offset == -10) || (offset == 6))) {
            return true;
        }
        return false;
    }

    private static boolean isSeventhColumnExclusion(int currentPosition, int offset) {

        if (BoardUtils.SEVENTH_COLUMN[currentPosition] && ((offset == -6) || (offset == 10))) {
            return true;
        }
        return false;
    }

    private static boolean isEighthColumnExclusion(int currentPosition, int offset) {

        if (BoardUtils.EIGHTH_COLUMN[currentPosition] && ((offset == -15) || (offset == -6) || (offset == 10) || (offset == 17))) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return PieceType.KNIGHT.toString();
    }

    @Override
    public Knight movePiece(Move move) {
        return new Knight(move.getDestinationCoordinate(), move.getMovedPiece().getPieceColor());
    }


}
