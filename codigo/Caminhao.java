package codigo;

public class Caminhao extends Veiculo {
	Caminhao(double km_medio, double tanque, double valor_venda, double preco_ipva, double preco_seguro) {
		super(km_medio, tanque, valor_venda, preco_ipva, preco_seguro);
		// TODO Auto-generated constructor stub
	}


	public void adicionar_combustivel(double litros){

		if (this.tanque < this.capacidade) {
			this.tanque =+ litros;
		} else {
			System.out.println("O tanque está cheio");
		}
	}

	@Override
	public void adicionar_combustivel() {
	}
	public int vistoria() {
		return 0;
	}
}
