package codigo;

import java.util.Scanner;

public class App {
	public static void main(String[] args) throws Exception {
		Scanner teclado = new Scanner(System.in); // para ler o teclado
		int opcao = 1;// escolha da operação pelo usuário
		String nome = "";
		Frota frota = new Frota();

		do {
			System.out.println("Frota de carros");
			System.out.println("1 - Carregar um conjunto de veículos de um arquivo");
			System.out.println("2 - Salvar um conjunto de veículos de um arquivo");
			System.out.println("3 - Incluir Novo veículo");
			System.out.println("4 - Localizar veiculo da Frota");
			System.out.println("0 - Sair");
			System.out.println("Digite opção");
			opcao = Integer.parseInt(teclado.nextLine());

			if (opcao == 0) { // termina o sistema se a opÃ§Ã£o for 0
				System.out.println("Obrigado e volte sempre!");
				teclado.close();
				return;
			}
			switch (opcao) {
				case 1:
					System.out.println("Colocar Arquivo");
					System.out.println(frota.addVeiculos("codigo/adicionar.csv"));
					break;
				case 2:
					System.out.println("Salvar veículos");
					frota.saveVeiculos("nome");
					break;
				case 3:
					System.out.println("Adicionar novo veiculo");

					break;
				case 0:
					return;
			}
		} while (opcao != 0); // repete-se atÃ© que o usuÃ¡rio queira sair.
		teclado.close();
	}
}
