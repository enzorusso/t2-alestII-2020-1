import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        File file = new File("casoa.txt");
        long initialTime = System.currentTimeMillis(); //comeco do algoritmo

        Labirinto labirinto = new Labirinto(file);
        GrafoBFS bfs = new GrafoBFS();
        bfs.caminhaLabirinto(labirinto);
        System.out.println("Largura: " + labirinto.getWidth() + " x Altura: " + labirinto.getHeight());
        System.out.println("Movimentos BFS: " + bfs.getCountMov());

        long finalTime = System.currentTimeMillis();
        long duration = finalTime-initialTime;
        System.out.println("Tempo de execução: " +duration+ "ms" + " ou " + (duration/1000.) + "seg");
    }
}