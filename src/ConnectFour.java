public class ConnectFour {
    //Atributos
    char[][] grid = new char[7][6];
    char currentSymbol;

    //metodos;
    ConnectFour(){
        for(int i =0; i<7; i++){
            for(int j=0; j<6; j++){
                grid[i][j] = ' ';
            }
        }

        this.currentSymbol = 'X';
    }

    boolean makeMove(int z){
        if (z<0 || z>7){  // verifica si el usuario selecciono
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
        return true;
    }
    public int isGameOver(){
        //verificar si hay 4 fichas iguales horizontalmente
        for (int Filas = 0; Filas < 6; Filas++){
            for (int Columnas = 0; Columnas < 4; Columnas++){
                if (grid[Filas][Columnas] == ' ' && grid[Filas][Columnas+1] == ' ' && grid[Filas+2][Columnas]   == ' ' && grid[Filas+3][Columnas] == ' ' ){
                    if (grid[Columnas][Filas] == 'X') {
                        return 1;  // Gana X
                    } else {
                        return 2;  // Gana O
                    }
                }
            }
        }
        //verifica iguales verticalmente
        for (int Columnas = 0; Columnas < 6; Columnas++){
            for (int Filas = 0; Filas < 4; Filas++){
                if (grid [Columnas][Filas] == ' ' && grid [Columnas][Filas+1] == ' ' && grid [Columnas][Filas+2] == ' ' && grid [Columnas][Filas+3] == ' '){
                    if (grid[Columnas][Filas] == 'X') {
                        return 1;  // Gana X
                    } else {
                        return 2;  // Gana O
                    }
                }
            }
        }

        for (int Columnas = 0; Columnas < 4; Columnas++) {
            for (int Filas = 0; Filas < 3; Filas++) {
                if (grid[Columnas][Filas] != ' ' &&
                        grid[Columnas][Filas] == grid[Columnas+1][Filas+1] &&
                        grid[Columnas][Filas] == grid[Columnas+2][Filas+2] &&
                        grid[Columnas][Filas] == grid[Columnas+3][Filas+3]) {
                    if (grid[Columnas][Filas] == 'X') {
                        return 1;  // Gana X
                    } else {
                        return 2;  // Gana O
                    }
                }
            }
        }

        for (int Columnas = 0; Columnas < 4; Columnas++) {
            for (int Filas = 3; Filas < 6; Filas++) {
                if (grid[Columnas][Filas] != ' ' &&
                        grid[Columnas][Filas] == grid[Columnas+1][Filas-1] &&
                        grid[Columnas][Filas] == grid[Columnas+2][Filas-2] &&
                        grid[Columnas][Filas] == grid[Columnas+3][Filas-3]) {
                    if (grid[Columnas][Filas] == 'X') {
                        return 1;  // Gana X
                    } else {
                        return 2;  // Gana O
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

} //test gdeithub
