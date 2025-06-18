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

    public void play(){
        while(status.equals("IN_PROGRRES")){
            Scanner in = new Scanner(System.in);
            System.out.println("Ingresa nombre del Jugador 1: ");
            playerNameA = in.nextLine();
            System.out.println("Ingresa nombre del Jugador 2: ");
            playerNameB = in.nextLine();

            System.out.println(playerNameA + " : X |" + playerNameB + ": O");

        }
    }
}
