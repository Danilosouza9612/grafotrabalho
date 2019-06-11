package recomendacoes;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Comparator;
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
		this.artistas = new String[10];
		this.semelhancas = new double[10][10];
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
		boolean existe = grafo.verticeExiste(artista);
		
		
		if(existe && !this.artistaCache(artista)) {
			this.increaseCont();
			artistas[pos] = artista;
			
			
			for(int i=0; i<this.cont; i++) {
				System.out.println("Entrou");
				if(i!=pos) {
					try {
						menorcaminho=grafo.menorCaminho(artista, artistas[i]);
					}catch(Exception e) {
						return;
					}
					semelhancas[pos][i]=menorcaminho;
					semelhancas[i][pos]=menorcaminho;
				}
			}
			this.increasePos();
		}else if(!existe) {
			throw new Exception("Artista não encontrado");
		}
	}
	private void increasePos() {
		pos++;
		if(pos>=semelhancas.length) {
			pos=0;
		}
	}
	private void increaseCont() {
		if(cont<10) {
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
				
				for(int j=0; j<cont; j++) {
					if(listaConsultas.size()==0) {
						break;
					}
					
					if(j!=i) {
						if(listaConsultas.contains(j) && semelhancas[i][j]<=3) {
							set.add(artistas[j]);
						}
					}
				}
				
				conjuntosVertices.add(set);
			}
		}
		
		Collections.sort(conjuntosVertices, new Comparator<List<String>>() {

			public int compare(List<String> o1, List<String> o2) {
				if(o1.size()==o2.size()) {
					return 0;
				}
				if(o1.size()>o2.size()) {
					return -1;
				}
				return 1;
			}
			
		});
		
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
		String noSelecionado;
		
		/*for(List<String> i : conjunts) {
			System.out.println(i);
		}*/
		
		for(List<String> conjunto : conjunts) {
			if(conjunto.size()>1) {
				for(int i=0; i<conjunto.size(); i++) {
					vizinhos = grafo.listaVizinhos(conjunto.get(i));
					if(vizinhos.size()>0) {
						noSelecionado = vizinhos.get(random.nextInt(vizinhos.size()-1));
						if(!recomendacoes.contains(noSelecionado) && !this.artistaCache(noSelecionado)) {
							recomendacoes.add(noSelecionado);
							cont++;
							if(cont>20) {
								return recomendacoes;
							}
						}
					}
				}
			}
		}
		
		return recomendacoes;
	}
	private boolean artistaCache(String artista) {
		for(String item : this.artistas) {
			if(item==null) {
				return false;
			}
			if(item.equals(artista)) {
				return true;
			}
		}
		return false;
	}
}
