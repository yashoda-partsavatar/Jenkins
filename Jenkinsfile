pipeline {
    agent any

    stages {

        stage ('Compile Stage') {
            steps {
                bat 'mvn clean compile'
            }
        }
    }

    post {
            always {
                echo 'I will always say Hello again!'

                emailext body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
                    recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider'], 'yashoda.agrawal@partsavatar.ca'],
                    subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}"
                echo body
                echo recipientProviders
            }
        }
}