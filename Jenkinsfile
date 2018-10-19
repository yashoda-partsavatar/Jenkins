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
                bat 'mvn clean package sonar:sonar'
            }
        }
        stage ('build Stage') {
            steps {
                bat 'mvn clean install -Dmaven.test.skip=true'
            }
        }
        stage ('test Stage') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Deployment stage') {
            parallel {
                stage('Deploy WebHooks App') {
                    steps {
                        echo "Deploying WebHooks."
                        bat 'mvn tomcat7:redeploy -pl WebHooks -DskipTests -Dtomcat7.deploy.server=localhost:3425'
                    }
                }
                stage('Deploy PartsServer App') {
                    steps {
                        echo "Deploying PartsServer."
                        bat 'mvn tomcat7:redeploy -pl PartsServer -DskipTests -Dtomcat7.deploy.server=localhost:9689'
                    }
                }
            }
        }
        stage('Manually push build to production') {
            steps {
                script {
                    //def proceed = true
                    try {
                        timeout(time: 15, unit: 'SECONDS') {
                            input(message: 'Deploy this build to Production?')
                        }
                    } catch (err) {
                    def user = err.getCauses()[0].getUser()
                                    if('SYSTEM' == user.toString()) { //timeout
                                        currentBuild.result = "SUCCESS"
                                    }
                        //proceed = false
                    }
                    /*if (proceed) {
                        echo "deployed to production"
                    }*/
                }
            }
        }
    }

    post {
        success {
            echo "BUILD SUCCESS"
            echo "Keep Current Build If branch is master"
            notifySuccessful()
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

    The Build with Job Name :${env.JOB_NAME} and Build Number: [${env.BUILD_NUMBER}] has FAILED.

    For more details please check console output at "${env.BUILD_URL}"

    Thanks & Regards

    Yashoda """,

            recipientProviders: [[$class: 'DevelopersRecipientProvider'], brokenTestsSuspects(), brokenBuildSuspects(), culprits()]

    )

}