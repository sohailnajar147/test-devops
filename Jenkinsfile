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
        stage('SonarQube Analysis') {
            steps {
                // We skip the 'withSonarQubeEnv' wrapper and call maven directly
                sh "mvn sonar:sonar \
                    -Dsonar.projectKey=student-management \
                    -Dsonar.host.url=http://127.0.0.1:45173 \
                    -Dsonar.login=sqa_11491b2a8612831934996b6296854622aa5c0980"
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
