pipeline {
    agent any

    tools {
        jdk 'jdk-17'
        maven 'maven-3.9'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile -DskipTests'
            }
        }

        stage('Test') {
            steps {
                withCredentials([string(credentialsId: 'REQRES_API_KEY', variable: 'REQRES_API_KEY')]) {
                    sh 'mvn test -Dremote=true -DgridUrl=http://selenium-hub:4444/wd/hub'
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished'
        }
    }
}
