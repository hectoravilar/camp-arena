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
