package codigo;

public class App {
	public static void main(String[] args){
		CsvReader leitor = new CsvReader("codigo/Pasta1.csv");
		System.out.print(leitor.returnArrayNumbers());
	}
}

