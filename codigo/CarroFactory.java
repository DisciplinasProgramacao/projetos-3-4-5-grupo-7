package codigo;

import java.text.ParseException;

public class CarroFactory {
    /**
     * Método da factory que vai criar o Carro com base em informações passadas
     * @param kmMedio
     * @param capacidade
     * @param valorVenda
     * @param placa
     * @return Carro
     * @throws Exception
     */
    public Carro createCarro(Double kmMedio,Double capacidade, Double valorVenda, String placa ) throws Exception{
        Carro carroAdicionado = new Carro(kmMedio, capacidade, valorVenda, placa);
        return carroAdicionado;
    }

    /**
     * Método da factory que vai criar o Carro com base em uma string
     * usado para pegar informações de um arquivo e transformar em um 
     * carro
     * @param kmMedio
     * @param capacidade
     * @param valorVenda
     * @param placa
     * @return Carro
     * @throws Exception
     */
    public Carro createCarroString(String info) throws NumberFormatException, Exception{
        String[] veiculosInfo = info.split(",");
        Carro carroAux = new Carro(Double.parseDouble(veiculosInfo[1]), Double.parseDouble(veiculosInfo[2]), Double.parseDouble(veiculosInfo[3]), veiculosInfo[4]);
        carroAux.setTanque(veiculosInfo[5]);
        carroAux.setCombustivelTipo(veiculosInfo[6]);

        if(veiculosInfo[7].length() > 1){
            String[] rotasGeral = veiculosInfo[7].split("_");
            for (String rotas : rotasGeral) {
                if(rotas.length() > 2){
                    String[] rotasArmazenamento = rotas.split("/");
                    for (int i = 0; i < rotasArmazenamento.length; i++) {
                        if(i % 2 == 0){
                            carroAux.setRota(rotasArmazenamento[i], rotasArmazenamento[i+1]);
                        }
                    }
                }
            }
        }

        if(veiculosInfo[8].length() > 1){
            String[] combustiveisGeral = veiculosInfo[8].split("_");
            for (String combustivel : combustiveisGeral) {
                if(combustivel.length() > 2){
                    String[] combustiveisArmazenamento = combustivel.split("/");
                    for (int i = 0; i < combustiveisArmazenamento.length; i++) {
                        if(i % 2 == 0){
                            carroAux.setCombustiveisSelecionados(combustiveisArmazenamento[i], combustiveisArmazenamento[i+1]);
                        }
                    }
                }
            }
        }
        if(veiculosInfo[9] != null && veiculosInfo[9] != "" && veiculosInfo[9] != " "){
            carroAux.setCombustivelAtual(veiculosInfo[9]);
        }
        return carroAux;
    }
}
