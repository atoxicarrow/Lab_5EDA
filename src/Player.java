public class Player {
    //Atributos
    String playerName;
    int wins;
    int draws;
    int losses;

    //Metodos
    public void addWin(){
        wins++;
    }

    public void addDraw(){
        draws++;
    }
    public void addLoss(){
        losses++;
    }

    public float winRate(){
        float winRate;
        float total = wins + draws + losses;

        if(total == 0){
            return 0;
        }

        winRate = wins/total;

        return winRate;
    }

}
