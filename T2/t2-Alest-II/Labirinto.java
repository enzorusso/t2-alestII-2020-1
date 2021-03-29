import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Labirinto {
    private char ESPACO = '.';
    private char WALL = '#';
    private char HEROI = 'A';
    private char VILAO = 'B';

    private char[][] labirinto;
    private boolean[][] visited;
    private Coordenadas heroiPos;
    private Coordenadas vilaoPos;

    public Labirinto(File labirinto) throws FileNotFoundException {
        String file = new String();
        try (Scanner input = new Scanner(labirinto)) {
            while (input.hasNextLine()) {
                file += input.nextLine() + "\n";
            }
            input.close();
        }
        iniciaLabirinto(file);
    }

    private void iniciaLabirinto(String txt) {
        if (txt == null || (txt = txt.trim()).length() == 0) {
            throw new IllegalArgumentException("empty lines data");
        }
        String[] lines = txt.split("\n");
        labirinto = new char[lines.length][lines[0].length()];
        visited = new boolean[lines.length][lines[0].length()];
        for (int linha = 0; linha < getHeight(); linha++) {
            if (lines[linha].length() != getWidth()) {
                throw new IllegalArgumentException("line " + (linha + 1) + " wrong length (was " + lines[linha].length() + " but should be " + getWidth() + ")");
            }
            for (int coluna = 0; coluna < getWidth(); coluna++) {
                if (lines[linha].charAt(coluna) == '#')
                    labirinto[linha][coluna] = WALL;
                else if (lines[linha].charAt(coluna) == 'A') {
                    labirinto[linha][coluna] = HEROI;
                    heroiPos = new Coordenadas(linha, coluna);
                } else if (lines[linha].charAt(coluna) == 'B') {
                    labirinto[linha][coluna] = VILAO;
                    vilaoPos = new Coordenadas(linha, coluna);
                } else
                    labirinto[linha][coluna] = ESPACO; // == '.'
            }
        }
    }

    public int getHeight() { return labirinto.length; }

    public int getWidth() { return labirinto[0].length; }

    public Coordenadas getHeroiPos() { return heroiPos; }

    public Coordenadas getVilaoPos() { return vilaoPos; }

    public boolean isVilaoPos(int x, int y) { return x == vilaoPos.getX() && y == vilaoPos.getY(); }

    public boolean isHeroiPos(int x, int y) { return x == heroiPos.getX() && y == heroiPos.getY(); }

    public boolean isExplored(int linha, int coluna) { return visited[linha][coluna]; }

    public boolean isWall(int linha, int coluna) { return labirinto[linha][coluna] == WALL; }

    public void setVisited(int linha, int coluna, boolean valor) { visited[linha][coluna] = valor; }

    public boolean isValidLocation(int linha, int coluna) {
        if (linha < 0 || linha >= getHeight() || coluna < 0 || coluna >= getWidth()) return false;
        return true;
    }
}



    // public void printPath(List<Coordenadas> path) {
    //     int[][] labTemp = Arrays.stream(labirinto)
    //         .map(int[]::clone)
    //         .toArray(int[][]::new);
    //     for (Coordenadas coord : path) {
    //         if (isHeroiPos(coord.getX(), coord.getY()) || isVilaoPos(coord.getX(), coord.getY())) {
    //             continue;
    //         }
    //         labTemp[coord.getX()][coord.getY()] = CAMINHO;
    //     }
    //     System.out.println(toString(labTemp));
    // }

    // public String toString(int[][] labirinto) {
    //     StringBuilder result = new StringBuilder(getWidth() * (getHeight() + 1));
    //     for (int linha = 0; linha < getHeight(); linha++) {
    //         for (int coluna = 0; coluna < getWidth(); coluna++) {
    //             if (labirinto[linha][coluna] == CAMINHO) {
    //                 result.append(' ');
    //             } else if (labirinto[linha][coluna] == WALL) {
    //                 result.append('#');
    //             } else if (labirinto[linha][coluna] == HEROI) {
    //                 result.append('A');
    //             } else if (labirinto[linha][coluna] == VILAO) {
    //                 result.append('B');
    //             } else {
    //                 result.append('.'); //ESPACO
    //             }
    //         }
    //         result.append('\n');
    //     }
    //     return result.toString();
    // }

    // public void reset() {
    //     for (int i = 0; i < visited.length; i++)
    //         Arrays.fill(visited[i], false);
    // }
// }