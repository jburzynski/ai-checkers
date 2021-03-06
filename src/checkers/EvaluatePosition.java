package checkers; // Ten pakiet jest wymagany - nie usuwaj go
public class EvaluatePosition // Ta klasa jest wymagana - nie usuwaj jej
{
    static private final int WIN=Integer.MAX_VALUE/2;
    static private final int LOSE=Integer.MIN_VALUE/2;
    static private boolean _color; // To pole jest wymagane - nie usuwaj go
    static public void changeColor(boolean color) // Ta metoda jest wymagana - nie zmieniaj jej
    {
        _color=color;
    }
    static public boolean getColor() // Ta metoda jest wymagana - nie zmieniaj jej
    {
        return _color;
    }
    static public int evaluatePosition(AIBoard board) // Ta metoda jest wymagana. Jest to główna metoda heurystyki - umieść swój kod tutaj
    {
        int myRating = 0;
        int opponentRating = 0;
        int size = board.getSize();

        for (int i = 0; i < size; i++) {
            for (int j = (i + 1) % 2; j < size; j += 2) {
                if (!board._board[i][j].empty) {

                    if (board._board[i][j].white == getColor()) {
                        myRating += calculateRating(board, getColor(), i, j);
                    } else {
                        opponentRating += calculateRating(board, !getColor(), i, j);
                    }

                }
            }
        }

        if (myRating == 0) {
            return LOSE;
        } else if (opponentRating == 0) {
            return WIN;
        } else {
            return myRating - opponentRating;
        }
    }

    private static int calculateRating(AIBoard board, boolean color, int i, int j) {
        int rating = 0;

        rating += calculateMaterialCriterion(board._board[i][j]);
        rating += calculateProgressCriterion(board, color, i, j);

        return rating;
    }

    private static int calculateMaterialCriterion(Piece piece) {
        return piece.king ? 15 : 5;
    }

    private static int calculateProgressCriterion(AIBoard board, boolean color, int i, int j) {
        if (board._board[i][j].king) {
            return 0;
        }
        int distance = color == Piece.WHITE ? board.getSize() - i : i;
        return 10 * distance;
    }

}
