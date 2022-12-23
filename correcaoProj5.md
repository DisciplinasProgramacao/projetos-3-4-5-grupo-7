# Projeto 5 - Gestão de Frota (final)

## Nota: 9,5 + apresentação

**A nota final** se dará pela soma acima, multiplicada por um peso entre 0 e 1 relativo ao acompanhamento semanal do projeto. Este peso é atribuído **individualmente**. Lembre-se: não é só a entrega do produto finalizado que importa, é todo o processo de sua construção e as entregas parciais para o “cliente”
	
## Comentários
	- não compilou (sem implementar métodos abstratos)
	- case 2 não funciona no main
	- fazendo leitura de km médio
	
## Correção

### Modelagem: 1,5/2   
	- persiste a falta de modularização de Tanque
	- fazendo leitura de km médio e capacidade (propriedades do combustível, veículo)
	
### Persistência de dados: 4/4   
	- arquivo de teste: 1/1
	- salvar e carregar: 3/3 
	-- modularidade: reader/writer não deveriam ter classe base/interface comum?
	

### Robustez: 2/4 (+2 - revisão de nota)
	- menu principal: 0,5/1 (exceção em formato de placa mata o sistema)
	- regras inválidas do projeto: 1,5/3 (exceção não tratada de combustível mata o sistema) 
	
### Padrão de projeto implementado: 2/5 (+1,5 - não faz sentido criar uma nova fábrica a cada chamada. A Fábrica deve existir junto com o sistema)
	- Fábricas individuais ok
	- Não entendi direito o que faz "SuperFactory" (sem documentação/interface)
	- Fábricas não chamadas no principal 
	
	
### Documentação e apresentação:  5 pontos (pontos de documentação adicionados)
	- continua sem documentação
	- nota individual de acordo com a documentação e participação do aluno nas apresentações realizadas ao longo do Projeto

