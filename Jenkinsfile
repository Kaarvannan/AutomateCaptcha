pipeline {
    agent any
    tools {
        maven 'Maven_3.8.4'  // Use the configured Maven installation in Jenkins
        jdk 'JDK_21'  // Use the configured JDK installation in Jenkins
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Kaarvannan/AutomateCaptcha.git'
            }
        }

        stage('Build') {
            steps {
                // Using 'sh' for UNIX-based systems or 'bat' for Windows.
                // Assuming Windows environment based on 'bat' usage in the initial script
                bat 'mvn clean'
            }
        }

        stage('Run Test') {
            parallel {
                stage('Test on Chrome') {
                    steps {
                        bat 'mvn test -Dbrowser=chrome'
                    }
                }
                stage('Test on Edge') {
                    steps {
                        bat 'mvn test -Dbrowser=edge'
                    }
                }
                stage('Test on Firefox') {
                    steps {
                        bat 'mvn test -Dbrowser=firefox'
                    }
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/ExtentReports/SparkReport/HtmlReport/ExtentHtml.html', allowEmptyArchive: true
        }
        success {
            emailext(
                to: 'kaarvannan.97@gmail.com',
                subject: "Jenkins Build Successful: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """<p>The Jenkins build for ${env.JOB_NAME} is successful.</p>
                         <p>See attached test report for more details.</p>""",
                attachLog: true,
                attachmentsPattern: '**/ExtentReports/SparkReport/HtmlReport/ExtentHtml.html'
            )
        }
        failure {
            emailext(
                to: 'kaarvannan.97@gmail.com',
                subject: "Jenkins Build Failed: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """<p>The Jenkins build for ${env.JOB_NAME} has failed.</p>
                         <p>See the console output for details.</p>""",
                attachLog: true,
                attachmentsPattern: '**/ExtentReports/SparkReport/HtmlReport/ExtentHtml.html'
            )
        }
    }
}