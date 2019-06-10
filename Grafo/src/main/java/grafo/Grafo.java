package grafo;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import repository.ArtistaDataCollection;

public class Grafo {
	private static Graph<String, DefaultEdge> graph;
	
	public Grafo() {
		graph = new SimpleGraph(DefaultEdge.class);
		ArtistaDataCollection collection = new ArtistaDataCollection();
				
		collection.gerarVertices(graph);
		
		collection.gerarArestas(graph);
	}
	
	public double menorCaminho(String artista1, String artista2) throws Exception {
		if(!graph.containsVertex(artista1)) {
			throw new Exception(artista1+" não consta em nossa base de dados");
		}
		if(!graph.containsVertex(artista2)) {
			throw new Exception(artista2+" não consta em nossa base de dados");
		}
		DijkstraShortestPath dijkstra = new DijkstraShortestPath(graph);
		
		GraphPath path = dijkstra.getPath(artista1, artista2);
		
		return path.getLength();
		
	}
	public boolean verticeExiste(String artista) {
		return this.graph.containsVertex(artista);
	}
	public List<String> listaVizinhos(String artista){
		return Graphs.neighborListOf(graph, artista);
	}
	
}
