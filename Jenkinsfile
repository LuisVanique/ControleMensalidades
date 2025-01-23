pipeline{
    agent any{
        stages{
            stage('Build backend'){
                bat 'mvn clean package -DskipTests=true'
            }
        }
    }
}