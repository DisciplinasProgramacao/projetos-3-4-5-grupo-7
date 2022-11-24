package codigo;

import java.util.Date;
import java.util.List;

public abstract class Veiculo implements Preco{
	protected double autonomia;
	protected double km_medio;
	protected double tanque;
	protected double valor_venda;
	protected double capacidade;
	protected double gastos_com_combustivel;
	protected Combustivel tipoCombustivel;
	protected List<Rota> rota;
	protected double preco_ipva;
	protected double preco_seguro;
	protected int id;

	Veiculo(double km_medio, double capacidade, double valor_venda) {
		this.km_medio = km_medio;
		this.valor_venda = valor_venda;
		this.capacidade = capacidade;
		precoIpva(valor_venda);
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
	protected void verificar_quantidade_de_litros_inseridos_no_tanque(double capacidade, double tanque) {
		if (tanque <= capacidade)
			this.tanque = tanque;
		else
			this.tanque = capacidade;
	}
	
	public double autonomia() {
		double autonomia = this.tanque * km_medio;
		return autonomia;
	}

	public void adicionarNovaRota(double distancia_total){
		Date dataDoMomento = new Date();
		Rota rotaParaSerAdicionada = new Rota(dataDoMomento, distancia_total);
		this.rota.add(rotaParaSerAdicionada);
		System.out.print("Rota adicionada!");
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
        relat.append("Id veículo:"+ this.id);
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
