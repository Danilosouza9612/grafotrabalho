package main;

import grafo.Grafo;
import recomendacoes.Recomendacoes;

import java.util.List;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Recomendacoes recomendacoes = new Recomendacoes();
		Scanner  l = new Scanner(System.in);
		String artista;
		int opcao;
		List<String> recomendado;
		
		do {
			System.out.println("1 - Consultar Artista");
			System.out.println("2 - Ver Matriz de Semelhanças");
			System.out.println("3 - Ver Recomendações");
			System.out.println("4 - Sair");
			System.out.println("Escolha uma opção:");
			opcao=l.nextInt();
			l.nextLine();
			
			switch(opcao) {
			case 1:
				do {
					System.out.println("Digite o nome de algum artista(-1 para voltar)");
					artista = l.nextLine();
					if(artista.equals("-1")) {
						break;
					}
					try {
						recomendacoes.adicionarArtista(artista);
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}while(true);
				break;
			case 2:
				recomendacoes.verMatriz();
				break;
			case 3:
				recomendado = recomendacoes.listarRecomendacoes();
				for(String item : recomendado) {
					System.out.println(item);
				}
				break;
			case 4:
				break;
			default:
				System.out.println("Opção Inválida");
			}
		}while(opcao!=4);

	}
}
