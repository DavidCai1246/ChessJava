package com.chess.engine.pieces;

import com.chess.engine.Color;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;


public abstract class Piece {

    protected final PieceType pieceType;
    protected final int position;
    protected final Color pieceAlliance;
    protected final boolean isFirstMove;

    Piece(final PieceType pieceType, final int position, final Color pieceAlliance) {
        this.pieceType = pieceType;
        this.pieceAlliance = pieceAlliance;
        this.position = position;
        this.isFirstMove = false;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public int getPiecePosition() {
        return this.position;
    }

    public Color getPieceColor(){
        return this.pieceAlliance;
    }

    public PieceType getPieceType(){
        return this.pieceType;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

    public abstract Piece movePiece(Move move);

    @Override
    public boolean equals(final Object other){
        if(this == other) {
            return true;
        }
        if(!(other instanceof Piece)) {
            return false;
        }

        final Piece otherPiece = (Piece) other;

        return position == otherPiece.getPiecePosition() && pieceType == otherPiece.getPieceType() &&
                pieceAlliance == otherPiece.getPieceColor() && isFirstMove == otherPiece.isFirstMove();
    }

    @Override
    public int hashCode() {
        int result = pieceType.hashCode();
        result = 31 * result * pieceAlliance.hashCode();
        result = 31 * result * position;
        result = 31 * result * (isFirstMove ? 1: 0);
        return result;
    }


    public enum PieceType{

       PAWN("P") {
           @Override
           public boolean isKing() {
               return false;
           }

           @Override
           public boolean isRook() {
               return false;
           }
       },
       KNIGHT("N") {
           @Override
           public boolean isKing() {
               return false;
           }

           @Override
           public boolean isRook() {
               return false;
           }
       },
       BISHOP("B") {
           @Override
           public boolean isKing() {
               return false;
           }

           @Override
           public boolean isRook() {
               return false;
           }
       },
       ROOK("R") {
           @Override
           public boolean isKing() {
               return false;
           }

           @Override
           public boolean isRook() {
               return true;
           }
       },
       QUEEN("Q") {
           @Override
           public boolean isKing() {
               return false;
           }

           @Override
           public boolean isRook() {
               return false;
           }
       },
       KING("K"){
           @Override
           public boolean isKing() {
               return true;
           }

           @Override
           public boolean isRook() {
               return false;
           }
       };

       private String pieceName;

        PieceType(final String pieceName) {
            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }

        public abstract boolean isKing();

        public abstract boolean isRook();
    }


}
