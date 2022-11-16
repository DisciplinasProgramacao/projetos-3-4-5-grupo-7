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

    public List<Integer> returnArrayNumbers(){
        String arquivoCSV = this.caminhoRelativoArquivoCsv;
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ";";
		try {
            List<Integer> numeros = new ArrayList<Integer>();
			br = new BufferedReader(new FileReader(arquivoCSV));
			while ((linha = br.readLine()) != null) {
	
				String[] valores = linha.split(csvDivisor);
	
				for (String string : valores) {
                    numeros.add(Integer.parseInt(string));
                }
	
			}
            return numeros;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        return null;
    }
}