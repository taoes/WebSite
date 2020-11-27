pipeline {
    agent any
    stages {
        stage('环境检查') {
            steps {
                sh 'java -version'
                sh 'gradle -version'
                sh 'uname -a'
                sh 'docker version'
                echo '环境检查....... OK'
            }
        }

        stage('构建阶段') {
            steps {
                sh './gradlew build'
                echo '项目构建....... OK'
            }
        }

        stage('部署阶段') {
            steps {
                echo '部署阶段....... OK'
            }
        }

        stage('检查启动阶段') {
            steps {
                echo '启动阶段....... OK'
            }
        }
    }
}
