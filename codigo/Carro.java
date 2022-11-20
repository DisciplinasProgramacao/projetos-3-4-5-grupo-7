package codigo;

public class Carro extends Veiculo {
	Carro(double km_medio, double tanque, double valor_venda, double preco_ipva, double preco_seguro) {
		super(km_medio, tanque, valor_venda, preco_ipva, preco_seguro);
	
	}
	public void adicionar_combustivel(Combustivel combustivel){
		Carro carro = new Carro(super.km_medio,super.tanque,super.valor_venda, super.preco_ipva, super.preco_seguro);
		carro.capacidade = 50;
		carro.tipoCombustivel = combustivel;
		double quantidade_a_abastecer = carro.tanque - carro.capacidade;
		carro.gastos_com_combustivel = quantidade_a_abastecer * carro.tipoCombustivel.getPrecoDoLitro();
		carro.tanque = carro.capacidade;
	}
	public void contar_quant_rotas_por_veiculo(){

	}
}
