pipeline {
  agent any
  stages {

   stage('SonarQube analysis') {
                  steps {
                      withSonarQubeEnv('SonarQube') {
                          bat 'mvn clean package sonar:sonar'
                      }
                  }
              }

    stage("Quality gate") {
                steps {
                    timeout(time: 5, unit: 'MINUTES') {
                    script{
                           def qg = waitForQualityGate()
                           if (qg.status != 'OK') {
                               error "Pipeline aborted due to quality gate failure: ${qg.status}"
                           }
                           echo "status is: ${qg.status}"
                    }
                    }
                }
            }

  }
}