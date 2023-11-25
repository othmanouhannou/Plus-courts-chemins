import org.graphstream.graph.Graph;

public class main {
    public static void main(String[] args) {
         PCC pcc = new PCC();
         Graph graph = pcc.generer_Aleatoire(100);
         pcc.display(graph);
    }
}
