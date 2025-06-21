import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    BST<Integer, String> winTree;
    HashST<String, Player> players;
    int playedGames;

    public Scoreboard() {
        this.winTree = new BST<>();
        this.players = new HashST<>();
        this.playedGames = 0;
    }

    public void addGameResult(String winnerPlayerName, String looserPlayerName, boolean draw) {
        playedGames++;

        if (draw) {
            Player playerA = players.get(winnerPlayerName);
            Player playerB = players.get(looserPlayerName);

            if (playerA != null) {
                // Eliminar del árbol con las victorias antiguas
                winTree.delete(playerA.getWins(), playerA.getPlayerName());
                playerA.addDraw();
                // Agregar al árbol con las nuevas victorias
                winTree.put(playerA.getWins(), playerA.getPlayerName());
            }

            if (playerB != null) {
                winTree.delete(playerB.getWins(), playerB.getPlayerName());
                playerB.addDraw();
                winTree.put(playerB.getWins(), playerB.getPlayerName());
            }
        } else {
            Player winner = players.get(winnerPlayerName);
            Player looser = players.get(looserPlayerName);

            if (winner != null) {
                winTree.delete(winner.getWins(), winner.getPlayerName());
                winner.addWin();
                winTree.put(winner.getWins(), winner.getPlayerName());
            }

            if (looser != null) {
                winTree.delete(looser.getWins(), looser.getPlayerName());
                looser.addLoss();
                winTree.put(looser.getWins(), looser.getPlayerName());
            }
        }
    }

    public void registerPlayer(String playerName) {
        if (!players.contains(playerName)) {
            Player newPlayer = new Player(playerName);
            players.put(playerName, newPlayer);
            winTree.put(0, playerName);
        }
    }

    public boolean checkPlayer(String playerName) {
        return players.contains(playerName);
    }

    public Player[] winRange(int lo, int hi) {
        List<Player> result = new ArrayList<>();

        for (Integer wins : winTree.keys()) {
            if (wins >= lo && wins <= hi) {
                for (String name : winTree.get(wins)) {
                    result.add(players.get(name));
                }
            }
        }

        return result.toArray(new Player[0]);
    }

    public Player[] winSuccessor(int wins) {
        Integer successorKey = winTree.ceiling(wins + 1);

        if (successorKey == null) {
            return new Player[0];
        }

        List<Player> result = new ArrayList<>();
        for (String name : winTree.get(successorKey)) {
            result.add(players.get(name));
        }

        return result.toArray(new Player[0]);
    }

    public int getPlayedGames() {
        return playedGames;
    }
}