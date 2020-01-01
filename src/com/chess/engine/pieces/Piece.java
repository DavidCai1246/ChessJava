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

    public enum PieceType{

       PAWN("P") {
           @Override
           public boolean isKing() {
               return false;
           }
       },
       KNIGHT("N") {
           @Override
           public boolean isKing() {
               return true;
           }
       },
       BISHOP("B") {
           @Override
           public boolean isKing() {
               return false;
           }
       },
       ROOK("R") {
           @Override
           public boolean isKing() {
               return false;
           }
       },
       QUEEN("Q") {
           @Override
           public boolean isKing() {
               return false;
           }
       },
       KING("K"){
           @Override
           public boolean isKing() {
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

    }


}
