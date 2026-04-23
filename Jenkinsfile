pipeline{
    agent any


    tools {
        maven '3.9.14'
        jdk 'JDK25'
    }



    stages {
        stage('Build') {
        steps {
        if(isUnix()){
                        sh 'mvn clean compile'

        }
        else{
        bat 'nvm clean compile'
        }
        }
        }


        stage('Run tests') {
            steps {
            if(isUnix()){
                            sh 'mvn test -PRegression -Dbrowser=chrome'

            }
            else{
            bat 'nvm -PRegression -Dbrowser=chrome'
            }
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