package app;

public class Veiculo {
	private double capacidade;
	private double km_medio;
	private double tanque;
	private double valor_venda;

	Veiculo(double capacidade, double km_medio, double tanque, double valor_venda){
		this.capacidade = capacidade;
		this.km_medio = km_medio;
		this.tanque = tanque;
		this.valor_venda = valor_venda;
	}

	public double autonomia_diaria() {
		return 0;
	}
	
	public void rota_valida() {
	}
	
	public void seguro(){

	}
	public void IPVA(){

	}

}
