pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/sohailnajar147/test-devops'
            }
        }

        stage('Unit Tests') {
            steps {
                echo 'Running unit tests'
                sh 'mvn test'
            }
        }

        stage('Build') {
            steps {
                echo 'Packaging project'
                sh 'mvn package'
            }
        }
    }

    post {
        failure {
            mail to: 'sohailnajar147@mail.com',
                 subject: 'Jenkins Build Failed',
                 body: 'The Jenkins pipeline failed. Please check Jenkins.'
        }
    }
}
