pipeline {
    agent none

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        disableConcurrentBuilds()
    }
    environment {
        ENV = 'dev'
        REGION = "us-east-1"
        projectName = 'AW_TableauDeployment'
        urlPrefix = ''
        projectUrl = "${urlPrefix}/TableauServer-deployment-HA.git"
    }

    stages {
        stage('Docker login') {
            agent none
            steps {
                sh '$(aws ecr get-login --no-include-email --region $REGION)'
            }
        }
        stage('Deployment') {
              print("allgood")
            }
    }       
