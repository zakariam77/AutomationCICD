pipeline {

       agent any
       tools{
            maven '3.6.3'
            jdk 'JDK21'
       }
       environment {
        COMPOSE_FILE = 'docker-compose.yaml'
        DB_PASSWORD = credentials('DB-PASSWORD2')
        }
       parameters {
            string(name: 'PROFILE', defaultValue: 'Regression', description: 'ErrorValidation/Regression')
            string(name: 'BROWSER', defaultValue: 'chrome', description: 'chrome/firefox')
       }
        stages{
        stage('closing exisiting compose enviroment'){
            steps{
                echo 'closing any existing enviroment'
                sh "docker compose -f ${env.COMPOSE_FILE} down"
            }
        }
        stage('starting up new docker enviroment'){
            steps{
                echo 'staring up docker enviroment'
                sh "docker compose -f ${env.COMPOSE_FILE} up -d --wait"
                sh 'docker compose ps'
            }
        }

        stage('run tests'){
            steps{
            ansiColor('xterm'){
                sh "mvn clean test -P${params.PROFILE} -Dbrowser=${params.BROWSER}"
                }
            }
        }
        }

        post{

        always{
            echo 'closing docker enviroment'
            sh "docker compose -f ${env.COMPOSE_FILE} down -v"
            junit allowEmptyResults: true, testResults: '**/target/surfire-reports/junitreports/TEST-*.xml'
            allure results: [[path : 'target/allure-results']]
        }
        success{
            echo 'build success'
        }
        failure {
            echo 'build failure'
        }

        }

}