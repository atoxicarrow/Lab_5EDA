import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        Scoreboard scoreboard = new Scoreboard();

        System.out.println("Conecta 4");
        System.out.print("Nomber Jugador 1 (X): ");
        String player1 = scanner.nextLine();
        scoreboard.registerPlayer(player1);

        System.out.print("Nombre Jugador 2 (O): ");
        String player2 = scanner.nextLine();
        scoreboard.registerPlayer(player2);

        boolean Jugar = true;
        while (Jugar) {
            Game game = new Game(player1, player2);
            String winner = game.play();

            if (game.getStatus().equals("VICTORY")) {
                String loser;
                if (winner.equals(player1)) {
                    loser = player2;
                } else {
                    loser = player1;
                }
                scoreboard.addGameResult(winner, loser, false);
            } else {
                scoreboard.addGameResult(player1, player2, true);
            }

            System.out.print("¿Jugar Denuevo? (si/no): ");
            String respuesta = scanner.nextLine();
            if (respuesta.equals("si")) {
                Jugar = true;
            } else {
                Jugar = false;
            }
        }

        System.out.println("Juego terminado");
        scanner.close();
    }

    }
