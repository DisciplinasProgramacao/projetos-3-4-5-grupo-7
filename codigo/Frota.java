package codigo;

import java.util.ArrayList;
import java.util.List;

public class Frota {
    private List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();

    public double calcular_Media(){
        double totalKm = this.listaVeiculos.stream().mapToDouble( veiculoKm -> veiculoKm.getRota().stream().mapToDouble(rota -> rota.distancia_total).sum()).sum();
        int quantidadeDeRotas = this.listaVeiculos.stream().mapToInt(totalRotaPorVeiculo -> totalRotaPorVeiculo.getRota().size()).sum();
        return totalKm/quantidadeDeRotas;
    }

    public void addVeiculo(Veiculo veiculo){
        listaVeiculos.add(veiculo);
    }

    public String addVeiculos(String arquivo){
        CsvReader newVeiculos = new CsvReader(arquivo);
        for (String veiculo : newVeiculos.returnArrayNumbers()) {
            String[] veiculosInfo = veiculo.split(",");
            switch (veiculosInfo[0]) {
                case "Caminhao":
                    Caminhao caminhaoAux = new Caminhao(Double.parseDouble(veiculosInfo[1]), Double.parseDouble(veiculosInfo[2]), Double.parseDouble(veiculosInfo[3]), Double.parseDouble(veiculosInfo[4]), Double.parseDouble(veiculosInfo[4]));
                    listaVeiculos.add(caminhaoAux);
                case "Carro":
                    Carro carroAux = new Carro(Double.parseDouble(veiculosInfo[1]), Double.parseDouble(veiculosInfo[2]), Double.parseDouble(veiculosInfo[3]), 0, 0);
                    listaVeiculos.add(carroAux);
                case "Van":
                    Van vanAux = new Van(Double.parseDouble(veiculosInfo[1]), Double.parseDouble(veiculosInfo[2]), Double.parseDouble(veiculosInfo[3]), 0, 0);
                    listaVeiculos.add(vanAux);
                case "Furgao":
                    Furgao forgaoAux = new Furgao(Double.parseDouble(veiculosInfo[1]), Double.parseDouble(veiculosInfo[2]), Double.parseDouble(veiculosInfo[3]), 0, 0);
                    listaVeiculos.add(forgaoAux);
            }
        }
        return "nao possui veiculos";
    }

    public void saveVeiculos(Veiculo veiculo, String arquivo){
        StringBuilder stringParaArquivoFinal = new StringBuilder();
        for (int i = 0; i < listaVeiculos.size(); i++) {
            stringParaArquivoFinal.append(listaVeiculos.getClass()).append(",").append(listaVeiculos.get(i).getKm_medio()).append(",").append(listaVeiculos.get(i).getValor_venda()).append(",").append(listaVeiculos.get(i).preco_seguro).append(";");
        }
        
    }

    public double[] ordem_decrescente_por_custo(){
        double[] sort = this.listaVeiculos.stream().mapToDouble( veiculoKm -> veiculoKm.custos()).sorted().toArray();
        return sort;
    }
}
