package com.chess.engine.board;

public class BoardUtils {

    public static final boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHTH_COLUMN = initColumn(7);

    public static final boolean[] SECOND_ROW = initRow(8);
    public static final boolean[] SEVENTH_ROW = initRow(48);

    public static final int NUM_TILES = 64;

    private static boolean[] initRow(int rowNumber) {

        final boolean[] row = new boolean [NUM_TILES];

        do{

            row[rowNumber] = true;
            rowNumber++;

        }while(rowNumber % 8 != 0);

        return row;

    }

    private static boolean[] initColumn(int colNumber) {

        final boolean[] col = new boolean [NUM_TILES];

        //initializing the col array to be all false
        for(int i = 0; i < NUM_TILES; i++) {
            col[i] = false;
        }

        //setting the specific col of the array to be true
        do{
            col[colNumber] = true;
            colNumber += 8;
        }

        while(colNumber < NUM_TILES);

        return col;
    }

    private BoardUtils() {
        throw new RuntimeException("You cannot initiate this class.");
    }

    //check to see  if the coordinate is on the board
    public static boolean isValidTileCoordinate(int coordinate) {
        return coordinate >= 0 && coordinate < NUM_TILES;
    }
}
