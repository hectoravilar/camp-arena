resource "aws_ecr_repository" "game_server" {
  # The actual name of the repository in AWS
  name = var.ecr_repository_name

  # "MUTABLE" allows us to overwrite the 'latest' tag during development. 
  image_tag_mutability = "MUTABLE"

  # allows Terraform to destroy the repo even if it contains images
  force_delete = true

  # Security: Automatically scans our Java Docker image for vulnerabilities when uploaded
  image_scanning_configuration {
    scan_on_push = true
  }
}
