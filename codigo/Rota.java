package codigo;

import java.util.Date;

public class Rota {
	protected Date data;
	protected double distancia_total;
	
	public Rota(Date data, double distancia_total) {
		this.data =  data;
		this.distancia_total = distancia_total;
	}
	
	public Date getData() {
		return this.data;
	}

	public double getDistancia_total() {
		return this.distancia_total;
	}

	@Override
	public String toString(){
		return this.data.toString() + "|" + this.distancia_total;
	}

}
