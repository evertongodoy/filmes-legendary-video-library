# filmes-legendary-video-library
Serviço para fazer o consumo dos end-points do serviço criado anteriormenrte chamado legendary-video-library


Isso acontece porque dentro do Docker, o Kafka escuta na porta 9092, mas para acesso externo (como sua aplicação rodando no host), ele precisa ser acessado via localhost:29092.

🔹 Se você está rodando a aplicação Java FORA do Docker (host)
Use localhost:29092:

spring:
  kafka:
    bootstrap-servers: localhost:29092
    producer:
      retries: 3
      acks: all

🔹 Se a aplicação Java estiver rodando DENTRO do Docker
Use kafka:9092:

spring:
  kafka:
    bootstrap-servers: kafka:9092
