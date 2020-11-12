# Integração entre Spring Boot e Kafka

Para executar o projeto configure:
* as variáveis **APPLICATION_ROOT_FOLDER** (apontando para o diretório em que este projeto foi clonado) e **MAVEN_SETTINGS_FOLDER** (caminho absoluto para a pasta .m2 de seu usuário) no arquivo .env;
* rode `docker-compose up`;
* monitore o log do consumidor com `docker-compose logs -f consumer`;
* chame http://localhost:8080/kafka?message=oi.
