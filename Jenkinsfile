pipeline{
    agent any
    tools {
        maven 'Maven'
    }

    environment {
      DOCKER_TAG = getVersion()
    }

    stages{
        stage ('SCM Checkout'){
            steps{
                git branch: 'main', credentialsId: 'github', url: 'https://github.com/skmaha/SpringbootAppwithCICD.git'
            }
        }
        
        stage ('Maven Build'){
            steps{
                sh "mvn clean package"
            }
        }
        
        stage ('Docker Build'){
            steps{
                sh 'docker build . -t sunil4356/sbtestapp:${DOCKER_TAG} '
            }
        }

        stage('DockerHub Push'){
            steps{
                withCredentials([string(credentialsId: 'docker-hub', variable: 'dockerHubPwd')]) {
                    sh 'docker login -u sunil4356 -p ${dockerHubPwd}'
                }
                sh 'docker push sunil4356/sbtestapp:${DOCKER_TAG}'
            }
        }
        stage('Decker Deploy'){
            steps{
                ansiblePlaybook credentialsId: 'test-server', disableHostKeyChecking: true, extras: "-e DOCKER_TAG=${DOCKER_TAG}", installation: 'Ansible', inventory: 'test-server.inv', playbook: 'deploy-docker.yaml'
            }
        }
    }
}
def getVersion(){
    def commitHash = sh label: '', returnStdout: true, script: 'git rev-parse --short HEAD'
    return commitHash
}
