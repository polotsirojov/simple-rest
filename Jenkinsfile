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
            timeout(time: 1, unit: 'HOURS') {
                script {
                    def qg = null
                    // Add sleep before retrieving the quality gate status
                    sleep time: 15, unit: 'SECONDS'
                    // Retry retrieving the quality gate status multiple times
                    for (int i = 0; i < 5; i++) {
                        qg = waitForQualityGate()
                        if (qg.status != null) {
                            break
                        }
                        sleep time: 10, unit: 'SECONDS'
                    }
                    if (qg.status != 'OK') {
                        echo "Quality gate status: ${qg.status}"
                        error "Pipeline aborted due to quality gate failure: ${qg.status}"
                    } else {
                        echo "Quality gate status: ${qg.status}"
                    }
                }
            }
        }
    }

  }
}