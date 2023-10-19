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
                    sleep time: 1, unit: 'MINUTES'
                    qg = waitForQualityGate()
                    // Add sleep before retrieving the quality gate status
                    sleep time: 1, unit: 'MINUTES'
                    // Retry retrieving the quality gate status
                        qg = waitForQualityGate()
                        echo "Quality gate status: ${qg.status}"
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

  stage('Deploy') {
        steps {
          bat 'docker stop springboot-backend'
          bat 'docker rm springboot-backend'
          bat 'docker build -t my-app-image .'
          bat 'docker run -d --name springboot-backend -p 81:81 my-app-image'
        }
      }

}


  }
