package codigo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public abstract class Veiculo implements Preco{
	protected double autonomia = 0;
	protected double km_medio;
	protected double tanque = 0;
	protected Combustivel combustivelAtual;
	protected double valor_venda;
	protected double capacidade;
	protected double gastos_com_combustivel;
	protected Combustivel tipoCombustivel;
	protected List<Rota> rota;
	protected double preco_ipva;
	protected double preco_seguro;
	static private int veiculosId = 0; 
	private int id;
	protected HashMap<String, Double> combustiveisSelecionados = new HashMap<String, Double>();

	Veiculo(double km_medio, double capacidade, double valor_venda) {
		this.km_medio = km_medio;
		this.valor_venda = valor_venda;
		this.capacidade = capacidade;
		precoIpva(valor_venda);
		this.id = veiculosId;
		veiculosId ++;
	}

	private void precoIpva(double valor_venda){
		this.preco_ipva = valor_venda * 0.04;
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

	public int getId(){
		return this.id;
	}
	
	public double autonomia() {
		double autonomia = this.tanque * km_medio;
		return autonomia;
	}

	public boolean adicionarNovaRota(double distancia_total) throws Exception{
		if(this.combustivelAtual == null){
			return false;
		}
		// if(distancia_total/this.combustivelAtual.getPrecoDoLitro() > this.tanque){
		// 	return false;
		// }
		// Date dataDoMomento = new Date();
		// Rota rotaParaSerAdicionada = new Rota(dataDoMomento, distancia_total);
		// this.rota.add(rotaParaSerAdicionada);
		// this.tanque = this.tanque - distancia_total/this.combustivelAtual.getPrecoDoLitro();
		// System.out.print("Rota adicionada!");
		return true;
	}

	public boolean rota_valida(double rota) {
		if(rota <= km_medio * capacidade){
			return true;
		}
		return false;
	}
	
	public double somar_custos_veiculo(){
		return preco_ipva + preco_seguro + this.gastos_com_combustivel;
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

	public int contar_quant_rotas_por_veiculo(){
		return this.rota.size();
	}

	/**
     * Classe de relatório do pedido. (a ser melhorado)
     * @return String com detalhamento do pedido. 
     */
    public String relatorio(){
        StringBuilder relat = new StringBuilder("Veiculo\n" + getClass().getName());
        relat.append("=====================\n");
        relat.append("Id por veículo:"+ this.id);
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
