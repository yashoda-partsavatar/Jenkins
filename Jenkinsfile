
pipeline {
    agent any

    stages {

        stage ('Compile Stage') {
            steps {
                sh 'mvn clean compile'
            }
        }
    }

    post {
            always {
                echo 'I will always say Hello again!'

                emailext body: 'body',
                    recipientProviders: [developers(), upstreamDevelopers()],
                    subject: 'build status'
                echo body
                echo recipientProviders
            }
        }
}