pipeline {
  agent none
  stages {
    stage('build') {
      agent {
        docker {
          image 'maven:3.8.1-adoptopenjdk-11'
        }

      }
      steps {
        sh '''sudo /usr/local/bin/docker-compose down
'''
        sh 'docker build -f Dockerfile-mongodb -t MarketDataAnalysis/mongodb .'
        sh 'docker build -f Dockerfile-app -t MarketDataAnalysis/app .'
        sh '''sudo /usr/local/bin/docker-compose up
'''
      }
    }

  }
}