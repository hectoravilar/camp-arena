terraform {

  required_version = ">= 1.0.0"

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0" # Allows minor updates but prevents major breaking changes (e.g., 6.0)
    }
  }
}

# Configure the AWS Provider
provider "aws" {
  region = var.aws_region


  default_tags {
    tags = {
      Project     = var.project
      Environment = var.environment
      ManagedBy   = "Terraform"
    }
  }
}
