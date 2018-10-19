pipeline {
    agent any
    def stageStatus
    stages {

        stage ('Compile Stage') {
            stageStatus = 'Compile Stage'
            steps {
                bat 'mvn clean compile'
            }
        }
        stage('build && SonarQube analysis') {
            stageStatus = 'SonarQube analysis'
            steps {
                bat 'mvn clean package sonar:sonar'
            }
        }
        stage ('build Stage') {
            stageStatus = 'build Stage'
            steps {
                bat 'mvn clean install -Dmaven.test.skip=true'
            }
        }
        stage ('test Stage') {
            stageStatus = 'test Stage'
            steps {
                bat 'mvn test'
            }
        }
        stage('Deployment stage') {
            stageStatus = 'Deployment stage'
            parallel {
                stage('Deploy WebHooks App') {
                    steps {
                        echo "Deploying to CI Environment."
                    }
                }
                stage('Deploy another App') {
                    steps {
                        echo "Deploying to CI Environment."
                    }
                }
            }
        }

    }

    post {
        always {
            echo "I AM ALWAYS first"
            //notifyBuild("${currentBuild.currentResult}")
        }
        success {
            echo "BUILD SUCCESS"
            echo "Keep Current Build If branch is master"
            notifySuccessful()
        }
        unstable {
            echo "BUILD UNSTABLE"
        }
        failure {
            echo "BUILD FAILURE"
            notifyFailed()
        }
    }
}

def notifySuccessful() {

    emailext (

            to: 'yashoda.agrawal@partsavatar.ca',

            subject: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",

            body: """    Hi Team

    The Build with Job Name :${env.JOB_NAME} and Build Number: [${env.BUILD_NUMBER}] is SUCCESSFUL.

    For more details please check console output at "${env.BUILD_URL}"

    Thanks & Regards

    Yashoda """,

            recipientProviders: [[$class: 'DevelopersRecipientProvider']]

    )
}


def notifyFailed() {

    emailext (

            to: 'yashoda.agrawal@partsavatar.ca',

            subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",

            body: """    Hi Team

    The Build with Job Name :${env.JOB_NAME} and Build Number: [${env.BUILD_NUMBER}] has FAILED during $stageStatus

    For more details please check console output at "${env.BUILD_URL}"

    Thanks & Regards

    Yashoda """,

            recipientProviders: [[$class: 'DevelopersRecipientProvider'], brokenTestsSuspects(), brokenBuildSuspects(), culprits()]

    )

}
