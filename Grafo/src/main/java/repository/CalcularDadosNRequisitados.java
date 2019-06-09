package repository;

import java.io.IOException;

public class CalcularDadosNRequisitados {

	public static void main(String[] args) throws IOException {
		GerarDataSet dataset = new GerarDataSet(5);
		
		dataset.gerarDataSetNRequisitados();
	}

}
