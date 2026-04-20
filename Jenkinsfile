pipeline{
    agent any
   parameters {
       String(name: 'saucedemoTests'},
       defaultValue: 'testing.xml', description: 'testing.xml')
    }
    stages {
    stage('initialize') {
        steps {
            echo "Preparing to run: ${params.saucedemoTests}"
            sh 'mvn -version'
        }
    }
    stage('Checkout'){
        steps {
            checkout scm
        }
    }
    stage('Execution') {
        steps {
        sh "mvn clean test -DsuiteXmlFile=${params.saucedemoTests}"
        }
    }
    post{
        failure {
            echo 'test failes'
    }

    }





    }


  }
