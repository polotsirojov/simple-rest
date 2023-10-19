pipeline {

environment {
    TOMCAT_HOME = "C:\\apache-tomcat-9.0.82"
    WAR_FILE = "${WORKSPACE}\\springboot-rest-blue-ocean\\target\\testing-0.0.1-SNAPSHOT.war.war"
  }

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

stage('Deploy to Tomcat') {
      steps {
        bat "${TOMCAT_HOME}\\bin\\shutdown.bat" // Stop Tomcat
        bat "rmdir /s /q ${TOMCAT_HOME}\\webapps\\ROOT" // Remove old deployment
        bat "copy ${WAR_FILE} ${TOMCAT_HOME}\\webapps\\ROOT.war" // Copy the WAR file
        bat "${TOMCAT_HOME}\\bin\\startup.bat" // Start Tomcat
      }
    }


  }
}