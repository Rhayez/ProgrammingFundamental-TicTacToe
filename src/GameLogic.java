public class GameLogic {

    private String[] board;

    public GameLogic() {

        board = new String[9];

        for (int i = 0; i < 9; i++) {

            board[i] = "";

        }

    }

    public boolean makeMove(
            int index,
            String symbol) {

        if (!board[index].equals("")) {

            return false;

        }

        board[index] = symbol;

        return true;
    }

    public String[] getBoard() {

        return board;

    }

    public boolean isDraw() {

        for (String cell : board) {

            if (cell.equals("")) {

                return false;

            }

        }

        return true;
    }

    public boolean checkWinner(
            String symbol) {

        int[][] winPatterns = {

                {0,1,2},
                {3,4,5},
                {6,7,8},

                {0,3,6},
                {1,4,7},
                {2,5,8},

                {0,4,8},
                {2,4,6}

        };

        for (int[] pattern : winPatterns) {

            if (

                    board[pattern[0]].equals(symbol)
                    &&
                    board[pattern[1]].equals(symbol)
                    &&
                    board[pattern[2]].equals(symbol)

            ) {

                return true;

            }

        }

        return false;
    }
}