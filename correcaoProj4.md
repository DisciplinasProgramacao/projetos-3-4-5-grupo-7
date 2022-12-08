# Projeto 4 - Gestão de Frota (parte 2)

## Nota: 10,3

## Comentários
	- Continua sem compilar 😔
	- Métodos abstratos sem sobreposição, erros de retorno etc. 
	- App ainda sem rodar pelos erros nas classes
	- Menu do app sem todas as opções
	- Uma sugestão: concentrem-se em uma coisa por vez. Não adianta ter 4 classes de veículo se duas delas não funcionam. Primeiro, façam funcionar uma classe. Depois, partam para a outra. Usar teste é muito bom para isso.
	- Não se pode criar um novo carro, dentro de Carro, só para o abastecimento. Devem ser modificados os atributos do carro. 
	
## Correção
	
	
### Modelagem: 1,8/2   
	- Modularização de tanque e combustível (melhor seria ter um tanque como classe)
	
### Requisitos dos veículos, de acordo com a modelagem: 4/9  
	- Restrição de combustível: 0/3  -- qualquer veículo usa qualquer combustível
	- Abastecimento e autonomia: 2/3 -- regra da autonomia ok, do abastecimento não (olhar comentários acima.
	- Custos fixos e variáveis: 2/3 -- só tem combustível como variável
	
### Requisitos da empresa no programa principal: 4,5/9 
	
	- Quilometragem média das rotas da empresa: 2,5/3 -- há um 'average' no stream para não precisar fazer duas streams e a conta manual 
	- Filtro para busca de rotas por data: 0/3  
	- Um dos dois abaixo: 2/3 - o uso do sorted na stream está incorreto (e usando um método que ainda não existe)
		- Os 3 veículos que mais fizeram rotas 
		- Lista de veículos ordenada decrescentemente por custos gerados 

