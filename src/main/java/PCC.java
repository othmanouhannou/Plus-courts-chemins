import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class PCC {
    public Graph generer_Aleatoire(int taille) {
        Graph graph = new SingleGraph("My Graphe");
        Generator g = new RandomGenerator(10);
        g.addSink(graph);
        g.begin();
        for (int i = 0; i < taille; i++)
            g.nextEvents();
            g.end();
        return graph;
    }
// method display
    public void display(Graph graph) {
        graph.display();
    }
}