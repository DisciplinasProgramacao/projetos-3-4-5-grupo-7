package codigo;

public class FurgaoFactory {
    /**
     * Método da factory que vai criar o furgao com base em informações passadas
     * @param kmMedio
     * @param capacidade
     * @param valorVenda
     * @param placa
     * @return Furgao
     * @throws Exception
     */
    public Furgao createFurgao(Double kmMedio,Double capacidade, Double valorVenda, String placa ) throws Exception{
        Furgao furgaoAdicionado = new Furgao(kmMedio, capacidade, valorVenda, placa);
        return furgaoAdicionado;
    }

    /**
     * Método da factory que vai criar o furgao com base em uma string
     * usado para pegar informações de um arquivo e transformar em um 
     * Furgao
     * @param kmMedio
     * @param capacidade
     * @param valorVenda
     * @param placa
     * @return Furgao
     * @throws Exception
     */
    public Furgao createFurgaoString(String info) throws NumberFormatException, Exception{
        String[] veiculosInfo = info.split(",");
        Furgao furgaoAux = new Furgao(Double.parseDouble(veiculosInfo[1]), Double.parseDouble(veiculosInfo[2]), Double.parseDouble(veiculosInfo[3]), veiculosInfo[4]);
        furgaoAux.setTanque(veiculosInfo[5]);
        furgaoAux.setCombustivelTipo(veiculosInfo[6]);
        if(veiculosInfo[7].length() > 1){
            String[] rotasGeral = veiculosInfo[7].split("_");
            for (String rotas : rotasGeral) {
                if(rotas.length() > 2){
                    String[] rotasArmazenamento = rotas.split("/");
                    for (int i = 0; i < rotasArmazenamento.length; i++) {
                        if(i % 2 == 0){
                            furgaoAux.setRota(rotasArmazenamento[i], rotasArmazenamento[i+1]);
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
                            furgaoAux.setCombustiveisSelecionados(combustiveisArmazenamento[i], combustiveisArmazenamento[i+1]);
                        }
                    }
                }
            }
        }
        if(veiculosInfo[9] != null && veiculosInfo[9] != "" && veiculosInfo[9] != " "){
            furgaoAux.setCombustivelAtual(veiculosInfo[9]);
        }
        return furgaoAux;
    }
}
