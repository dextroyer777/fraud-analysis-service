# Fraud Analysis Service 🛡️

Este projeto é um ecossistema de microserviço para análise de fraudes, desenvolvido em **Java 21** com foco em alta performance e observabilidade em tempo real. A infraestrutura é totalmente containerizada, utilizando **Docker** para orquestrar mensageria, cache e monitoramento.

## 🚀 Tecnologias Core
* **Linguagem**: Java 21 (Spring Boot / Micrometer).
* **Mensageria**: Apache Kafka (Confluent).
* **Cache**: Redis.
* **Observabilidade**: Prometheus & Grafana.
* **Ambiente**: openSUSE Leap 15.6.

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
