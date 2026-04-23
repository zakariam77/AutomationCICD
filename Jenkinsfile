pipeline{
    agent any


    tools {
        maven '3.9.14'
        jdk 'JDK25'
    }



    stages {
        stage('Build') {
        steps {
                bat 'mvn clean compile'

        }
        }



        stage('Run tests') {
            steps {
                        bat 'mvn -PRegression -Dbrowser=chrome'

            }
        }

    }
    post {


    success {
        echo 'test success'
    }
    failure {
        echo 'test failed'
        }
    }

}