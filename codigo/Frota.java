package codigo;

import java.util.ArrayList;
import java.util.List;

public class Frota {
    private List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();

    public List<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    /**
     * Método que calcula a média de quilometros rodados de todos os veículos
     * @return double média geral
     */
    public double calcular_Media(){
        double totalKm = this.listaVeiculos.stream().mapToDouble( veiculoKm -> veiculoKm.getRota().stream().mapToDouble(rota -> rota.distancia_total).sum()).sum();
        int quantidadeDeRotas = this.listaVeiculos.stream().mapToInt(totalRotaPorVeiculo -> totalRotaPorVeiculo.getRota().size()).sum();
        return totalKm/quantidadeDeRotas;
    }

    /**
     * Método que cria um novo veículo, podendo ser carro, caminhao, van e furgao
     */
    public void addVeiculo(String kmMedio,String capacidade, String valorVenda, String veiculoSelecionado, String placa ) throws NumberFormatException, Exception{
        SuperFactorty superFactorty = new SuperFactorty();
        Veiculo veiculoAdd = superFactorty.addVeiculo(kmMedio, capacidade, valorVenda, veiculoSelecionado, placa);
        this.listaVeiculos.add(veiculoAdd);
    }

    /**
     * Método que adiciona veículos a partir de um arquivo
     */
    public void addVeiculos(String arquivo) throws NumberFormatException, Exception{
       SuperFactorty superFactorty = new SuperFactorty();
       List<Veiculo> listVeiculos = superFactorty.addVeiculos(arquivo);
       for (Veiculo veiculo : listVeiculos) {
        listaVeiculos.add(veiculo);
       }
       System.out.print("Veiculos adicionados");
    }

    /**
     * Método que adiciona veículos a partir de um arquivo e coloca todas informações que tinha no arquivo,
     * rotas, combustiveis, tanque e etc
     */
    public void addVeiculosCompletos(String arquivo) throws NumberFormatException, Exception{
        SuperFactorty superFactorty = new SuperFactorty();
        List<Veiculo> listVeiculos = superFactorty.addVeiculos(arquivo);
        for (Veiculo veiculo : listVeiculos) {
         listaVeiculos.add(veiculo);
        }
        System.out.print("Veiculos adicionados");
    }

    /**
     * Método que escreve em arquivo informações sobre os veículo, nele vai conter todas as informações do veículo
     */
    public void saveVeiculos(String arquivo) throws Exception{
        CsvWriter escreverFile = new CsvWriter(arquivo, listaVeiculos);
        escreverFile.salvarInformacoesListaVeiculos();
        
    }

    /**
     * Método que pega um veículo a partir da placa
     * @param placa
     * @return Veiculo
     * @throws Exception
     */
    public Veiculo pegarVeiculoEspecifico(String placa) throws Exception{
        List<Veiculo> listaAuxParaVerSeExisteVeiculo = this.listaVeiculos.stream().filter(veiculo -> veiculo.getPlaca().equals(placa)).toList();
        if(listaAuxParaVerSeExisteVeiculo.size() == 0){
            throw new Exception("Nao existe essa placa");
        }
        return listaAuxParaVerSeExisteVeiculo.get(0);  
    }

    /**
     * Métodos que pegam o veículo especifico
     * @param placa
     * @return
     */
    public Carro pegarCarroEspecifico(String placa){
        return (Carro) this.listaVeiculos.stream().filter(veiculo -> veiculo.getPlaca().equals(placa)).toList().get(0);
    }
    public Caminhao pegarCaminhaoEspecifico(String placa){
        return (Caminhao)this.listaVeiculos.stream().filter(veiculo -> veiculo.getPlaca().equals(placa)).toList().get(0);
    }
    public Van pegarVanEspecifico(String placa){
        return (Van)this.listaVeiculos.stream().filter(veiculo -> veiculo.getPlaca().equals(placa)).toList().get(0);
    }
    public Furgao pegarFurgaoEspecifico(String placa){
        return (Furgao)this.listaVeiculos.stream().filter(veiculo -> veiculo.getPlaca().equals(placa)).toList().get(0);
    }
    
    public double[] ordem_decrescente_por_custo(){
        double[] sort = this.listaVeiculos.stream().mapToDouble( veiculoKm -> veiculoKm.somar_custo_veiculo()).sorted().toArray();
        return sort;
    }

    /**
     * Esse método traz o custo total de todos os veículos
     */
    public void getListaDeVeiculosComPreco() {
        this.listaVeiculos.stream().forEach(veiculo ->{
            StringBuilder relatorioVaiculoMaisPrecoFinal = new StringBuilder();
            relatorioVaiculoMaisPrecoFinal.append(veiculo.toString()).append("\n Custos totais: \n").append(veiculo.somar_custo_veiculo());
            System.out.print(relatorioVaiculoMaisPrecoFinal.toString());
        });
    }
}
