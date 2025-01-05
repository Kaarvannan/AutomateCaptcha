pipeline {
    agent any
    tools {
        maven 'Maven_3.8.4'  // Use the configured Maven installation in Jenkins
        jdk 'JDK_21'  // Use the configured JDK installation in Jenkins
    }
    environment {
        SONARQUBE_SERVER = 'SonarQubeServer'  // SonarQube server defined in Jenkins
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/your-username/your-repo.git'
            }
        }

        stage('Static Code Analysis') {
            steps {
                withSonarQubeEnv('SonarQubeServer') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    archiveArtifacts artifacts: '**/target/extent-reports/*.html', allowEmptyArchive: true
                }
            }
        }

        stage('Approval Before Deployment') {
            steps {
                script {
                    def userInput = input(id: 'userInput', message: 'Deploy to Staging?', parameters: [booleanParam(defaultValue: true, description: 'Approve Deployment', name: 'confirm')])
                }
            }
        }

        stage('Deploy to Staging') {
            steps {
                sh 'scp target/*.war user@staging-server:/path/to/staging'
            }

}
    }
    post {
        success {
            emailext(
                to: 'team@example.com',
                subject: "Jenkins Build Successful: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """<p>The Jenkins build for ${env.JOB_NAME} is successful.</p>
                         <p>See attached test report for more details.</p>""",
                attachLog: true,
                attachmentsPattern: '**/target/extent-reports/*.html'
            )
        }
        failure {
            emailext(
                to: 'team@example.com',
                subject: "Jenkins Build Failed: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """<p>The Jenkins build for ${env.JOB_NAME} has failed.</p>
                         <p>See the console output for details.</p>""",
                attachLog: true
            )
        }
    }
}
