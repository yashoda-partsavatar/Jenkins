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
         stage ('build Stage') {
              steps {
                      bat 'mvn clean build -Dmaven.test.skip=true'
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
}