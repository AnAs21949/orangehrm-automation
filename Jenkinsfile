pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK21'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/TON_USER/orangehrm-automation.git'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn clean test'  // bat = Windows, sh = Linux/Mac
            }
        }

        stage('Publish Report') {
            steps {
                publishHTML(target: [
                    reportDir: 'reports',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent Test Report'
                ])
            }
        }
    }

    post {
        always {
            echo 'Pipeline terminé.'
        }
        failure {
            echo 'Des tests ont échoué !'
        }
    }
}