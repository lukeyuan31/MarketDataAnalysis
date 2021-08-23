pipeline {
  agent {
    docker {
      image 'maven:3.8.1-adoptopenjdk-11'
    }

  }
  stages {
    stage('build') {
      steps {
        sh 'docker-compose down'
        sh 'docker build -f Dockerfile-mongodb -t MarketDataAnalysis/mongodb .'
        sh 'docker build -f Dockerfile-app -t MarketDataAnalysis/app .'
        sh 'docker-compose up -d'
      }
    }

  }
}