pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        bat 'mvn clean install'
      }
    }

     stage('sonar-scan') {
                steps {
                    // Run SonarQube analysis
                    withSonarQubeEnv('SonarQube') {
                        sh 'mvn sonar:sonar'
                    }
                }
            }

  stage('SonarQube Quality Gate') {
              steps {
                  // Check SonarQube quality gate status
                  script {
                      def qg = waitForQualityGate()
                      if (qg.status != 'OK') {
                          error "SonarQube quality gate failed!"
                      }
                  }
              }
          }


  }
}