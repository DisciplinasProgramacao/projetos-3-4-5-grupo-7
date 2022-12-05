package codigo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe que lê arquivo csv, nela você coloca no construtor o caminho
 * do arquivo csv e a função returnArrayNumber retorna um ArrayList com
 * todos os elementos do csv.
 */
public class CsvReader{
    private String caminhoRelativoArquivoCsv;
    public CsvReader(String caminhoRelativo){
        this.caminhoRelativoArquivoCsv = caminhoRelativo;
    }

    private List<String> returnArrayNumbers() throws IOException, FileNotFoundException{
        String arquivoCSV = this.caminhoRelativoArquivoCsv;
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ";";
		List<String> numeros = new ArrayList<String>();
		br = new BufferedReader(new FileReader(arquivoCSV));
		while ((linha = br.readLine()) != null) {

			String[] valores = linha.split(csvDivisor);

			for (String string : valores) {
				numeros.add(string);
			}

		}
		br.close();
		return numeros;
    }

	public List<Veiculo> addVeiculos() throws NumberFormatException, Exception{
        List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
        if(returnArrayNumbers().size() == 0){
            return null;
        }
        for (String veiculo : returnArrayNumbers()) {
            String[] veiculosInfo = veiculo.split(",");
            switch (veiculosInfo[0]) {
                case "Caminhao":
                    Caminhao caminhaoAux = new Caminhao(Double.parseDouble(veiculosInfo[1]), Double.parseDouble(veiculosInfo[2]), Double.parseDouble(veiculosInfo[3]), veiculosInfo[4]);
                    listaVeiculos.add(caminhaoAux);
                    System.out.print(caminhaoAux);
                    break;
                case "Carro":
                    Carro carroAux = new Carro(Double.parseDouble(veiculosInfo[1]), Double.parseDouble(veiculosInfo[2]), Double.parseDouble(veiculosInfo[3]), veiculosInfo[4]);
                    listaVeiculos.add(carroAux);
                    System.out.print(carroAux);
                    break;
                case "Van":
                    Van vanAux = new Van(Double.parseDouble(veiculosInfo[1]), Double.parseDouble(veiculosInfo[2]), Double.parseDouble(veiculosInfo[3]), veiculosInfo[4]);
                    listaVeiculos.add(vanAux);
                    System.out.print(vanAux);
                    break;
                case "Furgao":
                    Furgao forgaoAux = new Furgao(Double.parseDouble(veiculosInfo[1]), Double.parseDouble(veiculosInfo[2]), Double.parseDouble(veiculosInfo[3]), veiculosInfo[4]);
                    listaVeiculos.add(forgaoAux);
                    System.out.print(forgaoAux);
                    break;
            }
        }
        return listaVeiculos;
    }

	public List<Veiculo> lerVeiculoArquivoCompleto() throws NumberFormatException, Exception{
		List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
        if(returnArrayNumbers().size() == 0){
            return null;
        }
        for (String veiculo : returnArrayNumbers()) {
            String[] veiculosInfo = veiculo.split(",");
            switch (veiculosInfo[0]) {
                case "Caminhao":
                    Caminhao caminhaoAux = new Caminhao(Double.parseDouble(veiculosInfo[1]), Double.parseDouble(veiculosInfo[2]), Double.parseDouble(veiculosInfo[3]), veiculosInfo[4]);
					caminhaoAux.setTanque(veiculosInfo[5]);
					caminhaoAux.setCombustivelTipo(veiculosInfo[6]);
					String[] rotasArmazenamento = veiculosInfo[7].split("/");
					for (int i = 0; i < rotasArmazenamento.length; i++) {
                        
						if(i % 2 == 0){
						caminhaoAux.setRota(rotasArmazenamento[i], rotasArmazenamento[i+1]);
						}
					}
					String[] combustiveisArmazenamento = veiculosInfo[8].split("/");
					for (int i = 0; i < combustiveisArmazenamento.length; i++) {
						if(i % 2 == 0){
							caminhaoAux.setCombustiveisSelecionados(combustiveisArmazenamento[i], combustiveisArmazenamento[i+1]);
						}
					}

					listaVeiculos.add(caminhaoAux);
                    System.out.print(caminhaoAux);
                    break;
                case "Carro":
                    Carro carroAux = new Carro(Double.parseDouble(veiculosInfo[1]), Double.parseDouble(veiculosInfo[2]), Double.parseDouble(veiculosInfo[3]), veiculosInfo[4]);
                    carroAux.setTanque(veiculosInfo[5]);
					carroAux.setCombustivelTipo(veiculosInfo[6]);
                    System.out.print(veiculosInfo[7].length());
                    if(veiculosInfo[7].length() > 1){
                        String[] rotasArmazenamentoCarro = veiculosInfo[7].split("/");
                        for (int i = 0; i < rotasArmazenamentoCarro.length; i++) {
                            
                            if(i % 2 == 0){
                            carroAux.setRota(rotasArmazenamentoCarro[i], rotasArmazenamentoCarro[i+1]);
                            }
                        }
                    }

					String[] combustiveisArmazenamentoCarro = veiculosInfo[8].split("/");
                    if(veiculosInfo[8].length() > 1){
                        for (int i = 0; i < combustiveisArmazenamentoCarro.length; i++) {
                            if(i % 2 == 0){
                                carroAux.setCombustiveisSelecionados(combustiveisArmazenamentoCarro[i], combustiveisArmazenamentoCarro[i+1]);
                            }
                        }
                    }

					listaVeiculos.add(carroAux);
                    System.out.print(carroAux);
                    break;
                case "Van":
                    Van vanAux = new Van(Double.parseDouble(veiculosInfo[1]), Double.parseDouble(veiculosInfo[2]), Double.parseDouble(veiculosInfo[3]), veiculosInfo[4]);
                    vanAux.setTanque(veiculosInfo[5]);
					vanAux.setCombustivelTipo(veiculosInfo[6]);
					String[] rotasArmazenamentoVan = veiculosInfo[7].split("/");
                    if(veiculosInfo[7].length() > 1){
                        for (int i = 0; i < rotasArmazenamentoVan.length; i++) {
                            
                            if(i % 2 == 0){
                            vanAux.setRota(rotasArmazenamentoVan[i], rotasArmazenamentoVan[i+1]);
                            }
                        }
                    }
					String[] combustiveisArmazenamentoVan = veiculosInfo[8].split("/");
                    if(veiculosInfo[8].length() > 1){
                        for (int i = 0; i < combustiveisArmazenamentoVan.length; i++) {
                            if(i % 2 == 0){
                                vanAux.setCombustiveisSelecionados(combustiveisArmazenamentoVan[i], combustiveisArmazenamentoVan[i+1]);
                            }
                        }
                    }

					listaVeiculos.add(vanAux);
                    System.out.print(vanAux);
                    break;
                case "Furgao":
                    Furgao forgaoAux = new Furgao(Double.parseDouble(veiculosInfo[1]), Double.parseDouble(veiculosInfo[2]), Double.parseDouble(veiculosInfo[3]), veiculosInfo[4]);
                    forgaoAux.setTanque(veiculosInfo[5]);
					forgaoAux.setCombustivelTipo(veiculosInfo[6]);
					String[] rotasArmazenamentoFurgao = veiculosInfo[7].split("/");
                    if(veiculosInfo[7].length() > 1){
                        for (int i = 0; i < rotasArmazenamentoFurgao.length; i++) {
                            
                            if(i % 2 == 0){
                            forgaoAux.setRota(rotasArmazenamentoFurgao[i], rotasArmazenamentoFurgao[i+1]);
                            }
                        }
                    }
					String[] combustiveisArmazenamentoFurgao = veiculosInfo[8].split("/");
                    if(veiculosInfo[8].length() > 1){
                        for (int i = 0; i < combustiveisArmazenamentoFurgao.length; i++) {
                            if(i % 2 == 0){
                                forgaoAux.setCombustiveisSelecionados(combustiveisArmazenamentoFurgao[i], combustiveisArmazenamentoFurgao[i+1]);
                            }
                        }
                    }

					listaVeiculos.add(forgaoAux);
                    System.out.print(forgaoAux);
                    break;
            }
        }
        return listaVeiculos;

	}
}