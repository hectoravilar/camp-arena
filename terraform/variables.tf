variable "vpc_name" {
  description = "The name of the VPC"
  type        = string
  default     = "camp-arena-vpc"

}

variable "vpc_cidr" {
  description = "The CIDR block for the VPC"
  type        = string
  default     = "10.0.0.0/16"

}

variable "environment" {
  description = "The environment for the VPC (e.g., dev, staging, prod)"
  type        = string
  default     = "dev"

}

variable "project" {
  description = "The project name for tagging purposes"
  type        = string
  default     = "CampArena"

}

variable "aws_region" {
  description = "The AWS region where resources will be created"
  type        = string
  default     = "us-east-1"

}

variable "cluster_name" {
  description = "The name of the EKS cluster"
  type        = string
  default     = "camp-arena-cluster"

}

variable "cluster_version" {
  description = "The Kubernetes version for the EKS cluster"
  type        = string
  default     = "1.30"

}

variable "ecr_repository_name" {
  description = "The name of the ECR repository"
  type        = string
  default     = "camp-arena-repo"
}
