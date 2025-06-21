import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scoreboard scoreboard = new Scoreboard();

        System.out.println("=== CONECTA 4 ===");

        System.out.print("Nombre Jugador 1 (X): ");
        String player1 = scanner.nextLine();
        scoreboard.registerPlayer(player1);

        System.out.print("Nombre Jugador 2 (O): ");
        String player2 = scanner.nextLine();
        scoreboard.registerPlayer(player2);

        boolean jugarDeNuevo = true;
        while (jugarDeNuevo) {
            mostrarEstadisticas(scoreboard, player1, player2);

            Game game = new Game(player1, player2, scoreboard);
            String winner = game.play();

            System.out.print("\n¿Jugar de nuevo? (si/no): ");
            String respuesta = scanner.nextLine().toLowerCase();
            jugarDeNuevo = respuesta.equals("si") || respuesta.equals("sí");

            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        System.out.println("\n=== ESTADÍSTICAS FINALES ===");
        mostrarEstadisticas(scoreboard, player1, player2);
        System.out.println("\n¡Gracias por jugar Conecta 4!");
        scanner.close();
    }

    private static void mostrarEstadisticas(Scoreboard scoreboard, String player1, String player2) {
        Player jugador1 = scoreboard.players.get(player1);
        Player jugador2 = scoreboard.players.get(player2);

        System.out.println("\n=== ESTADÍSTICAS ===");
        System.out.println("Partidas jugadas: " + scoreboard.getPlayedGames());
        System.out.println("\nJugador\t\tVictorias\tEmpates\tDerrotas\t% Victorias");

        System.out.printf("%s\t%d\t\t%d\t%d\t\t%.2f%%\n",
                jugador1.getPlayerName(),
                jugador1.getWins(),
                jugador1.getDraws(),
                jugador1.getLosses(),
                jugador1.winRate() * 100);

        System.out.printf("%s\t%d\t\t%d\t%d\t\t%.2f%%\n",
                jugador2.getPlayerName(),
                jugador2.getWins(),
                jugador2.getDraws(),
                jugador2.getLosses(),
                jugador2.winRate() * 100);

        Player[] ranking = scoreboard.winRange(0, Integer.MAX_VALUE);
        if (ranking.length > 0) {
            System.out.println("\nRanking:");
            for (int i = 0; i < ranking.length; i++) {
                System.out.printf("%d. %s - %d victorias\n",
                        i+1, ranking[i].getPlayerName(), ranking[i].getWins());
            }
        }
    }
}