# Quatty

[![wakatime](https://wakatime.com/badge/user/c490d7c7-53db-4d39-9285-bc8fded329ef/project/c1283952-8b35-4aac-9453-cedcf9622cb1.svg)](https://wakatime.com/badge/user/c490d7c7-53db-4d39-9285-bc8fded329ef/project/c1283952-8b35-4aac-9453-cedcf9622cb1)

![JAVA](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![HIBERNATE](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Spring](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white)
![Postgresql](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

![Typescript](https://img.shields.io/badge/TypeScript-007ACC?style=for-the-badge&logo=typescript&logoColor=white)
![HTML](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![SASS](https://img.shields.io/badge/Sass-CC6699?style=for-the-badge&logo=sass&logoColor=white)
![Angular](https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white)

![HEROKU](https://img.shields.io/badge/Heroku-430098?style=for-the-badge&logo=heroku&logoColor=white)
![NETLIFY](	https://img.shields.io/badge/Netlify-00C7B7?style=for-the-badge&logo=netlify&logoColor=white)
![AWS](https://img.shields.io/badge/Amazon_AWS-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white)
![FIGMA](https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white)
![JIRA](https://img.shields.io/badge/Jira-0052CC?style=for-the-badge&logo=Jira&logoColor=white)

## Publico alvo

O publico alvo da aplicação são os jogadores de basquete do mais diversos tipos sejam amadores, estudantes ou profissionais.

### O que a aplicação busca resolver?

Existe uma dificuldade de comunicação entre os praticantes de esportes (basquete) para se reunir seja em locais públicos ou privados para a prática do esporte, além disso, após se reunirem existe uma dificuldade de gerenciamento das partidas no que diz respeito ao times, as estatísticas e o balanceamento das equipes.

- Onde está ocorrendo o problema?

O problema ocorre em encontros para prática de esportes dos mais diversos tipos, entretanto, o publico alvo para esse projeto vão ser os jogadores de basquete.

- Qual o impacto do problema?

Esse problema tem impacto na saúde e bem estar da sociedade no geral onde existe uma dificuldade em gerir partidas e campeonatos, além de encontrar praticantes do esporte com interesses em comum.

## Soluções oferecidas na aplicação
### Para todos
- Disponibilização de avaliação de jogadores, locais e equipes.
- Abertura de espaço para divulgação de peneiras.
### Para Jogadores
- Disponibilização de plataforma para visualização de estatísticas
- Falicitação na busca de locais para prática de esportes
- Facilitação de busca por rachas/equipes para treinar.
### Para equipes
- Registro de equipes
- Facilitação da busca por jogadores para completar equipes
- Falicitação na busca de locais para prática de esportes
- Disponibilização de plataforma para gestão de partidas e campeonatos
- Integração da comunidade a espaços publicos.
### Para gestores
- Disponibilização de plataforma para gestão de entrada e saída de pessoas
- Disponibilização de plataforma para manuteção de horarios e valores
- Dashboards de vendas/utilizações dos espaços

# Arquitetura do projeto


## Low level architecture

### Diagrama

Na imagem a seguir é apresentado como o projeto se comporta, qual a relação entre os pacotes, protocolo de comunicação entre a interface gráfica e parte logica da aplicação.

![low level architecture](architecture/Organization%20of%20modules/Diagrama%20em%20branco%20-%20Arquitetura.png)

### Implementação

Na imagem abaixo é apresentado a estrutura do projeto e sua organização de pastas.

![Implementação backend](architecture/Organization%20of%20modules/Modules.png)

## Processo de release

### Etapas do processo de release.
1. A partir da branch de *DEVELOPMENT* é criado a branch para a task que será atuada na task da sprint do jira.
2. Após finalizado o desenvolvimento deve ser realizado uma Pull Request para a branch development.
3. A medida que os códigos são enviados para PR devem ser atribuidas pessoas para realizar o review onde em sua PR deve conter a descrição dos arquivos que foram alterados e por que foram alterados.
4. Com a finalização da sprint e realizado o code review de todas as funcionalidades criadas as mesmas são mergeadas para a branch *QA* onde será testada por usuários de diferentes tipos.
5. Caso passe nos testes de usabilidade e que as funcionalidades que foram estipuladas serem desenvolvidas na sprint estajam funcionando será realizado o merge para a branch principal *MAIN*
6. A versão em produção é disponibilizada para uso de todos os usuários.

![release](architecture/Release/Processo%20de%20release.svg)

## Diagrama de classes

O Diagrama de classes apresenta a estrutura de como será produzido o código independente de linguagem tornando fácil a compreensão de qualquer leitor.

Com o diagrama de classes podemos representar as funcionalidades que estão a ser construidas.

- O diagrama de classes se encontra em construção a medida que o projeto evolui ele é incrementado e versionado.
### Versão Atual 2.0
![diagrama de classes v2](architecture/Class%20Diagrams/Diagrama%20de%20classe%20-%20Quatty%20V2.png)

O diagrama a seguir mostra como está relacionado o desenvolvimento do sistema de forma completa tendo como pespectiva o que foi desenvolvido até então.

![Desenvolvido](architecture/Class%20Diagrams/backend.png)

## Modelo de entidade e relacionamento

Com a utilização de um modelo entidade e relacionamento é possivel identificar como as entidades vão estar dispostas no banco de dados, seus relacionamentos e realizar a normalização das mesmas ganhando performace.

- Assim como o diagrama de classes o modelo de entidade e relacionamento é alterado a medida que o projeto cresce. 
### Versão Atual 2.0

![MER](architecture/ERM/Quatty_conceitual_v2.png)

## Diagrama de caso de uso

Esse diagrama trás uma visão macro do sistema como os atores podem utilizar-los.

## Diagramas de sequência

Os diagramas de sequência são utilizados para representar como o usuário interage com o sistema e como funciona a comunicação interna do sistema independente de linguagem de programação, é utilizado para situações que contém estados mais complexos para melhorar o entendimento do programador que está a construir a funcionalidade especificada.