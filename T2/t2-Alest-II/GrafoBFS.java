public class GrafoBFS { //busca em largura
    private static final int[][] DIRECOES = {{ 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }};
    private static int countMov;

    public Queue<Coordenadas> caminhaLabirinto(Labirinto labirinto) {
        Queue<Coordenadas> nextToVisit = new Queue<>(); //fila de coordenadas a visitar
        Coordenadas heroi = labirinto.getHeroiPos(); //inicio do caminhamento (pos do heroi)
        nextToVisit.enqueue(heroi); //adiciona o heroi a fila do proximos a visitar

        while (!nextToVisit.isEmpty()) {
            Coordenadas atual = nextToVisit.dequeue(); //retira um vertice da fila
            labirinto.isValidLocation(atual.getX(), atual.getY());
            if (!labirinto.isValidLocation(atual.getX(), atual.getY()) || labirinto.isExplored(atual.getX(), atual.getY())) {
                continue;
            }

            if (labirinto.isWall(atual.getX(), atual.getY())) {
                labirinto.setVisited(atual.getX(), atual.getY(), true);
                continue;
            }

            if (labirinto.isVilaoPos(atual.getX(), atual.getY())) {
                return backtrackPath(atual);
            }

            for (int[] direcao : DIRECOES) { //para cada vizinho de direcao em direcoes
                Coordenadas coord = new Coordenadas(atual.getX() + direcao[0], atual.getY() + direcao[1], atual);
                //numera esse vizinho
                nextToVisit.enqueue(coord); //poe esse vizinho na fila
                labirinto.setVisited(atual.getX(), atual.getY(), true); //marca como visitado
            }
        }
        // return Collections.<Coordenadas>emptyList(); //método sempre retornará a mesma instância (singleton).
       //Sabendo que o método retorna uma lista sempre, mesmo que vazia, evita a checagem por null

       return Queue.<Coordenadas>emptyQueue();
    }

    private Queue<Coordenadas> backtrackPath(Coordenadas atual) { //metodo para 'andar para trás'
        Queue<Coordenadas> path = new Queue<>(); //lista de caminhos
        Coordenadas iterador = atual;

        while (iterador != null) { //processo iterativo
            path.enqueue(iterador);
            iterador = iterador.getParent();
            countMov++;
        }
        countMov--; //tira um para nao contar o momento que A encontra B
        return path;
    }

    public int getCountMov(){ return countMov;} //retorna o numero de movimentos
}