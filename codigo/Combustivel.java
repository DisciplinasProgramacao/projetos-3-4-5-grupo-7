package codigo;

/**
 * O enum combustivel serve para armazenar os dados de cada tipo de combustivel
 * cada combustivel tem dois dados especificos, sendo o primeiro deles a
 * quilometragem por livro
 * e o segundo, o preço daquele combustivel por litro.
 */
public enum Combustivel {
  Gasolina(12.0, 4.80), Etanol(8.0, 3.65), Diesel(3.5, 6.65);

  private double quilometragemPorLitro;
  private double precoDoLitro;

  Combustivel(double quilometragemPorLitro, double precoDoLitro) {
    this.quilometragemPorLitro = quilometragemPorLitro;
    this.precoDoLitro = precoDoLitro;
  }

  public double getPrecoDoLitro() {
    return precoDoLitro;
  }

  public double getQuilometragemPorLitro() {
    return quilometragemPorLitro;
  }
}
