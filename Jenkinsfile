pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/jenkins-pipeline']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'yashoda-git', url: 'https://github.com/Partsavatar-Team/partsavatar-team.git']]])
            }
        }

        stage ('Compile Stage') {

            steps {
                sh 'mvn clean compile'
            }
        }

        stage ('Testing Stage') {

            steps {
                 sh 'mvn test'
            }
        }


        stage ('Deployment Stage') {
            steps {
                 sh 'mvn deploy'
            }
        }
    }
}