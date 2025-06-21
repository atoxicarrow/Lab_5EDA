import java.util.Scanner;

public class Game {
    private String status;
    private String winnerPlayerName;
    private final String playerNameA;
    private final String playerNameB;
    ConnectFour ConnectFour;
    Scoreboard scoreboard;

    Game(String playerNameA, String playerNameB, Scoreboard scoreboard) {
        this.playerNameA = playerNameA;
        this.playerNameB = playerNameB;
        this.ConnectFour = new ConnectFour();
        this.status = "IN_PROGRESS";
        this.winnerPlayerName = "";
        this.scoreboard = scoreboard;

        if (!scoreboard.checkPlayer(playerNameA)) {
            scoreboard.registerPlayer(playerNameA);
        }
        if (!scoreboard.checkPlayer(playerNameB)) {
            scoreboard.registerPlayer(playerNameB);
        }
    }

    private void mostrarTablero() {
        for (int Fila = 5; Fila >= 0; Fila--) {
            for (int Columna = 0; Columna < 7; Columna++) {
                System.out.print(ConnectFour.grid[Columna][Fila] + " ");
            }
            System.out.println();
        }
    }

    private void mostrarEstadisticas() {
        Player jugadorA = scoreboard.players.get(playerNameA);
        Player jugadorB = scoreboard.players.get(playerNameB);

        System.out.println("\n=== ESTADÍSTICAS DE JUGADORES ===");
        System.out.println("Jugador\t\tVictorias\tEmpates\tDerrotas\t% Victorias");
        System.out.printf("%s\t%d\t\t%d\t%d\t\t%.2f%%\n",
                jugadorA.getPlayerName(),
                jugadorA.getWins(),
                jugadorA.getDraws(),
                jugadorA.getLosses(),
                jugadorA.winRate() * 100);

        System.out.printf("%s\t%d\t\t%d\t%d\t\t%.2f%%\n",
                jugadorB.getPlayerName(),
                jugadorB.getWins(),
                jugadorB.getDraws(),
                jugadorB.getLosses(),
                jugadorB.winRate() * 100);
    }

    public String play() {
        while(status.equals("IN_PROGRESS")) {
            Scanner in = new Scanner(System.in);
            char currentSymbol = ConnectFour.getCurrentSymbol();
            String currentPlayer = (currentSymbol == 'X') ? playerNameA : playerNameB;

            ConnectFour.ImprimirTablero();
            System.out.print(currentPlayer + " (" + currentSymbol + "), ingrese columna (0-6) o 'estadisticas': ");
            String input = in.nextLine();

            if (input.equalsIgnoreCase("estadisticas")) {
                mostrarEstadisticas();
                continue;
            }

            try {
                int Columna = Integer.parseInt(input);
                if (!ConnectFour.makeMove(Columna)) {
                    System.out.println("Movimiento inválido. Intente de nuevo.");
                    continue;
                }

                int gameResult = ConnectFour.isGameOver();
                if (gameResult == 1) {
                    status = "VICTORY";
                    winnerPlayerName = playerNameA;
                } else if (gameResult == 2) {
                    status = "VICTORY";
                    winnerPlayerName = playerNameB;
                } else if (gameResult == 3) {
                    status = "DRAW";
                    winnerPlayerName = "";
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intente de nuevo.");
            }
        }

        if (status.equals("VICTORY")) {
            String perdedor = winnerPlayerName.equals(playerNameA) ? playerNameB : playerNameA;
            scoreboard.addGameResult(winnerPlayerName, perdedor, false);
            System.out.println("¡Ganador: " + winnerPlayerName + "!");
        } else {
            scoreboard.addGameResult(playerNameA, playerNameB, true);
            System.out.println("¡Empate!");
        }

        ConnectFour.ImprimirTablero();
        mostrarEstadisticas();

        return winnerPlayerName;
    }

    public String getStatus() {
        return status;
    }

    public String getWinnerPlayerName() {
        return winnerPlayerName;
    }
}