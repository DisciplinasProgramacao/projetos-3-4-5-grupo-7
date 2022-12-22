package codigo;

public class CaminhaoFactory {
    /**
     * Método da factory que vai criar o caminhao com base em informações passadas
     * @param kmMedio
     * @param capacidade
     * @param valorVenda
     * @param placa
     * @return Caminhao
     * @throws Exception
     */
    public Caminhao createCaminhao(Double kmMedio,Double capacidade, Double valorVenda, String placa ) throws Exception{
        Caminhao caminhaoAdicionado = new Caminhao(kmMedio, capacidade, valorVenda, placa);
        return caminhaoAdicionado;
    }

    /**
     * Método da factory que vai criar o caminhao com base em uma string
     * usado para pegar informações de um arquivo e transformar em um 
     * caminhão
     * @param kmMedio
     * @param capacidade
     * @param valorVenda
     * @param placa
     * @return Caminhao
     * @throws Exception
     */
    public Caminhao createCaminhaoString(String info) throws NumberFormatException, Exception{
        String[] veiculosInfo = info.split(",");
        Caminhao caminhaoAux = new Caminhao(Double.parseDouble(veiculosInfo[1]), Double.parseDouble(veiculosInfo[2]), Double.parseDouble(veiculosInfo[3]), veiculosInfo[4]);
        caminhaoAux.setTanque(veiculosInfo[5]);
        caminhaoAux.setCombustivelTipo(veiculosInfo[6]);
        if(veiculosInfo[7].length() > 1){
            String[] rotasGeral = veiculosInfo[7].split("_");
            for (String rotas : rotasGeral) {
                if(rotas.length() > 2){
                    System.out.print(rotas);
                    String[] rotasArmazenamento = rotas.split("/");
                    for (int i = 0; i < rotasArmazenamento.length; i++) {
                        if(i % 2 == 0){
                        caminhaoAux.setRota(rotasArmazenamento[i], rotasArmazenamento[i+1]);
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
                            caminhaoAux.setCombustiveisSelecionados(combustiveisArmazenamento[i], combustiveisArmazenamento[i+1]);
                        }
                    }
                }
            }
        }

        if(veiculosInfo[9] != null && veiculosInfo[9] != "" && veiculosInfo[9] != " "){
            caminhaoAux.setCombustivelAtual(veiculosInfo[9]);
        }
            
        return caminhaoAux;
    }
}
