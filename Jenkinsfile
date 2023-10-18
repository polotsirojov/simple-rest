pipeline {
  agent any
  stages {

   stage('SonarQube analysis') {
            def scannerHome = tool 'SonarQube';
                withSonarQubeEnv('SonarQube') {
                  sh "${scannerHome}/bin/sonar-scanner \
                  -D sonar.login=admin \
                  -D sonar.password=Soliha.2020 \
                  -D sonar.projectKey=testing \
                  -D sonar.exclusions=vendor/**,resources/**,**/*.java \
                  -D sonar.host.url=http://192.168.1XX.XX:9000/"
                }

              }

    stage("Quality gate") {
                steps {
                    waitForQualityGate abortPipeline: true
                }
            }

  }
}