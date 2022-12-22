
package codigo;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

import java.util.List;
import java.util.Map.Entry;

/**
 * Classe que escreve as informações em um arquivo csv como se fosse um banco de dados
 */
public class CsvWriter {
    List<Veiculo> listaVeiculo;
    Veiculo veiculo;
    String caminho;

    public CsvWriter(String caminho, List<Veiculo> listaVeiculo){
        this.caminho = caminho;
        this.listaVeiculo = listaVeiculo;
    }
    public CsvWriter(String caminho, Veiculo veiculo){
        this.caminho = caminho;
        this.veiculo = veiculo;
    }
    /**
     * Método que transforma todas as informações do veículo em string separados por , e _ para separar as informações
     * e utilizando o símbolo ; para separar os veículos
     * @throws Exception
     */
    public void salvarInformacoesListaVeiculos() throws Exception {
        StringBuilder stringParaArquivoFinal = new StringBuilder();
        for (int i = 0; i < this.listaVeiculo.size(); i++) {
            if(i == this.listaVeiculo.size() -1){
                stringParaArquivoFinal.append(this.listaVeiculo.get(i).getClass().getSimpleName()).append(",")
                .append(this.listaVeiculo.get(i).getKm_medio()).append(",")
                .append(this.listaVeiculo.get(i).getCapacidade()).append(",")
                .append(this.listaVeiculo.get(i).getValor_venda()).append(",")
                .append(this.listaVeiculo.get(i).getPlaca()).append(",")
                .append(this.listaVeiculo.get(i).getTanque()).append(",")
                .append(this.listaVeiculo.get(i).getTipoCombustivel().name()).append(",");
                if(this.listaVeiculo.get(i).getRota().size() == 0){
                    stringParaArquivoFinal.append(", ");
                }
                this.listaVeiculo.get(i).getRota().stream().forEach(rota ->{
                   stringParaArquivoFinal.append(rota.toString()); 
                   stringParaArquivoFinal.append("_"); 
                });
                stringParaArquivoFinal.append(",");
                if(this.listaVeiculo.get(i).getCombustiveisSelecionados().size() == 0){
                    stringParaArquivoFinal.append(" ");
                }
                for (Entry<String, Double> pair :this.listaVeiculo.get(i).getCombustiveisSelecionados().entrySet()) {
                    stringParaArquivoFinal.append(pair.getKey() + "/" + pair.getValue());
                    stringParaArquivoFinal.append("_");
                }
                stringParaArquivoFinal.append(",");
                if(this.listaVeiculo.get(i).getCombustivelAtual() != null){
                    stringParaArquivoFinal.append(this.listaVeiculo.get(i).getCombustivelAtual().name());
                } else {
                    stringParaArquivoFinal.append(" ");
                }
                stringParaArquivoFinal.append(";");
            } else {
                stringParaArquivoFinal.append(this.listaVeiculo.get(i).getClass().getSimpleName()).append(",")
                .append(this.listaVeiculo.get(i).getKm_medio()).append(",")
                .append(this.listaVeiculo.get(i).getCapacidade()).append(",")
                .append(this.listaVeiculo.get(i).getValor_venda()).append(",")
                .append(this.listaVeiculo.get(i).getPlaca()).append(",")
                .append(this.listaVeiculo.get(i).getTanque()).append(",")
                .append(this.listaVeiculo.get(i).getTipoCombustivel().name()).append(",");
                if(this.listaVeiculo.get(i).getRota().size() == 0){
                    stringParaArquivoFinal.append(", ");
                }
                this.listaVeiculo.get(i).getRota().stream().forEach(rota ->{
                   stringParaArquivoFinal.append(rota.toString());
                   stringParaArquivoFinal.append("_"); 
                });
                stringParaArquivoFinal.append(",");
                if(this.listaVeiculo.get(i).getCombustiveisSelecionados().size() == 0){
                    stringParaArquivoFinal.append(" ");
                }
                for (Entry<String, Double> pair :this.listaVeiculo.get(i).getCombustiveisSelecionados().entrySet()) {
                    stringParaArquivoFinal.append(pair.getKey() + "/" + pair.getValue());
                    stringParaArquivoFinal.append("_");
                }
                stringParaArquivoFinal.append(",");
                if(this.listaVeiculo.get(i).getCombustivelAtual() != null){
                    stringParaArquivoFinal.append(this.listaVeiculo.get(i).getCombustivelAtual().name());
                } else {
                    stringParaArquivoFinal.append(" ");
                }
                stringParaArquivoFinal.append(";");
            }
        }
        try {
            FileWriter myWriter = new FileWriter(this.caminho);
            myWriter.write(stringParaArquivoFinal.toString());
            myWriter.close();
            System.out.println("Texto escrito no arquivo.");
        } catch (IOException e) {
            throw new Exception(e);
        }
    }
}