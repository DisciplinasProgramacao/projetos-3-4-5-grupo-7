package app;

public class Carro extends Veiculo{
	static double capacidade = 50;
	Carro(double km_medio, double tanque, double valor_venda){
		super(capacidade, km_medio, tanque, valor_venda);
	}
	
	public double autonomia_diaria() {
		return 0;
	}
}
