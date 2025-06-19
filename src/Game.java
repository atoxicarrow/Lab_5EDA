import java.util.Scanner;

public class Game {
    String status;
    String winnerPlayerName;
    String playerNameA;
    String playerNameB;
    ConnectFour ConnectFour;



    Game(String playerNameA, String playerNameB){
        this.playerNameA = playerNameA;
        this.playerNameB = playerNameB;
        ConnectFour = new ConnectFour();
        status = "IN_PROGRRES";
        winnerPlayerName = "";
    }
    private void mostrarTablero() {
        for (int Fila = 5; Fila >= 0; Fila--) {
            for (int Columna = 0; Columna < 7; Columna++) {
                System.out.print(ConnectFour.grid[Columna][Fila] + " ");
            }
            System.out.println();
        }
    }

    public String play(){
        while(status.equals("IN_PROGRRES")){

            Scanner in = new Scanner(System.in);
            char currentSymbol = ConnectFour.getCurrentSymbol();
            String currentPlayer;
            if (currentSymbol == 'X') {
                currentPlayer = playerNameA;
            } else {
                currentPlayer = playerNameB;
            }
            ConnectFour.ImprimirTablero();
            System.out.print(currentPlayer + " (" + currentSymbol + "), ingrese columna (0-6): ");
            int Columna = in.nextInt();
            if (!ConnectFour.makeMove(Columna)) {
                System.out.println("Movimiento inválido. Intente de nuevo.");
                continue;
            }

            int gameResult = ConnectFour.isGameOver();
            if (gameResult == 1) {
                status = "VICTORY";
                winnerPlayerName = playerNameA;
            }
            if (gameResult == 2) {
                status = "VICTORY";
                winnerPlayerName = playerNameB;
            }
            if (gameResult == 3) {
                status = "DRAW";
                winnerPlayerName = "";
            }
        }

        // Mostrar resultado final
        if (status.equals("VICTORY")) {
            System.out.println("¡Ganador: " + winnerPlayerName + "!");
            ConnectFour.ImprimirTablero();
        } else {
            System.out.println("¡Empate!");
            ConnectFour.ImprimirTablero();
        }

        return winnerPlayerName;
    }
    public String getStatus() {
        return status;
    }

    public String getWinnerPlayerName() {
        return winnerPlayerName;
    }
}




