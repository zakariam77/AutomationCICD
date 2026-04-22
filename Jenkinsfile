pipeline
    agent any


    tools {
        maven 'Maven3'
    }



    stages {
        stage('checkout') {
            mvn clean compile

        }
        stage('run tests') {
            mvn test -PRegression Dbrowser=chrome
        }
        post {

        }{

        }
}