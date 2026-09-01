module "vpc" {
  source  = "terraform-aws-modules/vpc/aws"
  version = "~> 5.0"

  name = var.vpc_name
  cidr = var.vpc_cidr

  azs             = ["us-east-1a", "us-east-1b"]
  private_subnets = ["10.0.1.0/24", "10.0.2.0/24"]
  public_subnets  = ["10.0.101.0/24", "10.0.102.0/24"]

  # NAT Gateway configuration
  enable_nat_gateway     = true
  single_nat_gateway     = true # Cost optimization: 1 NAT Gateway for all private subnets instead of 1 per AZ
  one_nat_gateway_per_az = false


  # General tags for the VPC
  tags = {
    Terraform   = "true"
    Environment = var.environment
    Project     = var.project
  }

  # EKS requires specific tags on subnets so the AWS Load Balancer Controller can discover them
  public_subnet_tags = {
    "kubernetes.io/role/elb" = "1"
  }

  private_subnet_tags = {
    "kubernetes.io/role/internal-elb" = "1"
  }
}
