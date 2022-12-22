package codigo;

public class VanFactory {
    /**
     * Método da factory que vai criar a van com base em informações passadas
     * @param kmMedio
     * @param capacidade
     * @param valorVenda
     * @param placa
     * @return Van
     * @throws Exception
     */
    public Van createVan(Double kmMedio,Double capacidade, Double valorVenda, String placa ) throws Exception{
        Van vanAdicionado = new Van(kmMedio, capacidade, valorVenda, placa);
        return vanAdicionado;
    }

    /**
     * Método da factory que vai criar a van com base em uma string
     * usado para pegar informações de um arquivo e transformar em uma
     * van
     * @param kmMedio
     * @param capacidade
     * @param valorVenda
     * @param placa
     * @return Van
     * @throws Exception
     */
    public Van createVanString(String veiculo) throws NumberFormatException, Exception{
        String[] veiculosInfo = veiculo.split(",");
        Van vanAux = new Van(Double.parseDouble(veiculosInfo[1]), Double.parseDouble(veiculosInfo[2]), Double.parseDouble(veiculosInfo[3]), veiculosInfo[4]);
        vanAux.setTanque(veiculosInfo[5]);
        vanAux.setCombustivelTipo(veiculosInfo[6]);
        if(veiculosInfo[7].length() > 1){
            String[] rotasGeral = veiculosInfo[7].split("_");
            for (String rotas : rotasGeral) {
                if(rotas.length() > 2){
                    String[] rotasArmazenamento = rotas.split("/");
                    for (int i = 0; i < rotasArmazenamento.length; i++) {
                        if(i % 2 == 0){
                            vanAux.setRota(rotasArmazenamento[i], rotasArmazenamento[i+1]);
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
                            vanAux.setCombustiveisSelecionados(combustiveisArmazenamento[i], combustiveisArmazenamento[i+1]);
                        }
                    }
                }
            }
        }
        if(veiculosInfo[9] != null && veiculosInfo[9] != "" && veiculosInfo[9] != " "){
            vanAux.setCombustivelAtual(veiculosInfo[9]);
        }
        return vanAux;
    }
}
