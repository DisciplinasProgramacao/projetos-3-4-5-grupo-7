package codigo;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
}