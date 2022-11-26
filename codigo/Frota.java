package codigo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Frota {
    private List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();

    public double calcular_Media(){
        double totalKm = this.listaVeiculos.stream().mapToDouble( veiculoKm -> veiculoKm.getRota().stream().mapToDouble(rota -> rota.distancia_total).sum()).sum();
        int quantidadeDeRotas = this.listaVeiculos.stream().mapToInt(totalRotaPorVeiculo -> totalRotaPorVeiculo.getRota().size()).sum();
        return totalKm/quantidadeDeRotas;
    }

    public void addVeiculo(String kmMedio,String capacidade, String valorVenda, String veiculoSelecionado, String placa ) throws NumberFormatException, Exception{
        switch (veiculoSelecionado) {
            case "1":
                Carro carroAdicionado = new Carro(Double.parseDouble(kmMedio),Double.parseDouble(capacidade),Double.parseDouble(valorVenda), placa);
                this.listaVeiculos.add(carroAdicionado);
                break;
            case "2":
                Caminhao caminhaoAdicionado = new Caminhao(Double.parseDouble(kmMedio),Double.parseDouble(capacidade),Double.parseDouble(valorVenda), placa);
                this.listaVeiculos.add(caminhaoAdicionado);
                break;
            case "3":
                Van vanAdicionado = new Van(Double.parseDouble(kmMedio),Double.parseDouble(capacidade),Double.parseDouble(valorVenda), placa);
                this.listaVeiculos.add(vanAdicionado);
                break;
            case "4":
                Furgao furgaoAdicionado = new Furgao(Double.parseDouble(kmMedio),Double.parseDouble(capacidade),Double.parseDouble(valorVenda), placa);
                this.listaVeiculos.add(furgaoAdicionado);
                break;
            default:
                System.out.print("Nao existe essa opcao");
                break;
        }
    }

    public String addVeiculos(String arquivo) throws NumberFormatException, Exception{
        CsvReader newVeiculos = new CsvReader(arquivo);
        if(newVeiculos.returnArrayNumbers().size() == 0){
            return "nao possui veiculos";
        }
        for (String veiculo : newVeiculos.returnArrayNumbers()) {
            System.out.print(veiculo);
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
        return "veiculos adicionados";
    }

    public void saveVeiculos(String arquivo){
        StringBuilder stringParaArquivoFinal = new StringBuilder();
        for (int i = 0; i < this.listaVeiculos.size(); i++) {
            stringParaArquivoFinal.append(listaVeiculos.get(i).getClass().getSimpleName()).append(listaVeiculos.get(i).getKm_medio()).append(",").append(listaVeiculos.get(i).getValor_venda()).append(",").append(listaVeiculos.get(i).preco_seguro).append(";");
        }
        System.out.print(stringParaArquivoFinal);
    }

    public List<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    public Veiculo pegarVeiculoEspecifico(String placa) throws Exception{
        System.out.print(placa);
        List<Veiculo> listaAuxParaVerSeExisteVeiculo = this.listaVeiculos.stream().filter(veiculo -> veiculo.getPlaca().equals(placa)).toList();
        if(listaAuxParaVerSeExisteVeiculo.size() == 0){
            throw new Exception("Nao existe essa placa");
        }
        return listaAuxParaVerSeExisteVeiculo.get(0);  
    }

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
        double[] sort = this.listaVeiculos.stream().mapToDouble( veiculoKm -> veiculoKm.custos()).sorted().toArray();
        return sort;
    }

    public void getListaDeVeiculosComPreco() {
        this.listaVeiculos.stream().forEach(veiculo ->{
            StringBuilder relatorioVaiculoMaisPrecoFinal = new StringBuilder();
            relatorioVaiculoMaisPrecoFinal.append(veiculo.toString()).append("\n Custos totais: \n").append(veiculo.custos());
            System.out.print(relatorioVaiculoMaisPrecoFinal.toString());
        });;
    }
}
