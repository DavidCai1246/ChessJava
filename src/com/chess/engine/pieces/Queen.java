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

public class Queen extends Piece{

    private final static int[] OFFSETS = {1, -1, 8, -8, -9, -7, 7, 9};

    public Queen(int position, Color pieceAlliance) {
        super(PieceType.QUEEN, position, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        int destinationCoordinate;

        for(final int currentOffset: OFFSETS) {

            destinationCoordinate = this.position;

            while(BoardUtils.isValidTileCoordinate(destinationCoordinate)) {

                if(isEighthColumnExclusion(this.position, currentOffset) ||
                        isFirstColumnExclusion(this.position, currentOffset)) {
                    break;
                }

                destinationCoordinate += currentOffset;

                if(BoardUtils.isValidTileCoordinate(destinationCoordinate)) {

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
                        break;

                    }

                }

            }

        }

        return Collections.unmodifiableList(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, int offset) {

        if (BoardUtils.FIRST_COLUMN[currentPosition] && ((offset == -1) || ((offset == -9) || (offset == -7)))) {
            return true;
        }
        return false;
    }

    private static boolean isEighthColumnExclusion(int currentPosition, int offset) {

        if (BoardUtils.EIGHTH_COLUMN[currentPosition] && ((offset == 1)) || (offset == -7) || (offset == 9)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return PieceType.QUEEN.toString();
    }

    @Override
    public Queen movePiece(Move move) {
        return new Queen(move.getDestinationCoordinate(), move.getMovedPiece().getPieceColor());
    }

}
