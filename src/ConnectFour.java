public class ConnectFour {
    //Atributos
    char[][] grid = new char[7][6];
    private char currentSymbol;

    //metodos;
    public ConnectFour(){
        for(int i =0; i<7; i++){
            for(int j=0; j<6; j++){
                grid[i][j] = ' ';
            }
        }

        this.currentSymbol = 'X';
    }

    public boolean makeMove(int z){
        if (z<0 || z>=7){  // verifica si el usuario selecciono
            // una columna entre 0 y 6, porque el juego ql tiene 6 colunnas nomas
            return false;
        }

        for (int i = 0; i < 6; i++) {
            if (grid[z][i] == ' '){   //busca el primer slot vacio de la columna dada por el user.
                grid[z][i] = currentSymbol;
                //swapea el simbolo actual al contrario
                if (currentSymbol == 'X'){
                    currentSymbol = 'O';
                }
                else if (currentSymbol == 'O') {
                    currentSymbol = 'X';
                }
                return true;
            }
        }
        return false;
    }
    public int isGameOver() {
        // }horizontal
        for (int Fila = 0; Fila < 6; Fila++) {
            for (int Columna = 0; Columna < 4; Columna++) {
                if (grid[Columna][Fila] != ' ' && grid[Columna][Fila] == grid[Columna+1][Fila] && grid[Columna][Fila] == grid[Columna+2][Fila] && grid[Columna][Fila] == grid[Columna+3][Fila]) {
                    if (grid[Columna][Fila] == 'X') {
                        return 1;  // Gana X
                    }
                    if (grid[Columna][Fila] == 'O') {
                        return 2;  // Gana O
                    }
                }
            }
        }

        // verticalmente
        for (int Columna = 0; Columna < 7; Columna++) {
            for (int Fila = 0; Fila < 3; Fila++) {
                if (grid[Columna][Fila] != ' ' && grid[Columna][Fila] == grid[Columna][Fila+1] && grid[Columna][Fila] == grid[Columna][Fila+2] && grid[Columna][Fila] == grid[Columna][Fila+3]) {
                    if (grid[Columna][Fila] == 'X') {
                        return 1;
                    }
                    if (grid[Columna][Fila] == 'O') {
                        return 2;
                    }
                }
            }
        }

        // diagonal \
        for (int Columna = 0; Columna < 4; Columna++) {
            for (int Fila = 0; Fila < 3; Fila++) {
                if (grid[Columna][Fila] != ' ' && grid[Columna][Fila] == grid[Columna+1][Fila+1] && grid[Columna][Fila] == grid[Columna+2][Fila+2] && grid[Columna][Fila] == grid[Columna+3][Fila+3]) {
                    if (grid[Columna][Fila] == 'X') {
                        return 1;
                    }
                    if (grid[Columna][Fila] == 'O') {
                        return 2;
                    }
                }
            }
        }

        // diagonal /
        for (int Columna = 3; Columna < 7; Columna++) {
            for (int Fila = 0; Fila < 3; Fila++) {
                if (grid[Columna][Fila] != ' ' && grid[Columna][Fila] == grid[Columna-1][Fila+1] && grid[Columna][Fila] == grid[Columna-2][Fila+2] && grid[Columna][Fila] == grid[Columna-3][Fila+3]) {
                    if (grid[Columna][Fila] == 'X') {
                        return 1;
                    }
                    if (grid[Columna][Fila] == 'O') {
                        return 2;
                    }
                }
            }
        }


        //verifica empate
        boolean isFull = true;
        for (int col = 0; col < 7; col++) {
            if (grid[col][5] == ' ') {
                isFull = false;
                break;
            }
        }
        if (isFull) {
            return 3;
        }
        return 0;
    }
    public char getCurrentSymbol() {
        return currentSymbol;
    }
    public void ImprimirTablero() {
        System.out.println("/0/1/2/3/4/5/6/");

        for (int Fila = 5; Fila >= 0; Fila--) {
            System.out.print("|");
            for (int Columna = 0; Columna < 7; Columna++) {
                System.out.print(grid[Columna][Fila] + "|");
            }
            System.out.println();
        }
        System.out.println("---------------");
    }
}

