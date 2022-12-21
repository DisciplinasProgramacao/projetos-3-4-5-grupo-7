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

/**
 * Classe mãe Veículo, essa classe tem os métodos e atributos base
 * para o bom funcionamento dos outros tipos de veículos 
 */
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

	/**
	 * Construtor base da classe veículo, nele tem a base para a criação de um veículo
	 * @param km_medio
	 * @param capacidade
	 * @param valor_venda
	 * @param placa
	 * @throws Exception
	 */
	Veiculo(double km_medio, double capacidade, double valor_venda, String placa) throws Exception {
		this.km_medio = km_medio;
		this.valor_venda = valor_venda;
		this.capacidade = capacidade;
		verificarPlaca(placa);
		this.placa = placa;
		veiculosPlaca.add(placa);
	}

	/**
	 * Método para garantir que não vão existir placas repetidas e que as placas estão no
	 * formato AAA-1111
	 * @param placa
	 * @throws Exception
	 */
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

	/**
	 * Método que armazena o combustível que foi enchido em um hashmap 
	 * para que consiga somar o valor correto na hora
	 * de ver o custo com combustível 
	 * @param tipoCombustivel
	 * @param quantidadePreenchida
	 */
	protected void armazenarCombustivelPreenchido(Combustivel tipoCombustivel, double quantidadePreenchida){
		if(this.combustiveisSelecionados.get(tipoCombustivel.name()) == null){
			this.combustiveisSelecionados.put(tipoCombustivel.name(), quantidadePreenchida);
		} else {
			this.combustiveisSelecionados.put(tipoCombustivel.name(), this.combustiveisSelecionados.get(tipoCombustivel.name()) + quantidadePreenchida);
		}
	}

	/**
	 * Método para calcular o preço gasto com combustível, vai utilizar o hashmap de combustivelSelecionado
	 * @return
	 */
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

	/**
	 * Método que coloca qual o combustível que está sendo utilizado no veículo
	 * @param tipoCombustivel
	 */
	protected void colocarCombustivelAtual(Combustivel tipoCombustivel){
		if(this.tanque < this.capacidade){
			this.combustivelAtual = tipoCombustivel;
		}
	}

	/**
	 * Método get para o tanque
	 * @return
	 */
	public double getTanque(){
		return this.tanque;
	}

	/**
	 * Método get para capacidade
	 * @return
	 */
	public double getCapacidade(){
		return this.capacidade;
	}

	/**
	 * Método get para o tipo de combustível
	 * @return
	 */
	public Combustivel getTipoCombustivel(){
		return this.tipoCombustivel;
	}

	/**
	 * Método get para o hashmap que armazena os combustíveis
	 * @return
	 */
	public HashMap<String, Double> getCombustiveisSelecionados(){
		return this.combustiveisSelecionados;
	}

	/**
	 * Método get para pegar a placa
	 * @return
	 */
	public String getPlaca(){
		return this.placa;
	}
	
	/**
	 * Método que calcula a autonomia do veículo 
	 * @return
	 */
	public double autonomia() {
		double autonomia = this.tanque * km_medio;
		return autonomia;
	}
	
	/**
	 * Método que adiciona uma nova rota, sendo necessário passar apenas a distancia 
	 * que terá a rota, a data que vai ser adicionada vai ser a data do momento que foi adicionada 
	 * a rota
	 * @param distancia_total
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * Método que cria rota com base em uma string, método usado para quando vai criar uma rota a partir 
	 * de um arquivo onde foram salvas as rotas dos veículos
	 * @param data
	 * @param autonomia
	 * @throws ParseException
	 */
	public void setRota(String data, String autonomia) throws ParseException{
		SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dataFinal = dataformat.parse(data);
		Rota novaRota = new Rota(dataFinal,Double.parseDouble(autonomia));
		this.rota.add(novaRota);
	}

	/**
	 * Método que armazena a quantidade em um tanque com base em um arquivo
	 * @param tanque
	 */
	public void setTanque(String tanque){
		this.tanque = Double.parseDouble(tanque);
	}

	/**
	 * Método set para mostrar qual o combustível que foi selecionado
	 * e qual está sendo usado no veículo
	 * @param combustivel
	 */
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

	/**
	 * Método set para mostrar qual o combustível que foi selecionado
	 * e qual está sendo usado no veículo
	 * @param combustivel
	 */
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

	/**
	 * Método que vê se é possível de fazer a rota, vai medir se a rota 
	 * vai ter a quantidade de combustível necessária
	 * @param rota
	 * @return
	 */
	public boolean rota_valida(double rota) {
		if(rota <= km_medio * capacidade){
			return true;
		}
		return false;
	}

	/**
	 * Método get ipva
	 * @return
	 */
	public double IPVA() {
		return preco_ipva;
	}
	/**
	 * Método get seguro
	 * @return
	 */
	public double seguro() {
		return preco_seguro;
	}

	/**
	 * Método get rotas
	 * @return
	 */
	public List<Rota> getRota() {
		return this.rota;
	}
	
	/**
	 * Método get km medios
	 * @return
	 */
	public double getKm_medio() {
		return this.km_medio;
	}

	/**
	 * Método get valor vendas
	 * @return
	 */
	public double getValor_venda() {
        return this.valor_venda;
    }

	/**
	 * Método get combustível atual
	 * @return
	 */
	public Combustivel getCombustivelAtual() {
        return this.combustivelAtual;
    }

	/**
	 * Método que calcula quantidade de rotas feitas pelo veículo
	 * @return
	 */
	public int contar_quant_rotas_por_veiculo(){
		return this.rota.size();
	}

	/**
	 * Método que caltraz uma string com as informações do veículo
	 * @return
	 */
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

	/**
	 * Método que traz uma string com as informações do veículo, sobrescrevendo o método 
	 * @return
	 */
	@Override 
    public String toString(){
        return this.relatorio();
    }

	/**
	 * Métodos abstratos que precisam ser colocados nas classes filhas
	 * @return
	 */
	public abstract double somar_custo_veiculo();
	public abstract void adicionar_combustivel(Combustivel tipoCombustivel, double litros) throws Exception; 

}
