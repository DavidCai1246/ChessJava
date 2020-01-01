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

public class King extends Piece{
    public King(int position, Color pieceAlliance) {
        super(PieceType.KING, position, pieceAlliance);
    }

    private final static int[] OFFSETS = {8, -8, -1, 1, 7, 9, -7, -9};


    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (int currentOffset: OFFSETS) {

            int destinationCoordinate = this.position + currentOffset;

            if (BoardUtils.isValidTileCoordinate(destinationCoordinate)) {

                if(isEighthColumnExclusion(this.position, currentOffset) ||
                    isFirstColumnExclusion(this.position, currentOffset)){
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

    private static boolean isFirstColumnExclusion(final int currentPosition, int offset) {
        if (BoardUtils.FIRST_COLUMN[currentPosition] && ((offset == -9) || (offset == -1) || (offset == 7))) {
            return true;
        }
        return false;
    }
    private static boolean isEighthColumnExclusion(int currentPosition, int offset) {

        if (BoardUtils.EIGHTH_COLUMN[currentPosition] && ((offset == 9) || (offset == 1) || (offset == -7))) {
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return PieceType.KING.toString();
    }
}
