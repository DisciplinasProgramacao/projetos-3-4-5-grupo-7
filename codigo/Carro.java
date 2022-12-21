package codigo;

/**
 * Classe que representa o veiculo carro, classe filha 
 * da classe veículo
 */
public class Carro extends Veiculo {
	/**
	 * Construtor da classe carro
	 * @param km_medio
	 * @param tanque
	 * @param valor_venda
	 * @param placa
	 * @throws Exception
	 */
	Carro(double km_medio, double tanque, double valor_venda, String placa) throws Exception {
		super(km_medio, tanque, valor_venda, placa);
		precoSeguro(valor_venda);
		precoIpva(valor_venda);
		this.tipoCombustivel = Combustivel.Gasolina;
	}

	/**
	 * Método privado que retorna o preço do ipva baseado no
	 * valor de venda
	 * @param valor_venda
	 */
	private void precoIpva(double valor_venda){
		this.preco_ipva = valor_venda * 0.04;
	}

	/**
	 * Método privado que retorna o preço do seguro baseado no
	 * valor de venda
	 * @param valor_venda
	 */
	private void precoSeguro(double valor_venda){
		this.preco_seguro = valor_venda * 0.04 + 300;
	}

	/**
	 * Calcular quanto vai ser gasto em alinhamento com base na soma da quilometragem das
	 * rotas feitas
	 * @return
	 */
	private int precoAlinhamento(){
		double quantidadeTotalPercorrida = this.rota.stream().mapToDouble(rota -> rota.distancia_total).sum();
		int gastoAlinhamento = ((int) Math.floor(quantidadeTotalPercorrida / 10000)) * 80;
		return gastoAlinhamento;
	}
	/**
	 * Método que adiciona combustível, ele seleciona apenas os combustíveis que o veículo aceita, ele coloca o valor
	 * que vai ser cheio no tanque e armazena a quantidade de combustível colocada e qual foi o combustível
	 */
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
	/**
	 * Método que soma todos os custos dos veículo
	 */
	@Override
	public double somar_custo_veiculo() {
		double precoGastoComCombustivel = calcularPrecoGastoComCombustivel();
		double precoTotal = precoGastoComCombustivel + this.preco_seguro + this.preco_ipva + precoAlinhamento();
		return precoTotal;
	}
}