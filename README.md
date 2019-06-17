# RepositorioExercicios
Criar um Projeto Java Web, seguindo as regras de CRUD (Create, Read, Update e Delete).

Comentários para as questões 1 e 2 abaixo.

A aplicação foi criada utilizando as seguintes ferramentas:
*front-end: Java Server Faces 2.1 com Prime Faces 5.0 
*Persistência: Eclipse Link (JPA)
*IDE: NetBeans
*Banco de Dados: MySql (script para criação do banco com dados para testes está no repositório)

A aplicação foi desenvolvida basicamente com a utilização de facades, controllers e entity classes mapeadas a partir 
do modelo de dados criado para este projeto.

Foram criadas 5 tabelas para o sistema: Projeto, Usuario, Empresa, Status (status do projeto) e ProjetoUsuario. A partir destas tabelas
foram geradas as entity classes e consequentemente os controllers e as facades, sempre obedecendo à estrutura inicial pensada para o banco de dados.

"•	No cadastramento de um novo projeto, deve-se verificar se já existe um projeto ativo para a empresa e ativo no período 
(vigência = data de ativação e desativação), caso exista um projeto no período para a empresa, o sistema deverá cadastrar 
o projeto com data de ativação a partir da data de desativação da última vigência, registrar log. Por fim, efetuar o cadastramento 
e retornar mensagem de que projeto já existia na vigência informada e retornar a vigência criada: "

- para solucionar esta demanda foi necessário fazer uma consulta paralela ao banco de dados buscando a mais futura data de desativação
para um projeto vigente da empresa a ser cadastrada. Para isso foi criado um método que faz a consulta que retorna a mais futura data de desativação,
e posteriormente, no ato do cadastro essa data é comparada com a data que o usuário insere para o cadastramento. Desta forma,
quando a data de início da vigência é menor que a data retornada pelo método descrito, esta é realocada para a data da ultima desativação.
Essa mudança de data teve de ser feita por meio de variáveis auxiliares que convertem e recebem o valor da data em milli-segundos a partir de 01/01/1970
possibilitando assim operações aritméticas para definir a nova data do projeto.

- Esta validação de email existente foi feita definindo a coluna "amail" no banco de dados como "unique".

- Esta validação do formato do email foi feita por meio da utilização de REGEX na entity class Usuario.

3)	O que é OOP?

Programação Orientada a Objetos é um paradigma de programação que nos traz na sua essência o conceito de objetos, que podem ou não ter 
seus próprios dados (atributos), e é estruturada em fragmentos ou partes de código conhecidos como métodos. Os métodos são utilizados 
principalmente para modificar ou manipular dados de um objeto ao qual estão associados. Atualmente existem diversas linguagens de 
programação que utilizam deste paradigma, e as mais populares são majoritariamente baseadas em classes, o que nos leva ao entendimento 
de que os objetos são instâncias destas classes e que cada um terá suas características baseadas na classe à qual estão ligados.

Dada esta breve explanação, pode-se afirmar que a OOP (ou POO) nos traz muitos benefícios e algumas dificuldades que são compensadas 
por estes benefícios.
•	Reutilização de código – sem dúvida alguma esta é uma das principais vantagens de se utilizar programação orientada a objetos, pois um mesmo trecho de código pode ser replicado em diversas partes do projeto, bastando apenas que a classe ou método que o invoca necessite de uma determinada funcionalidade que este desempenhe. Ainda sobre reutilização de código, podemos destacar os conceitos de herança e polimorfismo. A herança nos permite criar novas classes a partir de classes existentes reaproveitando características da classe herdada. O polimorfismo acontece quando classes que derivam de uma mesma superclasse podem invocar métodos com a mesma identificação, mas que possuem comportamentos diferentes entre si.
•	Melhor adequação à arquitetura cliente servidor.
•	Padrão conceitual uniforme.
•	Maior nível de abstração, o que torna as possibilidades praticamente infinitas.
•	Menor custo para desenvolvimento e manutenção de sistemas.
•	Normalmente sistemas desenvolvidos com OO são mais longevos e tendem a ter menos manutenção.
•	A OO normalmente nos permite desenvolver sistemas mais complexos em um tempo que pode ser considerado reduzido a se comparar com outros paradigmas de programação, pois a maioria das linguagens que a utilizam dispõem de milhares de funções prontas para as mais diversas funcionalidades, basta saber como e onde usá-las.

Elencadas as vantagens, vamos a alguns percalços que a Orientação a Objetos nos traz (afinal nem tudo são flores na vida de um 
desenvolvedor). 
•	Normalmente desenvolvedores que estão acostumados com linguagens estruturadas têm certa dificuldade para “virar a chave” e 
entender como realmente a mágica da orientação a objetos funciona. A dica aqui é: esqueça tudo e aprenda novamente (experiência própria).
•	O uso de memória pode se tornar um problema para desenvolvedores menos atentos.
•	A parte de modelagem de um sistema OO normalmente é mais complexa.
•	A facilidade para desenvolver também pode se tornar um problema para desenvolvedores menos experientes, já que muitas vezes pelo
excesso de reutilização de código por meio de herança ou polimorfismo pode-se deixar muito “lixo” espalhado pelas classes, métodos 
redundantes e outros sem função.

É basicamente isso, as vantagens compensam por muito as desvantagens e fazem da OOP o principal paradigma de programação utilizado
por desenvolvedores ao redor do mundo.



4)	Dada uma stream, encontre o primeiro caractere Vogal, após uma consoante, onde a mesma é antecessora a uma vogal e que não se repita
 no resto da stream. O termino da leitura da stream deve ser garantido através do método hasNext(), ou seja, retorna falso 
 para o termino da leitura da stream. Você terá acesso a leitura da stream através dos métodos de interface fornecidos ao 
 termino do enunciado. (20 pontos)
 
 -A solução está no repositório junto com o outro projeto.
 


5)	Uma das grandes inclusões no Java 8 foi a API Stream. Com ela podemos fazer diversas operações de loop, filtros, maps, etc. 
 Porém, existe uma variação bem interessante do Stream que é ParallelStreams. Descreva com suas palavras quando 
 qual é a diferença entre os dois e quando devemos utilizar cada um deles. (10 pontos)
 
 -Por não possuir conhecimentos mais sólidos a respeito da API e nunca antes ter trabalhado com a mesma, achei por bem não adentrar à questão, embora tenha me interessado e
 pretenda me aprofundar no assunto em um outro momento.


