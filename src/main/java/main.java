import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.io.FileWriter;
import java.io.IOException;

public class main {


    public static void main(String[] args) {

//         Graph graph = pcc.generer_Aleatoire(5);
//         pcc.display(graph);
//         pcc.djikstra(graph, graph.getNode(0));
//        // Exécuter l'algorithme de Dijkstra de GraphStream
//        pcc.dijkstraGS(graph, graph.getNode(0).getId());

        // Créez une instance de la classe PCC
        PCC pcc = new PCC();

        // Définissez la taille du graphe aléatoire


        long debut, fin;

        try (FileWriter csvWriter = new FileWriter("resultats_dijkstra.csv")) {
            csvWriter.append("NodeCount,CustomDijkstraTime,GraphStreamDijkstraTime\n");

            for (int i = 0; i <= 10000; i += 1000) {
                Graph graph = pcc.generer_Aleatoire(i);
                Node sourceNode = graph.getNode(0);

                debut = System.currentTimeMillis();
                pcc.djikstra(graph, sourceNode);
                fin = System.currentTimeMillis();
                long customDijkstraTime = fin - debut;

                debut = System.currentTimeMillis();
                PCC.dijkstraGS(graph, sourceNode.getId());
                fin = System.currentTimeMillis();
                long graphStreamDijkstraTime = fin - debut;

                writeResultsToCSV(csvWriter, graph.getNodeCount(), customDijkstraTime, graphStreamDijkstraTime);

                System.out.println("--------------------" + i + "-------------------");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeResultsToCSV(FileWriter csvWriter, int nodeCount, long customDijkstraTime, long graphStreamDijkstraTime) throws IOException {
        csvWriter.append(nodeCount + "," + customDijkstraTime + "," + graphStreamDijkstraTime + "\n");
    }
}