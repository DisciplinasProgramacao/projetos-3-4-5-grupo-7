package codigo;

public class Furgao extends Veiculo{
	Furgao(double km_medio, double tanque, double valor_venda, String placa) throws Exception {
		super(km_medio, tanque, valor_venda, placa);
		precoSeguro(valor_venda);
	}
	private void precoSeguro(double valor_venda){
		this.preco_seguro = valor_venda * 0.03;
	}

	@Override
	public void adicionar_combustivel(Combustivel tipoCombustivel, double litros) throws Exception {
		if(tipoCombustivel == Combustivel.Gasolina){
			throw new Exception("Apenas permitido gasolina");
		}
		colocarCombustivelAtual(tipoCombustivel);
		double quantidadePreenchida = quantidadeDeLitrosInseridasNoTanque(litros);
		armazenarCombustivelPreenchido(tipoCombustivel,quantidadePreenchida);
		System.out.println("O tanque está cheio");
		
	}
	@Override
	public double custos() {
		double precoGastoComCombustivel = calcularPrecoGastoComCombustivel();
		return 0;
	}
	
}
