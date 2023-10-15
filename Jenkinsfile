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
        withSonarQubeEnv(installationName: 'sonarscan', credentialsId: 'squ_cb3f53722c82960e5e2a793fb585d3a1025cd4ec')
        waitForQualityGate(credentialsId: 'squ_cb3f53722c82960e5e2a793fb585d3a1025cd4ec', abortPipeline: true)
      }
    }

  }
}