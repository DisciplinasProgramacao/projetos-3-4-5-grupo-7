package codigo;

import java.util.Map;
import java.util.Map.Entry;

public class Caminhao extends Veiculo {
	Caminhao(double km_medio, double tanque, double valor_venda, String placa) throws Exception {
		super(km_medio, tanque, valor_venda, placa);
		precoSeguro(valor_venda);
		precoIpva(valor_venda);
		this.tipoCombustivel = Combustivel.Diesel;
	}

	private void precoIpva(double valor_venda){
		this.preco_ipva = valor_venda * 0.01;
	}

	private void precoSeguro(double valor_venda){
		this.preco_seguro = valor_venda * 0.02 + 2000;
	}

	private int precoVistoria(){
		double quantidadeTotalPercorrida = this.rota.stream().mapToDouble(rota -> rota.distancia_total).sum();
		int gastoAlinhamento = ((int) Math.floor(quantidadeTotalPercorrida / 30000)) * 1000;
		return gastoAlinhamento;
	}

	private int precoManutencao(){
		double quantidadeTotalPercorrida = this.rota.stream().mapToDouble(rota -> rota.distancia_total).sum();
		int gastoAlinhamento = ((int) Math.floor(quantidadeTotalPercorrida / 20000)) * 1000;
		return gastoAlinhamento;
	}

	public int vistoria() {
		return 0;
	}

	@Override
	public void adicionar_combustivel(Combustivel tipoCombustivel, double litros) throws Exception {
		if(tipoCombustivel != Combustivel.Diesel){
			throw new IllegalAccessError("Apenas permitido diesel");
		}
		colocarCombustivelAtual(tipoCombustivel);
		double quantidadePreenchida = quantidadeDeLitrosInseridasNoTanque(litros);
		armazenarCombustivelPreenchido(tipoCombustivel,quantidadePreenchida);
		System.out.println("O tanque está cheio");
	}

	@Override
	public double custos() {
		double precoGastoComCombustivel = calcularPrecoGastoComCombustivel();
		double precoTotal = precoGastoComCombustivel + this.preco_seguro + this.preco_ipva + precoManutencao() + precoVistoria();
		return precoTotal;
	}
}
