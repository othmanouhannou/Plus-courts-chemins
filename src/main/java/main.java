import org.graphstream.graph.Graph;

public class main {
    public static void main(String[] args) {
         PCC pcc = new PCC();
         Graph graph = pcc.generer_Aleatoire(5);
         pcc.display(graph);
         pcc.djikstra(graph, graph.getNode(0));
        // Exécuter l'algorithme de Dijkstra de GraphStream
        pcc.dijkstraGS(graph, graph.getNode(0).getId());
    }
}
