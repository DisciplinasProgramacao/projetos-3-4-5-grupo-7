package codigo;

public class Carro extends Veiculo {
	Carro(double km_medio, double tanque, double valor_venda, double preco_ipva, double preco_seguro) {
		super(km_medio, tanque, valor_venda, preco_ipva, preco_seguro);
	
	}
	public void contar_quant_rotas_por_veiculo(){

	}
	@Override
	public void adicionar_combustivel(Combustivel tipoCombustivel, double litros) throws Exception {
		if(tipoCombustivel == Combustivel.Diesel){
			throw new Exception("Apenas permitido gasolina ou etanol");
		}
		verificar_quantidade_de_litros_inseridos_no_tanque(litros, this.capacidade);
		System.out.println("O tanque está cheio");
	}
}