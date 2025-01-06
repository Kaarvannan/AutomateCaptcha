pipeline {
    agent any
    tools {
        maven 'Maven_3.8.4'  // Use the configured Maven installation in Jenkins
        jdk 'JDK_21'  // Use the configured JDK installation in Jenkins
    }
    stages {
        stage('Checkout') {
            steps {
                git branch:'main', url:'https://github.com/Kaarvannan/AutomateCaptcha.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Run Test') {
            parallel {
                stage('Test on Chrome') {
                    steps {
                        sh 'mvn test -Dbrowser=chrome'
                    }
                }
                stage('Test on Edge') {
                    steps {
                        sh 'mvn test -Dbrowser=edge'
                    }
                }
                stage('Test on Firefox') {
                    steps {
                        sh 'mvn test -Dbrowser=firefox'
                    }
                }
            }
        }
            post {
                always {
                    archiveArtifacts artifacts: '**/target/cucumber-reports/report.html', allowEmptyArchive: true
                }
            }
        }  // This brace closes the 'stages' block properly

    }  // Missing closing brace added here

    post {
        success {
            emailext(
                to: 'kaarvannan.97@gmail.com',
                subject: "Jenkins Build Successful: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """<p>The Jenkins build for ${env.JOB_NAME} is successful.</p>
                         <p>See attached test report for more details.</p>""",
                attachLog: true,
                attachmentsPattern: '**/target/cucumber-reports/report.html'
            )
        }
        failure {
            emailext(
                to: 'kaarvannan.97@gmail.com',
                subject: "Jenkins Build Failed: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """<p>The Jenkins build for ${env.JOB_NAME} has failed.</p>
                         <p>See the console output for details.</p>""",
                attachLog: true,
                attachmentsPattern: '**/target/cucumber-reports/report.html'
            )
        }
    }
}