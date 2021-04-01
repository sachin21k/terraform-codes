def instance_id = ""

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
            agent {
                label 'AW2CIDev'
            }
            steps {
                sh '$(aws ecr get-login --no-include-email --region $REGION)'
            }
        }
        stage('Deployment') {
            agent {
                docker {
                    image '960885402552.dkr.ecr.us-east-1.amazonaws.com/xsight/infra/terragrunt:1.2.1'
                    label 'AW2CIDev'
                    args '-u root:root'
                }
            }
