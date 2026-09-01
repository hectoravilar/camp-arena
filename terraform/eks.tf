module "eks" {
  source  = "terraform-aws-modules/eks/aws"
  version = "~> 20.0"

  cluster_name    = var.cluster_name
  cluster_version = var.cluster_version

  # Ensures we can run kubectl commands from our local terminal
  cluster_endpoint_public_access = true

  # Modern and secure way to grant admin access to the user/role running Terraform
  enable_cluster_creator_admin_permissions = true


  # Network integration: placing the cluster in the VPC we just created
  vpc_id     = module.vpc.vpc_id
  subnet_ids = module.vpc.private_subnets

  eks_managed_node_groups = {
    camp_arena_nodes = {
      # Utilizing the lightweight and fast Amazon Linux 2023 for containers
      ami_type       = "AL2023_x86_64_STANDARD"
      instance_types = ["t3.medium"]

      # Auto Scaling configuration for cost efficiency during MVP
      min_size     = 1
      max_size     = 2
      desired_size = 1
    }
  }

  tags = {
    Environment = var.environment
    Project     = var.project
    Terraform   = "true"
  }
}
