package codigo;

import java.util.List;

public abstract class Veiculo {
	protected double autonomia;
	protected double km_medio;
	protected double tanque;
	protected double valor_venda;
	protected double capacidade;
	protected double gastos_com_combustivel;
	protected Combustivel tipoCombustivel;
	private double custo_veiculo;
	private List rota;
	protected double preco_ipva;
	protected double preco_seguro;

	Veiculo(double km_medio, double tanque, double valor_venda, double preco_ipva, double preco_seguro) {
		this.km_medio = km_medio;
		this.valor_venda = valor_venda;
		this.preco_ipva = preco_ipva;
		this.preco_seguro = preco_seguro;
		
		verificar_quantidade_de_litros_inseridos_no_tanque(capacidade, tanque);
	}

	/**
	 * Verificar se a quantidade de combustivel inserida no tanque respeita a
	 * capacidade maxima do veiculo,
	 * se for, a quantidade inserida sera matida, se nao, a quantidade de
	 * combustivel a mais sera ignorada.
	 * 
	 * @param capacidade a capacidade maxima permitida no tanque do veiculo
	 * @param tanque     a quantidade de combustivel inserida no veiculo
	 */
	private void verificar_quantidade_de_litros_inseridos_no_tanque(double capacidade, double tanque) {
		if (tanque <= capacidade)
			this.tanque = tanque;
		else
			this.tanque = capacidade;
	}

	public double autonomia() {
		double autonomia = this.tanque * km_medio;
		return autonomia;
	}

	public boolean rota_valida(double rota) {
		if(rota <= km_medio * capacidade){
			if(rota < km_medio * autonomia){
				Veiculo veiculo = new Veiculo(this.km_medio,this.tanque,this.valor_venda, this.preco_ipva, this.preco_seguro);
				veiculo.adicionar_combustivel();
			}
			return true;
		}
		return false;
	}
	
	public double somar_custos_veiculo(){
		return preco_ipva + preco_seguro + this.gastos_com_combustivel;
	}

	private double IPVA() {
		return preco_ipva;
	}

	private double seguro() {
		return preco_seguro;
	}
	
	public abstract void adicionar_combustivel(); 
	public abstract void contar_quant_rotas_por_veiculo(); 
}
