package codigo;

import java.text.SimpleDateFormat;
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
		return ValorData() + "|" + this.distancia_total;
	}

	private String ValorData(){
		Date dataNormal = this.data;
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dataFinalAux = dt.format(dataNormal);
		return dataFinalAux;
	}

}
