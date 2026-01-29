pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/sohailnajar147/test-devops'
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
                sh 'mvn package -DskipTests'
            }
        }
    }

    post {
        failure {
            emailext (
                to: 'sohailnajar147@gmail.com',
                subject: "Jenkins Pipeline Failed: ${currentBuild.fullDisplayName}",
                body: "The build ${env.BUILD_URL} has failed. Check console output.",
                mimeType: 'text/html'
            )
        }
    }
}
