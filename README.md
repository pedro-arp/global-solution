# Global Fishing Application (Global Solution)

## Descrição
O projeto "Global Fishing Application" é um sistema desenvolvido em Java 17 utilizando Spring Boot 3.2.5. A partir de métodos HTTP, ele consome uma API externa fornecida pela Global Fishing Watch para monitorar navios pesqueiros.

## Instalação
Para configurar e executar o projeto, siga os passos abaixo:

1. **Pré-requisitos**:
   - Java 17
   - Uma ferramenta para documentar e testar requisições API, recomendamos o [Postman](https://www.postman.com/).

2. **Clonar o repositório**:
   ```bash
   git clone https://github.com/pedro-arp/global-solution
   ```

## Utilização do sistema

Para usar o projeto, importe-o do GitHub e inicie o sistema. Após isso, você pode acessar os end-points através da interface gráfica junto com a documentação do projeto na seguinte URL:

  ```bash
  http://localhost:8080/swagger-ui/index.html#/
  ```
# Endpoints
**Gerar Relatórios em JSON:**

  ```bash
     http://localhost:8080/global-fishing/v1/report
  ```
**Gerar Mapa de Calor para um País Específico:**
    
   ```bash
     http://localhost:8080/global-fishing/v1/{country}
   ```
Substitua {country} pelo código do país desejado.
**Gerar Mapa de Calor Global:**

  ```bash
     http://localhost:8080/global-fishing/v1/ALL
  ```
# Notas
No código fonte do sistema, a porta foi definida como 8080, mas pode ser alterada conforme sua preferência.
# Licença
Este projeto utiliza a API pública fornecida pela Global Fishing Watch. Para mais detalhes, consulte a documentação da API.

  ```bash
      globalfishingwatch.org/our-apis/documentation#stats-parameters
  ```
