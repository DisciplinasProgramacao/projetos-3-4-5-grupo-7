package codigo;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Classe que representa a rota, ela é composta pela data que aconteceu a rota 
 * e a distancia da rota
 */
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
		return ValorData() + "/" + this.distancia_total;
	}

	/**
	 * Método que retorna um valor de data dentro do formato yyyy-MM-dd HH:mm:ss, 
	 * para facilitar a leitura quando as informações forem gravadas em um arquivo
	 * @return
	 */
	private String ValorData(){
		Date dataNormal = this.data;
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dataFinalAux = dt.format(dataNormal);
		return dataFinalAux;
	}

}
