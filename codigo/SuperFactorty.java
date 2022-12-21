package codigo;

import java.util.ArrayList;
import java.util.List;

public class SuperFactorty {
    public Veiculo addVeiculo(String kmMedio,String capacidade, String valorVenda, String veiculoSelecionado, String placa ) throws NumberFormatException, Exception{
        switch (veiculoSelecionado) {
            case "1":
                CarroFactory carroFactory = new CarroFactory();
                Carro carroAdicionado = carroFactory.createCarro(Double.parseDouble(kmMedio),Double.parseDouble(capacidade),Double.parseDouble(valorVenda), placa);
                return carroAdicionado;
            case "2":
                CaminhaoFactory caminhaoFactory = new CaminhaoFactory();
                Caminhao caminhaoAdicionado = caminhaoFactory.createCaminhao(Double.parseDouble(kmMedio),Double.parseDouble(capacidade),Double.parseDouble(valorVenda), placa);
                return caminhaoAdicionado;
            case "3":
                VanFactory vanFactory = new VanFactory();
                Van vanAdicionado = vanFactory.createVan(Double.parseDouble(kmMedio),Double.parseDouble(capacidade),Double.parseDouble(valorVenda), placa);
                return vanAdicionado;
            case "4":
                FurgaoFactory furgaoFactory = new FurgaoFactory();
                Furgao furgaoAdicionado = furgaoFactory.createFurgao(Double.parseDouble(kmMedio),Double.parseDouble(capacidade),Double.parseDouble(valorVenda), placa);
                return furgaoAdicionado;
            default:
                System.out.print("Nao existe essa opcao");
                break;
        }
        return null;
    }

    public List<Veiculo> addVeiculos(String arquivo) throws NumberFormatException, Exception{
        CsvReader newVeiculos = new CsvReader(arquivo);
        List<Veiculo> listVeiculos = new ArrayList<Veiculo>();
        List<String> arrayVeiculos = newVeiculos.returnArrayNumbers();
        for (String veiculo : arrayVeiculos) {
            String[] veiculosInfo = veiculo.split(",");
            switch (veiculosInfo[0]) {
                case "Caminhao":
                    CaminhaoFactory caminhaoFactory = new CaminhaoFactory();
                    Caminhao caminhaoAdd = caminhaoFactory.createCaminhaoString(veiculo);
                    listVeiculos.add(caminhaoAdd);
                    break;
                case "Carro":
                    CarroFactory carroFactory = new CarroFactory();
                    Carro carroAdd = carroFactory.createCarroString(veiculo);
                    listVeiculos.add(carroAdd);
                    break;
                case "Van":
                    VanFactory vanFactory = new VanFactory();
                    Van vanAdd = vanFactory.createVanString(veiculo);
                    listVeiculos.add(vanAdd);
                    break;
                case "Furgao":
                    FurgaoFactory furgaoFactory = new FurgaoFactory();
                    Furgao furgaoAdd = furgaoFactory.createFurgaoString(veiculo);
                    listVeiculos.add(furgaoAdd);
                    break;
            }
        }
        System.out.print("Veiculos adicionados");
        return listVeiculos;
    }
}
