package codigo;
public class Veiculo {
	protected double capacidade;
	protected double km_medio;
	protected double tanque;
	protected double valor_venda;

	Veiculo(double km_medio, double tanque, double valor_venda) {
		this.km_medio = km_medio;
		this.valor_venda = valor_venda;
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

	public double autonomia_diaria() {
		double autonomia_diaria = this.tanque * km_medio;
		return autonomia_diaria;
	}

	public void rota_valida() {
	}

	private void seguro() {

	}

	public void IPVA() {

	}

}
