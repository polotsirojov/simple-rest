pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        bat 'mvn clean install'
      }
    }
stage('Static Analysis') {
      withSonarQubeEnv('sonarToken')
      {
        bat 'mvn clean package sonar:sonar
   	echo 'Static Analysis Completed'
      }

    stage("Quality Gate"){
      timeout(time: 1, unit: 'HOURS')
      {
        waitForQualityGate abortPipeline: true
        def qg= waitForQualityGate()
        if (qg.status!= 'OK'){
          error "Pipeline aborted due to quality gate failure: ${qg.status}"
        }
      }
      echo 'Quality Gate Passed'
    }

  }
}