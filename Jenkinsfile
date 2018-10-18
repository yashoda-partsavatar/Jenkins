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
            /*
             * These steps will run at the end of the pipeline based on the condition.
             * Post conditions run in order regardless of their place in the pipeline
             * 1. always - always run
             * 2. changed - run if something changed from the last run
             * 3. aborted, success, unstable or failure - depending on the status
             */
            always {
                echo "I AM ALWAYS first"
                notifyBuild("${currentBuild.currentResult}")
            }
            aborted {
                echo "BUILD ABORTED"
            }
            success {
                echo "BUILD SUCCESS"
                echo "Keep Current Build If branch is master"
    //            keepThisBuild()
            }
            unstable {
                echo "BUILD UNSTABLE"
            }
            failure {
                echo "BUILD FAILURE"
            }
        }
}

def notifyBuild(String buildStatus = 'STARTED') {
    // build status of null means successful
    buildStatus = buildStatus ?: 'SUCCESS'

    def branchName = getCurrentBranch()
    def shortCommitHash = getShortCommitHash()
    def changeAuthorName = getChangeAuthorName()
    def changeAuthorEmail = getChangeAuthorEmail()
    def changeSet = getChangeSet()
    def changeLog = getChangeLog()

    // Default values
    def colorName = 'RED'
    def colorCode = '#FF0000'
    def subject = "${buildStatus}: '${env.JOB_NAME} [${env.BUILD_NUMBER}]'" + branchName + ", " + shortCommitHash
    def summary = "Started: Name:: ${env.JOB_NAME} \n " +
            "Build Number: ${env.BUILD_NUMBER} \n " +
            "Build URL: ${env.BUILD_URL} \n " +
            "Short Commit Hash: " + shortCommitHash + " \n " +
            "Branch Name: " + branchName + " \n " +
            "Change Author: " + changeAuthorName + " \n " +
            "Change Author Email: " + changeAuthorEmail + " \n " +
            "Change Set: " + changeSet

    if (buildStatus == 'STARTED') {
        color = 'YELLOW'
        colorCode = '#FFFF00'
    } else if (buildStatus == 'SUCCESS') {
        color = 'GREEN'
        colorCode = '#00FF00'
    } else {
        color = 'RED'
        colorCode = '#FF0000'
    }

    // Send notifications
    hipchatSend(color: color, notify: true, message: summary, token: "${env.HIPCHAT_TOKEN}",
        failOnError: true, room: "${env.HIPCHAT_ROOM}", sendAs: 'Jenkins', textFormat: true)
if (buildStatus == 'FAILURE') {
        emailext attachLog: true, body: summary, compressLog: true, recipientProviders: [brokenTestsSuspects(), brokenBuildSuspects(), culprits()], replyTo: 'noreply@yourdomain.com', subject: subject, to: 'mpatel@yourdomain.com'
    }
}