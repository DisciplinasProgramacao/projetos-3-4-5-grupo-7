package codigo;

public class Van extends Veiculo{
	private static int vanID = 0;
	Van(double km_medio, double tanque, double valor_venda) {
		super(km_medio, tanque, valor_venda);
		this.id = vanID;
		vanID++;
	}
	public void contar_quant_rotas_por_veiculo(){

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
