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
			System.out.println("2 - Ver Recomendações");
			System.out.println("3 - Sair");
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
						System.out.println("Consta em nossa base de dados");
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}while(true);
				break;
			case 2:
				recomendado = recomendacoes.listarRecomendacoes();
				for(String item : recomendado) {
					System.out.println(item);
				}
				break;
			case 3:
				break;
			default:
				System.out.println("Opção Inválida");
			}
		}while(opcao!=3);

	}
}
