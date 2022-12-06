package codigo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Veiculo implements Preco{
	protected double autonomia = 0;
	protected double km_medio;
	protected double tanque = 0;
	protected Combustivel combustivelAtual;
	protected double valor_venda;
	protected double capacidade;
	protected Combustivel tipoCombustivel;
	protected List<Rota> rota = new ArrayList<Rota>();
	protected double preco_ipva;
	protected double preco_seguro;
	static private List<String> veiculosPlaca = new LinkedList<String>(); 
	private String placa;
	protected HashMap<String, Double> combustiveisSelecionados = new HashMap<String, Double>();

	Veiculo(double km_medio, double capacidade, double valor_venda, String placa) throws Exception {
		this.km_medio = km_medio;
		this.valor_venda = valor_venda;
		this.capacidade = capacidade;
		verificarPlaca(placa);
		this.placa = placa;
		veiculosPlaca.add(placa);
	}

	private void verificarPlaca(String placa) throws Exception{
		String regex = "^[A-Z]{3}\\-[0-9]{4}";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(placa);
		if(veiculosPlaca.contains(placa)){
			throw new Exception("Ja existe veiculo com essa placa");
		}
		if(m.matches() == false){
			throw new Exception("Formato da placa incorreto deve ser EX: ABC-1234");
		}
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
	protected double quantidadeDeLitrosInseridasNoTanque(double valorASerEnchido) {
		if (this.tanque + valorASerEnchido >= this.capacidade){
			double valorASerEnchidoFinal = this.capacidade - this.tanque;
			this.tanque = valorASerEnchidoFinal + this.tanque;
			return valorASerEnchidoFinal;
		} else {
			this.tanque = valorASerEnchido;
			return valorASerEnchido;
		}
	}

	protected void armazenarCombustivelPreenchido(Combustivel tipoCombustivel, double quantidadePreenchida){
		if(this.combustiveisSelecionados.get(tipoCombustivel.name()) == null){
			this.combustiveisSelecionados.put(tipoCombustivel.name(), quantidadePreenchida);
		} else {
			this.combustiveisSelecionados.put(tipoCombustivel.name(), this.combustiveisSelecionados.get(tipoCombustivel.name()) + quantidadePreenchida);
		}
	}

	protected double calcularPrecoGastoComCombustivel(){
		double precoGastoEmCombustivel = 0;
		for (Entry<String, Double> pair :this.combustiveisSelecionados.entrySet()) {
			switch(pair.getKey()) {
				case "Diesel":
					precoGastoEmCombustivel = precoGastoEmCombustivel + pair.getValue() * Combustivel.Diesel.getPrecoDoLitro();
				  break;
				case "Gasolina":
					precoGastoEmCombustivel = precoGastoEmCombustivel + pair.getValue() * Combustivel.Gasolina.getPrecoDoLitro();
				  break;
				case "Etanol":
					precoGastoEmCombustivel = precoGastoEmCombustivel + pair.getValue() * Combustivel.Etanol.getPrecoDoLitro();
				  break;
			}
		}
		return precoGastoEmCombustivel;
	}

	protected void colocarCombustivelAtual(Combustivel tipoCombustivel){
		if(this.tanque < this.capacidade){
			this.combustivelAtual = tipoCombustivel;
		}
	}

	public double getTanque(){
		return this.tanque;
	}

	public double getCapacidade(){
		return this.capacidade;
	}

	public Combustivel getTipoCombustivel(){
		return this.tipoCombustivel;
	}

	public HashMap<String, Double> getCombustiveisSelecionados(){
		return this.combustiveisSelecionados;
	}
	public String getPlaca(){
		return this.placa;
	}
	
	public double autonomia() {
		double autonomia = this.tanque * km_medio;
		return autonomia;
	}

	public boolean adicionarNovaRota(double distancia_total) throws Exception{
		if(this.combustivelAtual == null){
			return false;
		}
		if(distancia_total/this.combustivelAtual.getQuilometragemPorLitro() > this.tanque){
			return false;
		}
		Date dataDoMomento = new Date();
		Rota rotaParaSerAdicionada = new Rota(dataDoMomento, distancia_total);
		this.rota.add(rotaParaSerAdicionada);
		this.tanque = this.tanque - distancia_total/this.combustivelAtual.getQuilometragemPorLitro();
		System.out.println("Rota adicionada!");

		return true;
	}

	/// set para escrever a partir de Arquivo
	public void setCombustiveisSelecionados(String tipoCombustivel, String quantidadePreenchida){
		this.combustiveisSelecionados.put(tipoCombustivel, Double.parseDouble(quantidadePreenchida));
	}

	public void setRota(String data, String autonomia) throws ParseException{
		SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dataFinal = dataformat.parse(data);
		Rota novaRota = new Rota(dataFinal,Double.parseDouble(autonomia));
		this.rota.add(novaRota);
	}

	public void setTanque(String tanque){
		this.tanque = Double.parseDouble(tanque);
	}

	public void setCombustivelAtual(String combustivel ){
		switch(combustivel){
			case "Diesel":
				this.combustivelAtual = Combustivel.Diesel;
			break;
			case "Gasolina":
				this.combustivelAtual = Combustivel.Gasolina;
			break;
			case "Etanol":
				this.combustivelAtual = Combustivel.Etanol;
			break;
		}
	}

	public void setCombustivelTipo(String tanque){
		switch(tanque){
			case "Diesel":
				this.tipoCombustivel = Combustivel.Diesel;
			break;
			case "Gasolina":
				this.tipoCombustivel = Combustivel.Gasolina;
			break;
			case "Etanol":
				this.tipoCombustivel = Combustivel.Etanol;
			break;
		}
	}

	public boolean rota_valida(double rota) {
		if(rota <= km_medio * capacidade){
			return true;
		}
		return false;
	}

	public double IPVA() {
		return preco_ipva;
	}

	public double seguro() {
		return preco_seguro;
	}

	public List<Rota> getRota() {
		return this.rota;
	}
	
	public double getKm_medio() {
		return this.km_medio;
	}

	public double getValor_venda() {
        return this.valor_venda;
    }

	
	public Combustivel getCombustivelAtual() {
        return this.combustivelAtual;
    }

	public int contar_quant_rotas_por_veiculo(){
		return this.rota.size();
	}

    public String relatorio(){
        StringBuilder relat = new StringBuilder("Veiculo\n" + getClass().getName());
        relat.append("=====================\n");
        relat.append("Placa veiculo:"+ this.placa);
		relat.append("=====================\n");
		relat.append("Autonomia:"+this.autonomia);
		relat.append("=====================\n");
		relat.append("Km médio:"+this.km_medio);
        relat.append("=====================\n");
        return relat.toString();
        
    }

	@Override 
    public String toString(){
        return this.relatorio();
    }

	public abstract double custos ();
	public abstract void adicionar_combustivel(Combustivel tipoCombustivel, double litros) throws Exception; 

}
