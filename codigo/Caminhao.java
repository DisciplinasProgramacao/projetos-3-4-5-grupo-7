package codigo;

public class Caminhao extends Veiculo {
	Caminhao(double km_medio, double tanque, double valor_venda, double preco_ipva, double preco_seguro) {
		super(km_medio, tanque, valor_venda, preco_ipva, preco_seguro);
	}

	public int vistoria() {
		return 0;
	}

	@Override
	public void contar_quant_rotas_por_veiculo() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void adicionar_combustivel(Combustivel tipoCombustivel, double litros) throws Exception {
		if(tipoCombustivel != Combustivel.Diesel){
			throw new Exception("Apenas permitido diesel");
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
