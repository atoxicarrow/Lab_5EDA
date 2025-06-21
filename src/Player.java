public class Player {
    //Atributos
    String playerName;
    private int wins;
    private int draws;
    private int losses;

    //Metodos
    public Player(String playerName) {
        this.playerName = playerName;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
    }

    public void addWin() {
        wins++;
    }

    public void addDraw() {
        draws++;
    }

    public void addLoss() {
        losses++;
    }

    public float winRate() {
        float winRate;
        float total = wins + draws + losses;
        if (total == 0) {
            return 0;
        }

        winRate = wins / total;
        return winRate;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getLosses() {
        return losses;

    }
}
