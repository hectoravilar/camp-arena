terraform {
  backend "s3" {
    # Name of the S3 bucket created to store the Terraform state file
    bucket = "camp-arena-tfstate-hector" # <-- Mude para o seu bucket

    # Path and filename inside the S3 bucket where the state will be saved
    key = "eks/terraform.tfstate"

    # AWS region where the S3 bucket and DynamoDB table are located
    region = "us-east-1"

    # DynamoDB table used for state locking to prevent concurrent modifications
    dynamodb_table = "terraform-state-lock"
  }
}
