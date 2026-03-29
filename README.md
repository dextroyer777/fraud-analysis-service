# Fraud Analysis Service 🛡️

Este projeto é um ecossistema de microserviço para análise de fraudes, desenvolvido em **Java 21** com foco em alta performance e observabilidade em tempo real. A infraestrutura é totalmente containerizada, utilizando **Docker** para orquestrar mensageria, cache e monitoramento.

## 🚀 Tecnologias Core
* **Spring Boot 3.2.x**: Framework base para construção da aplicação, utilizando o ecossistema Spring para fornecer uma base robusta, autoconfigurável e pronta para produção.
* **Java 21**: Utilização de recursos modernos da linguagem, como Virtual Threads, para otimizar o processamento assíncrono no seu processador Ryzen 5.
* **Micrometer**: Fachada de métricas que expõe os dados internos da JVM e da aplicação para o Prometheus.
* **Mensageria**: Apache Kafka (Confluent 7.5.0) para processamento de eventos de fraude.
* **Cache**: Redis 7 (Alpine) para armazenamento temporário de alta performance.
* **Observabilidade**: Prometheus & Grafana OSS (última versão) para monitoramento e dashboards.
* **Ambiente**: openSUSE Leap 15.6 gerenciado via terminal com Vim e Docker Compose.

## 📊 Arquitetura de Observabilidade
O projeto implementa os três pilares da observabilidade:
1. **Métricas**: Coletadas via Micrometer e armazenadas no Prometheus.
2. **Logs**: Centralizados no console dos containers e IDE.
3. **Tracing**: Fluxo de eventos entre Spring Boot, Redis e Kafka.

## 🛠️ Como Executar

### Pré-requisitos
* Docker & Docker Compose instalado.
* Java 21 (JDK).
* Maven.

### Passo a Passo
1. Clone o repositório:
   ```bash
   git clone [https://github.com/SEU_USUARIO/fraud-analysis-service.git](https://github.com/SEU_USUARIO/fraud-analysis-service.git)
   cd fraud-analysis-service
