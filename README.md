# Camp Arena: Multiplayer Game Server & Cloud Infrastructure

This repository is a Proof of Concept (PoC) focused on **Cloud Architecture, Infrastructure as Code (IaC), and Backend Engineering**. The primary goal of this project is not game development, but rather the design and automation of a highly scalable, resilient, and cost-efficient infrastructure on AWS to host a multiplayer backend.

## 🏗️ Architecture & Tech Stack

The ecosystem is designed to support real-time WebSocket connections and automated deployments, utilizing the following technologies:

- **Cloud Provider:** Amazon Web Services (AWS)
- **Infrastructure as Code (IaC):** Terraform (with remote state management via S3)
- **Container Orchestration:** AWS EKS (Elastic Kubernetes Service)
- **Backend Application:** Java 21, Spring Boot 3, Spring WebSockets
- **CI/CD:** GitHub Actions (Automated Deployment and Tear-Down pipelines)

## ⚙️ Core Infrastructure Features

- **Automated Provisioning:** Terraform handles the deterministic creation of the entire network foundation (VPCs, Subnets, Internet Gateways, Route Tables) and the EKS cluster.
- **Real-Time Communication:** The Spring Boot server manages bidirectional WebSocket connections (`MatchSocketHandler`) for real-time game state synchronization.
- **Service Exposure:** Kubernetes natively manages AWS Load Balancers to securely expose the game's API to the public internet.
- **Cost Management (Tear-Down Pipeline):** A dedicated CI/CD workflow (`destroy-infra.yml`) automates the safe destruction of all AWS resources on-demand, preventing idle compute costs when the lab environment is not in use.

## 🚀 Getting Started

### Local Development (Backend)

To test the server logic locally without provisioning cloud resources:

```bash
cd game-server
./mvnw clean spring-boot:run
```

The server will start on port `8080`. You can validate the health check by sending a GET request to `http://localhost:8080/ping`.

### Cloud Provisioning

The infrastructure is fully managed via GitHub Actions.

1. Configure your AWS credentials (`AWS_ACCESS_KEY_ID` and `AWS_SECRET_ACCESS_KEY`) in the repository Secrets.
2. Run the **Deploy to EKS** workflow to build the Docker image, update the EKS cluster, and provision the AWS Load Balancer.

### Infrastructure Destruction

To avoid ongoing AWS charges after testing, execute the **Destroy Infrastructure** workflow via the GitHub Actions tab. This process deletes Kubernetes resources and triggers a `terraform destroy` automatically.
