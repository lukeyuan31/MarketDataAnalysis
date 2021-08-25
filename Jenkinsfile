pipeline {
  agent none
  stages {
    stage('check and test') {
      agent {
        docker {
          image 'maven:3.8.1-adoptopenjdk-11'
        }

      }
      steps {
        sh 'mvn test'
      }
    }
    stage('build') {
      agent {
        docker {
          image 'maven:3.8.1-adoptopenjdk-11'
        }

      }
      steps {
        sh 'mvn -DskipTests -Pprod clean package'
        stash(name: 'jar', includes: 'target/*.jar')
        archiveArtifacts 'target/*.jar'
      }
    }
    stage('deploy') {
      agent any
      steps {
        unstash 'jar'
        sh 'docker-compose down'
        sh 'docker-compose up -d'
      }
    }

  }
}