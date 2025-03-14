# filmes-legendary-video-library
Serviço para fazer o consumo dos end-points do serviço legendary-video-library e envia mensagens Kafka para solicitar a lista de filmes.
Esse projeto é o inicio do processo. Uma requisição sincrona HTTP pode ser realizada para recuperar a lista de filmes, ou uma mensagem Kafka pode ser enviada para solicitar a lista de filmes de forma assincrona.

# Esse projeto está dividido em 4 branches.
* **consumo-legendary-video-library-normal &rarr;** Faz requisição HTTP [GET] `/library/recuperar/todos` para o micro-serviço **legendary-video-library** para recuperar uma lista de filmes. Não há restrições de acesso pois o GetMapping está aberto para todos. Na prática é a mais simples entre as 3.
* **consumo-legendary-video-library-token &rarr;** Faz requisição HTTP [GET] `/library/recuperar/todos` para o micro-serviço **legendary-video-library** para recuperar uma lista de filmes. Possui dependência externa com o projeto que gera e valida tokens.
* **consumo-legendary-video-library-token-kafka &rarr;** Herda as mesmas necessidades da branch **consumo-legendary-video-library-token** A partir desse momento temos uma requisição HTTP [POST] `/publicar-solicitacao-videos` para publicar uma mensagem no tópico `solicita-videos-library` que será lido pelo projeto **legendary-video-library** e devolverá uma lista de filmes no tópico **devolve-videos-library** que será consumido por esse projeto. Foi criado o consumer **ConsumerMessage** que faz a leitura do topico ***devolve-videos-library***. Até o presente momento não necessiade de chaves para ler e escrever nos topicos do Kafka.
* **consumo-legendary-video-library-token-kafka-redis &rarr;** Herda as mesmas necessidades da branch **consumo-legendary-video-library-token** A partir desse momento foi adicionado um Cache REDIS de 2 minutos `Duration.ofMinutes(2)` ao recuperar a lista de filmes no `get` do projeto `legendary-video-library`.

# Pre-requisitos para rodar o projeto
1. **Docker Desktop** deve estar instalado no seu computador.
2. Ter iniciado o projeto **legendary-video-library**.

# Softwares de apoio
1. **Postman &rarr;** Para realizar HTTP Requests
2. **MongoDB Compass &rarr;** Para consultar e alterar dados no MongoDB se necessário.

# Dependência externa (Opcional, branch consumo-legendary-video-library-normal)
### Esse passo não é obrigatório se estiver utilizando a branch ***consumo-legendary-video-library-normal***.

Como forma de geração e validação de Tokens, foi utilizado o projeto **security-tokens**. Encontra-se disponível no github e pode ser clonado `git clone https://github.com/evertongodoy/security-tokens.git`.
Executar o comando `mvn clean install` para gerar o jar e adicionar no repositório local.
Esse jar será utilizado para validar o token gerado pelo projeto **security-tokens**.

# Como rodar o projeto
1. Abra um **Terminal** válido para rodar comandos do Docker, por exemplo, **PowerShell** ou **Git Bash**.
2. Clone o projeto para sua máquina. Exemplo: `git clone https://github.com/evertongodoy/filmes-legendary-video-library.git`
3. Acesse a branch desejada. Exemplo: `git checkout consumo-legendary-video-library-normal`
4. Abra o projeto no IntelliJ e faça a configuração do JAVA:
   1. Procure no menu por **Project Structure**
   2. Em Project Settings, selecione **Project**
   3. Em Project SDK, selecione a versão do Java que você deseja utilizar, no caso, Corretto 17.
   4. Se ainda não estiver instalada, clique em **Download** e selecione a versão e o vendor. Diretório não precisa ser alterado.
   5. Nesse projeto não será necessário executar comandos Docker pois foi centralizado no projeto **legendary-video-library**.
5. No IntelliJ, abra a janela **Run Anything** tecla Control(2x) e execute o comando `mvn clean install`.
6. Execute a aplicação, botão direito no arquivo **FilmesLegendaryVideoLibraryApplication** e selecione **Run**.

# Orientações para uso
1. Esse projeto vai ser o consumidor do projeto **legendary-video-library**.
2. Possui um Controller **FilmesLegendaryVideoController** que disponibiliza um endpoint [GET] `/library/recuperar/todos` para retornar a lista de filmes.
3. Possui também o endpoint [POST] `/publicar-solicitacao-videos` para publicar uma mensagem no tópico `solicita-videos-library` que será lido pelo projeto **legendary-video-library** e devolverá uma lista de filmes no tópico **devolve-videos-library** que será consumido por esse projeto.
3. Na branch **consumo-legendary-video-library-normal**, não há restrições de acesso. Qualquer usuário pode acessar o endpoint.
4. Nas demais branches, não há restrições de acesso, porém, é utilizado o projeto security-tokens para enviar o Bearer token no Header da requisição HTTP [GET]. O endpoint no proejto **legendary-video-library** está protegido e necessita de um token que é validado através da anotação **@EscopoNecessario()** com uma lista de escopos que o usuário precisa ter para acessar o endpoint, por exemplo, **{"listar-filmes"}**.

# Orientações para uso do Kafka (Se estiver utilizando branch Kafka)
* Se você está rodando a aplicação Java FORA do Docker (host)
* **application.yml**
```yml
spring:
   kafka:
      bootstrap-servers: localhost:29092
```

* Se a aplicação Java estiver rodando DENTRO do Docker
* **application.yml**
```yml
spring:
  kafka:
    bootstrap-servers: kafka:9092
```

Isso acontece porque dentro do Docker, o Kafka escuta na porta 9092, mas para acesso externo (como sua aplicação rodando no host/computador local), ele precisa ser acessado via localhost:29092.

# Acesso ao Kafka-UI
1. Abra o navegador e acesse `http://localhost:7085/ui/`.


# Representação básica da comunição dos microserviços e tópicos

```
---
config:
  theme: dark
---
sequenceDiagram
    participant FilmesService as filmes-legendary-video-library
    participant KafkaSolicita as Topic: solicita-videos-library
    participant LegendaryService as legendary-video-library
    participant KafkaDevolve as Topic: devolve-videos-library
    FilmesService->>KafkaSolicita: Publica solicitação de vídeos
    LegendaryService-->>KafkaSolicita: Lê mensagem do tópico (consome)
    LegendaryService->>LegendaryService: Processa solicitação e busca filmes
    LegendaryService->>KafkaDevolve: Publica lista de filmes
    FilmesService-->>KafkaDevolve: Lê mensagem do tópico (consome)

```

# Acesso ao Redis-Commander
1. Abra o navegador e acesse `http://localhost:7095/`.
