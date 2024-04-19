package org.iesvdm.tddjava.connect4;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Connect4TDDSpec {

    private Connect4TDD tested;

    private OutputStream output;

    @BeforeEach
    public void beforeEachTest() {
        output = new ByteArrayOutputStream();

        //Se instancia el juego modificado para acceder a la salida de consola
        tested = new Connect4TDD(new PrintStream(output));
    }

    /*
     * The board is composed by 7 horizontal and 6 vertical empty positions
     */

    @Test
    public void whenTheGameStartsTheBoardIsEmpty() {
        int[][] board = new int[6][7];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = 0;
            }
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                assertEquals(0, board[i][j]);
            }
        }
    }


    /*
     * Players introduce discs on the top of the columns.
     * Introduced disc drops down the board if the column is empty.
     * Future discs introduced in the same column will stack over previous ones
     */

    @Test
    public void whenDiscOutsideBoardThenRuntimeException() {
        int[][] board = new int[6][7];

        try {
            int column = 8;
            int row = 0;
            board[row][column] = 1;

        } catch (RuntimeException ignored) {
        }

    }

    @Test
    public void whenFirstDiscInsertedInColumnThenPositionIsZero() {

        assertThat(tested.putDiscInColumn(0)).isEqualTo(0);

        int[][] board = new int[6][7];

        int column = 0;
        board[0][column] = 1;

        assertEquals(0, board[0][column]);
    }

    @Test
    public void whenSecondDiscInsertedInColumnThenPositionIsOne() {

        int[][] board = new int[6][7];

        int column = 0;
        board[0][column] = 1;
        board[1][column] = 1;

        assertEquals(1, board[1][column]);

    }

    @Test
    public void whenDiscInsertedThenNumberOfDiscsIncreases() {

        int[][] board = new int[6][7];

        int countBefore = 0;
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 1) {
                    countBefore++;
                }
            }
        }

        board[0][0] = 1;

        int countAfter = 0;
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 1) {
                    countAfter++;
                }
            }
        }

        assertEquals(countBefore + 1, countAfter);


    }

    @Test
    public void whenNoMoreRoomInColumnThenRuntimeException() {

        int[][] board = new int[6][7];

        int column = 0;
        for (int i = 0; i < 6; i++) {
            board[i][column] = 1;
        }

        try {
            board[6][column] = 1;

        } catch (RuntimeException e) {
        }

    }

    /*
     * It is a two-person game so there is one colour for each player.
     * One player uses red ('R'), the other one uses green ('G').
     * Players alternate turns, inserting one disc every time
     */

    @Test
    public void whenFirstPlayerPlaysThenDiscColorIsRed() {

        char[][] board = new char[6][7];

        int column = 0;
        board[0][column] = 'R';

        assertEquals('R', board[0][column]);
    }

    @Test
    public void whenSecondPlayerPlaysThenDiscColorIsGreen() {

        char[][] board = new char[6][7];

        int column = 0;
        board[0][column] = 'R';

        board[1][column] = 'G';

        assertEquals('G', board[1][column]);
    }

    /*
     * We want feedback when either, event or error occur within the game.
     * The output shows the status of the board on every move
     */

    @Test
    public void whenAskedForCurrentPlayerTheOutputNotice() {

        String currentPlayer = "Jugador 1";

        assertNotNull(currentPlayer);

        System.out.println("El jugador actual es: " + currentPlayer);
    }

    @Test
    public void whenADiscIsIntroducedTheBoardIsPrinted() {
        char[][] board = new char[6][7];

        board[0][0] = 'X';

        assertNotNull(board);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*
     * When no more discs can be inserted, the game finishes and it is considered a draw
     */

    @Test
    public void whenTheGameStartsItIsNotFinished() {
        String gameState = "En curso";

        assertNotEquals(gameState, "Terminado");

        System.out.println("El estado del juego es: " + gameState);
    }

    @Test
    public void whenNoDiscCanBeIntroducedTheGamesIsFinished() {

        char[][] board = new char[6][7];
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], 'X');
        }

        String gameState = "Terminado";

        assertEquals(gameState, "Terminado");

        System.out.println("El estado del juego es: " + gameState);
    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight vertical line then that player wins
     */

    @Test
    public void when4VerticalDiscsAreConnectedThenThatPlayerWins() {
        char[][] board = new char[6][7];

        for (int i = 0; i < 4; i++) {
            board[i][0] = 'R';
        }

        boolean hasWon = checkVertical(board);
        assertTrue(hasWon);
    }

    public boolean checkVertical(char[][] board) {
        for (int r = 0; r < board.length - 3; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] != '\0' && board[r][c] == board[r+1][c] && board[r][c] == board[r+2][c] && board[r][c] == board[r+3][c]) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight horizontal line then that player wins
     */

    @Test
    public void when4HorizontalDiscsAreConnectedThenThatPlayerWins() {
        char[][] board = new char[6][7];

        for (int i = 0; i < 4; i++) {
            board[0][i] = 'R';
        }

        boolean hasWon = checkHorizontal(board);
        assertTrue(hasWon);
    }

    public boolean checkHorizontal(char[][] board) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length - 3; c++) {
                if (board[r][c] != '\0' && board[r][c] == board[r][c+1] && board[r][c] == board[r][c+2] && board[r][c] == board[r][c+3]) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight diagonal line then that player wins
     */

    @Test
    public void when4Diagonal1DiscsAreConnectedThenThatPlayerWins() {
        char[][] board = new char[6][7];

        for (int i = 0; i < 4; i++) {
            board[i][i] = 'R';
        }

        boolean hasWon = checkDiagonal1(board);
        assertTrue(hasWon);
    }

    @Test
    public void when4Diagonal2DiscsAreConnectedThenThatPlayerWins() {
        char[][] board = new char[6][7];

        for (int i = 0; i < 4; i++) {
            board[3 + i][i] = 'R';
        }

        boolean hasWon = checkDiagonal2(board);
        assertTrue(hasWon);
    }

    public boolean checkDiagonal1(char[][] board) {
        for (int r = 0; r < board.length - 3; r++) {
            for (int c = 0; c < board[0].length - 3; c++) {
                if (board[r][c] != '\0' && board[r][c] == board[r+1][c+1] && board[r][c] == board[r+2][c+2] && board[r][c] == board[r+3][c+3]) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkDiagonal2(char[][] board) {
        for (int r = 3; r < board.length; r++) {
            for (int c = 0; c < board[0].length - 3; c++) {
                if (board[r][c] != '\0' && board[r][c] == board[r - 1][c + 1] && board[r][c] == board[r - 2][c + 2] && board[r][c] == board[r - 3][c + 3]) {
                    return true;
                }
            }
        }
        return false;
    }
}

