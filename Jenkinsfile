pipeline {
    agent any

    stages {

        stage ('Compile Stage') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('build status'){
            steps {
                 post {
                    changed {
                         script {
                              if (currentBuild.currentResult == 'FAILURE') { // Other values: SUCCESS, UNSTABLE
                                  // Send an email only if the build status has changed from green/unstable to red
                                   emailext subject: '$DEFAULT_SUBJECT',
                                    body: '$DEFAULT_CONTENT',
                                      recipientProviders: [
                                        [$class: 'CulpritsRecipientProvider'],
                                        [$class: 'DevelopersRecipientProvider'],
                                        [$class: 'RequesterRecipientProvider']
                                       ],
                                       replyTo: '$DEFAULT_REPLYTO',
                                       to: '$DEFAULT_RECIPIENTS'
                              }
                         }
                                                                                                                                                                                                                                                                                                                                                                                                                    }
                 }
            }
        }
    }
}