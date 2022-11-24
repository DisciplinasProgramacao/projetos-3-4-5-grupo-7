package codigo;

public class Furgao extends Veiculo{
	private static int furgaoID = 0;
	Furgao(double km_medio, double tanque, double valor_venda) {
		super(km_medio, tanque, valor_venda);
		precoSeguro(valor_venda);
		this.id = furgaoID;
		furgaoID++;
	}
	private void precoSeguro(double valor_venda){
		this.preco_seguro = valor_venda * 0.03;
	}

	@Override
	public void adicionar_combustivel(Combustivel tipoCombustivel, double litros) throws Exception {
		if(tipoCombustivel == Combustivel.Gasolina){
			throw new Exception("Apenas permitido gasolina");
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
