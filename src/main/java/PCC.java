import org.graphstream.algorithm.Dijkstra;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class PCC {
    private HashMap<Node, Double> queue = new HashMap<Node, Double>();

    public Graph generer_Aleatoire(int taille) {
        Graph graph = new SingleGraph("My Graphe");
        Generator g = new RandomGenerator(10, false, false, "", "cap");
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

    public static void init(Graph graph, Node source) {
        for (Node node : graph) {
            node.setAttribute("distance", node.equals(source) ? 0 : Double.POSITIVE_INFINITY);
            //pile
            node.setAttribute("parent", new Stack<Node>());
        }
    }


    private Node findNodeWithMinimumDistance(Set<Node> nodes) {
        Node minDistanceNode = null;
        Double minDistance = Double.MAX_VALUE;

        for (Node node : nodes) {
            Double distance = node.getAttribute("distance");

            if (distance < minDistance) {
                minDistance = distance;
                minDistanceNode = node;
            }
        }

        return minDistanceNode;
    }

    public void djikstra(Graph graph, Node source) {
        long debut, fin;
        debut = System.currentTimeMillis();
        init(graph, source);
        queue.put(source, source.getAttribute("distance"));
        while (queue.size() != 0) {
            Node courant = findNodeWithMinimumDistance(queue.keySet());
            Iterator<Edge> edgeIterator = courant.getLeavingEdgeIterator();
            queue.remove(courant);
            // parcours des voisins
            while (edgeIterator.hasNext()) {
                Edge currEdge = edgeIterator.next();
                Node opNode = currEdge.getTargetNode();
                if (!queue.containsKey(opNode)) {
                    Double currNodeDist = courant.getAttribute("distance");
                    Double edgeWeight = currEdge.getAttribute("cap");
                    Double sum = currNodeDist + edgeWeight;
                    Double opNodeDistance = opNode.getAttribute("distance");
                    if (opNodeDistance > sum) {
                        opNode.setAttribute("distance", sum);
                        opNode.setAttribute("parent", courant);
                        queue.put(opNode, opNode.getAttribute("distance"));
                    }
                }
                System.out.println("Edge from Node " + graph.getNode(0) + " to Node " + opNode.getId() +
                        ", Distance: " + opNode.getAttribute("distance"));
            }
        }
        fin = System.currentTimeMillis();
        long temps = fin - debut;
        System.out.println("temps d'execution de la methode djikstra en ms : " + temps);


    }

    public static void dijkstraGS(Graph graph, String sourceNodeId) {
        // Mesure du temps de début
        long startTime = System.currentTimeMillis();

        Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "cap");

        // Initialise Dijkstra avec le graphe et le nœud source
        dijkstra.init(graph);
        dijkstra.setSource(graph.getNode(sourceNodeId));

        // Exécute l'algorithme de Dijkstra
        dijkstra.compute();

        // Mesure du temps de fin
        long endTime = System.currentTimeMillis();

        // Affiche les résultats
        for (Node node : graph) {
            System.out.println("Distance de " + sourceNodeId + " à " + node.getId() + " : " + dijkstra.getPathLength(node));

            // Affiche le chemin seulement si un chemin existe
            if (dijkstra.getPathLength(node) != Double.POSITIVE_INFINITY) {
                System.out.println("Chemin de " + sourceNodeId + " à " + node.getId() + " : " + dijkstra.getPath(node).toString());
            } else {
                System.out.println("Pas de chemin de " + sourceNodeId + " à " + node.getId());
            }
        }

        // Affiche le temps d'exécution
        System.out.println("Temps d'exécution de Dijkstra : " + (endTime - startTime) + " millisecondes");
    }
}