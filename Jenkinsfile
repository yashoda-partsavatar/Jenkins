
pipeline {
    agent any

    stages {

        stage ('Compile Stage') {
            steps {
                bat 'mvn clean compile'
            }
        }
        stage('build && SonarQube analysis') {
                    steps {
                            // Optionally use a Maven environment you've configured already
                                bat 'mvn clean package sonar:sonar'
                    }
                }
    }

    /*post {
            always {
                echo 'I will always say Hello again!'

                emailext body: 'body',
                    recipientProviders: [developers(), upstreamDevelopers()],
                    subject: 'build status'
                echo body
                echo recipientProviders
            }
        }*/
}