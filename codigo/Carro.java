package codigo;

public class Carro extends Veiculo {
	Carro(double km_medio, double tanque, double valor_venda, String placa) throws Exception {
		super(km_medio, tanque, valor_venda, placa);
		precoSeguro(valor_venda);
		precoIpva(valor_venda);
		this.tipoCombustivel = Combustivel.Gasolina;
	}

	private void precoIpva(double valor_venda){
		this.preco_ipva = valor_venda * 0.04;
	}

	private void precoSeguro(double valor_venda){
		this.preco_seguro = valor_venda * 0.04 + 300;
	}

	private int precoAlinhamento(){
		double quantidadeTotalPercorrida = this.rota.stream().mapToDouble(rota -> rota.distancia_total).sum();
		int gastoAlinhamento = ((int) Math.floor(quantidadeTotalPercorrida / 10000)) * 80;
		return gastoAlinhamento;
	}

	@Override
	public void adicionar_combustivel(Combustivel tipoCombustivel, double litros) throws Exception {
		if(tipoCombustivel == Combustivel.Diesel){
			throw new Exception("Apenas permitido gasolina ou etanol");
		}
		colocarCombustivelAtual(tipoCombustivel);
		double quantidadePreenchida = quantidadeDeLitrosInseridasNoTanque(litros);
		armazenarCombustivelPreenchido(tipoCombustivel,quantidadePreenchida);
		System.out.println("O tanque está cheio");
	}
	@Override
	public double custos() {
		double precoGastoComCombustivel = calcularPrecoGastoComCombustivel();
		double precoTotal = precoGastoComCombustivel + this.preco_seguro + this.preco_ipva + precoAlinhamento();
		return precoTotal;
	}
}