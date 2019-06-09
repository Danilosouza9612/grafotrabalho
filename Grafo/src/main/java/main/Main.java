package main;

import grafo.Grafo;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) throws Exception {
		Grafo grafo = new Grafo();
		Scanner  l = new Scanner(System.in);
		String artista1, artista2;

		System.out.println("Digite o nome do primeiro artista:");
		artista1=l.nextLine();
		
		while(!grafo.verticeExiste(artista1)) {
			System.out.println("Artista não encontrado");
			System.out.println("Digite o nome do primeiro artista:");
			artista1=l.nextLine();
		}
		System.out.println("Digite o nome do segundo artista:");
		artista2=l.nextLine();
		
		while(!grafo.verticeExiste(artista2)) {
			System.out.println("Artista não encontrado");
			System.out.println("Digite o nome do segundo artista:");
			artista2=l.nextLine();
		}
		
		double semelhanca = grafo.menorCaminho(artista1, artista2);
		System.out.println(semelhanca);
		
		if(semelhanca==1) {
			System.out.println(artista1 + " e "+artista2+" são extremamente semelhantes");
		}else if(semelhanca==2) {
			System.out.println(artista1 + " e "+artista2+" são muito semelhantes");			
		}else if(semelhanca==3) {
			System.out.println(artista1 + " e "+artista2+" são mediamente semelhantes");						
		}else if(semelhanca==4) {
			System.out.println(artista1 + " e "+artista2+" são pouco semelhantes");						
		}else if(semelhanca==5) {
			System.out.println(artista1 + " e "+artista2+" são muito pouco semelhantes");
		}else {
			System.out.println(artista1 + " e "+artista2+" não são semelhantes");						
		}

	}
}
