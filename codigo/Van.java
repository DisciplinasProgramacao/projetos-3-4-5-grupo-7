package codigo;

public class Van extends Veiculo{

	Van(double km_medio, double tanque, double valor_venda, String placa) throws Exception {
		super(km_medio, tanque, valor_venda, placa);
		precoSeguro(valor_venda);
		precoIpva(valor_venda);
		this.tipoCombustivel = Combustivel.Gasolina;
	}

	private void precoIpva(double valor_venda){
		this.preco_ipva = valor_venda * 0.03;
	}

	private int precoAlinhamento(){
		double quantidadeTotalPercorrida = this.rota.stream().mapToDouble(rota -> rota.distancia_total).sum();
		int gastoAlinhamento = ((int) Math.floor(quantidadeTotalPercorrida / 10000)) * 120;
		return gastoAlinhamento;
	}

	private int precoVistoria(){
		double quantidadeTotalPercorrida = this.rota.stream().mapToDouble(rota -> rota.distancia_total).sum();
		int gastoAlinhamento = ((int) Math.floor(quantidadeTotalPercorrida / 10000)) * 500;
		return gastoAlinhamento;
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
		double precoTotal = precoGastoComCombustivel + this.preco_seguro + this.preco_ipva + precoAlinhamento() + precoVistoria();
		return precoTotal;
	}
}
