package codigo;

public class Carro extends Veiculo {
	private static int carrosID = 0;
	Carro(double km_medio, double tanque, double valor_venda) {
		super(km_medio, tanque, valor_venda);
		precoSeguro(valor_venda);
		this.id = carrosID;
		carrosID++;
	}
	private void precoSeguro(double valor_venda){
		this.preco_seguro = valor_venda * 0.04;
	}

	@Override
	public void adicionar_combustivel(Combustivel tipoCombustivel, double litros) throws Exception {
		if(tipoCombustivel == Combustivel.Diesel){
			throw new Exception("Apenas permitido gasolina ou etanol");
		}
		verificar_quantidade_de_litros_inseridos_no_tanque(litros, this.capacidade);
		System.out.println("O tanque está cheio");
	}
	@Override
	public double custos() {
		// TODO Auto-generated method stub
		return 0;
	}
}