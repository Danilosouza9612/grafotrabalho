package recomendacoes;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import grafo.Grafo;

public class Recomendacoes {
	private String[] artistas;
	private double[][] semelhancas;
	private int pos;
	private int cont;
	private final Grafo grafo;
	
	public Recomendacoes() {
		this.artistas = new String[20];
		this.semelhancas = new double[20][20];
		grafo = new Grafo();
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(i!=j) {
					this.semelhancas[i][j]=Double.POSITIVE_INFINITY;
				}else {
					this.semelhancas[i][i]=0;
				}
			}
		}
	}
	public void adicionarArtista(String artista) throws Exception {
		double menorcaminho;
		
		
		if(grafo.verticeExiste(artista)) {
			artistas[pos] = artista;
			this.increaseCont();
			
			for(int i=0; i<this.cont; i++) {
				if(i!=pos) {
					try {
						menorcaminho=grafo.menorCaminho(artista, artistas[i]);
					}catch(Exception e) {
						System.out.println(e.getMessage());
						return;
					}
					semelhancas[pos][i]=menorcaminho;
					semelhancas[i][pos]=menorcaminho;
				}
			}
			
			this.increasePos();
		}else {
			throw new Exception("Artista não encontrado");
		}
	}
	private void increasePos() {
		pos++;
		if(pos==semelhancas.length) {
			pos=0;
		}
	}
	private void increaseCont() {
		if(cont<9) {
			cont++;
		}
	}
	public void verMatriz() {
		for(int i=0; i<this.cont; i++) {
			System.out.println(i+ " - "+artistas[i]);
		}
		
		for(int i=0; i<cont; i++) {
			for(int j=0; j<cont; j++) {
				System.out.print(semelhancas[i][j] + " ");
			}
			System.out.println();
		}
	}
	private List<List<String>> gerarConjuntoDeVertices() {
		List<List<String>> conjuntosVertices = new LinkedList();
		List<Integer> listaConsultas = new ArrayList();
		List<String> set;
		
		for(int i=0; i<cont; i++) {
			listaConsultas.add(i);
		}
		
		for(int i=0; i<cont; i++) {
			if(listaConsultas.contains(i)) {
				set = new ArrayList();
				set.add(artistas[i]);
				listaConsultas.remove((Integer)i);
				
				for(int j=0; j<cont; j++) {
					if(listaConsultas.size()==0) {
						break;
					}
					
					if(listaConsultas.contains(j) && semelhancas[i][j]<=3) {
						set.add(artistas[j]);
						listaConsultas.remove((Integer)j);
					}
				}
				
				conjuntosVertices.add(set);
			}
		}
		
		return conjuntosVertices;
	}
	public void verConjuntos() {
		List<List<String>> conjuntos = this.gerarConjuntoDeVertices();
		
		for(List<String> i : conjuntos) {
			System.out.println(i);
		}
	}
	public List<String> listarRecomendacoes(){
		int cont=0;
		SecureRandom random = new SecureRandom();
		List<List<String>> conjunts = this.gerarConjuntoDeVertices();
		List<String> recomendacoes = new LinkedList();
		List<String> vizinhos;
		
		for(List<String> i : conjunts) {
			System.out.println(i);
		}
		
		for(List<String> conjunto : conjunts) {
			if(conjunto.size()>1) {
				for(int i=0; i<3 && i<conjunto.size(); i++) {
					vizinhos = grafo.listaVizinhos(conjunto.get(i));
					recomendacoes.add(vizinhos.get(random.nextInt(vizinhos.size()-1)));
					cont++;
					if(cont>20) {
						return recomendacoes;
					}
				}
			}
		}
		
		return recomendacoes;
	}
	
}
