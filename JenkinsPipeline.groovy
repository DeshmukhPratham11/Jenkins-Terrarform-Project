pipeline{
    agent any
    
    parameters {
        choice(name: 'Action', choices: ['Apply', 'Destroy'], description: 'Select the Action to perform')
    }
    
    tools {
        terraform 'Terraform'
    }
    
    stages{
        stage("Git Clone"){
            steps{
                sh 'git clone https://github.com/DeshmukhPratham11/Jenkins-Terrarform-Project.git'
            }
        }
        stage("Terraform Init"){
            steps{
                dir ('/var/lib/jenkins/workspace/Jenkins/Jenkins-Terrarform-Project'){
                sh 'terraform init'
            }
            }
        }
        stage ("Terraform plan"){
            steps{
                dir('/var/lib/jenkins/workspace/Jenkins/Jenkins-Terrarform-Project'){
                sh 'terraform plan'
                }
            }
        }
        stage ("Terraform apply/destroy"){
                steps{
                    dir('/var/lib/jenkins/workspace/Jenkins/Jenkins-Terrarform-Project'){
                    script{
                    def ActionTaken = params.Action
                    
                    if (ActionTaken == 'Apply'){
                        sh 'terraform apply --auto-approve'
                    }
                    else if (ActionTaken == 'Destroy'){
                        sh 'terraform destroy --auto-approve'
                    }
                   }
                }
                }
            
        }
    }
}
