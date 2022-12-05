package codigo;

import java.util.Scanner;



public class App {
	public static Combustivel retornaCombustivel(String combustivel){
		System.out.print(combustivel);
		switch(combustivel) {
			case "1":
				return Combustivel.Diesel;
			case "2":
				return Combustivel.Gasolina;
			case "3":
				return Combustivel.Etanol;
			default:
				return null;
		}
	}

	public static Veiculo retornaVeiculoEspecificado(String veiculo, String id, Frota frota){
		switch(veiculo){
			case "Carro":
				return frota.pegarCarroEspecifico(id);
			case "Caminhao":
				return frota.pegarCaminhaoEspecifico(id);
			case "Van":
				return frota.pegarVanEspecifico(id);
			case "Furgao":
				return frota.pegarFurgaoEspecifico(id);
			default:
				return null;
		}
	}
	public static void main(String[] args) throws Exception {
		Scanner teclado = new Scanner(System.in); // para ler o teclado
		int opcao = 1;// escolha da operação pelo usuário
		Frota frota = new Frota();
		frota.addVeiculosCompletos("codigo/salvar.csv");
		do {
			System.out.println("Frota de carros");
			System.out.println("1 - Carregar um conjunto de veículos de um arquivo");
			System.out.println("2 - Salvar um conjunto de veículos de um arquivo");
			System.out.println("3 - Incluir Novo veículo");
			System.out.println("4 - Incluir Nova rota");
			System.out.println("5 - Localizar veiculo da Frota");
			System.out.println("6 - Custos totais de cada veiculo da frota");
			System.out.println("0 - Sair");
			System.out.println("Digite opção");
			opcao = Integer.parseInt(teclado.nextLine());

			if (opcao == 0) { // termina o sistema se a opção for 0
				System.out.println("Obrigado e volte sempre!");
				teclado.close();
				return;
			}
			switch (opcao) {
				case 1:
					System.out.println("Colocar caminho relativo Arquivo");
					frota.addVeiculos("codigo/adicionar.csv");
					frota.saveVeiculos("codigo/salvar.csv");
					break;
				case 3:
					System.out.println("Adicionar novo veiculo");
					System.out.println("km_medio");
					String kmMedio = teclado.nextLine();
					System.out.println("capacidade");
					String capacidade = teclado.nextLine();
					System.out.println("valor venda");
					String valorVenda = teclado.nextLine();
					System.out.println("Placa do veiculo");
					String placaVeiculo = teclado.nextLine();
					System.out.println("Carro 1, Caminhao 2, Van 3, Furgao 4");
					String veiculoSelecionado = teclado.nextLine();
					frota.addVeiculo(kmMedio, capacidade, valorVenda, veiculoSelecionado, placaVeiculo);
					frota.saveVeiculos("codigo/salvar.csv");
					break;
				case 4:
					System.out.print("Adicionar rota em veiculo: \n\n");
					frota.getListaVeiculos().stream().forEach(veiculo -> {
						System.out.print(veiculo);
					});
					System.out.print("Inserir placa de veiculo \n\n");
					String placa = teclado.nextLine();
					System.out.print("Quilometragem de rota de veiculo");
					String quilometragemRota = teclado.nextLine();
					Veiculo veiculoParaEncher = frota.pegarVeiculoEspecifico(placa);
					System.out.print(veiculoParaEncher.tanque);
					if(veiculoParaEncher.adicionarNovaRota(Double.parseDouble(quilometragemRota)) == false){
						System.out.print("Veiculo sem combustivel");
						System.out.print("Encher combustivel \n");
						System.out.print("1 Diesel, 2 Gasolina, 3 Etanol");
						String combustivelEscolhido = teclado.nextLine();
						System.out.print("Quantidade de combustivel que sera colocada");
						String quantidadeDeCombustivelASerColocada = teclado.nextLine();
						Combustivel combustivelParaAbastecer = retornaCombustivel(combustivelEscolhido);
						if(combustivelParaAbastecer != null){
							var veiculoSelecionadoParaAbstecer = retornaVeiculoEspecificado(veiculoParaEncher.getClass().getSimpleName(), placa, frota);
							veiculoSelecionadoParaAbstecer.adicionar_combustivel(combustivelParaAbastecer, Double.parseDouble(quantidadeDeCombustivelASerColocada));
							System.out.print("Repita o processo para adicionar combustivel");
						} else {
							throw new Exception("Repita de novo o processo com o numero correto de combustivel");
						}
						
					};
					frota.saveVeiculos("codigo/salvar.csv");
					break;
				case 5:
					System.out.print("Veiculos: \n\n");
					frota.getListaVeiculos().stream().forEach(veiculo -> {
						System.out.print(veiculo);
					});
					break;
				case 6:
					frota.getListaDeVeiculosComPreco();
					break;
				case 0:
					return;
			}
		} while (opcao != 0); // repete-se até que o usuário queira sair.
		teclado.close();
	}
}
