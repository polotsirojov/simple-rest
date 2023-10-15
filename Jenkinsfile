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
        withSonarQubeEnv(installationName: 'sonarToken', credentialsId: 'sonarToken')
        waitForQualityGate(credentialsId: 'sonarToken', abortPipeline: true)
      }
    }

    stage('Deploy to Tomcat') {
       steps {
           // Deploy the application to Tomcat using the Deploy to container Plugin
           tomcatDeploy(
               credentialsId: 'tomcat',
               war: 'target/*.war',
               containerId: 'http://localhost:9090'
           )
      }
    }

  }
}