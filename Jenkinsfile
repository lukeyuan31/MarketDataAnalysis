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
        sh '''mvn -DskipTests -Pprod clean package
'''
        stash(name: 'jar', includes: 'target/*.jar')
        archiveArtifacts 'target/*.jar'
      }
    }

    stage('deploy') {
      agent any
      steps {
        unstash 'jar'
        sh 'docker-compose down'
        sh 'docker build -f Dockerfile-mongo -t mongo .'
        sh 'docker build -f Dockerfile-app -t app .'
        sh 'docker-compose up -d'
      }
    }

  }
}