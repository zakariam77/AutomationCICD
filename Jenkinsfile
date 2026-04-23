pipeline{
    agent any


    tools {
        maven '3.9.14'
        jdk 'JDK25'
        git 'default'
    }



    stages {
        stage('Build') {
        steps {
                sh 'mvn clean compile'
        }
        }


        stage('Run tests') {
            steps {
                sh 'mvn test -PRegression -Dbrowser=chrome'
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