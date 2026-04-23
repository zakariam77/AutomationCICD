pipeline{
    agent any


    tools {
        maven '3.9.14'
        jdk 'JDK25'
    }



    stages {
        stage('Build') {
        steps {
                bat 'nvm clean compile'

        }
        }



        stage('Run tests') {
            steps {
                        bat 'nvm -PRegression -Dbrowser=chrome'

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