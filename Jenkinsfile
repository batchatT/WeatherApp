pipeline {
    agent {
        docker {
            image 'android-build-env'
            args '-v /tmp:/tmp'
        }
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh './gradlew assembleDebug'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }

        stage('Build Release APK') {
            steps {
                sh './gradlew assembleRelease'
            }
        }
    }

    post {
        success {
            archiveArtifacts artifacts: '**/*.apk', fingerprint: true
        }
        failure {
            echo 'Build failed!'
        }
    }
}
