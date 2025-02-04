pipeline{
    agent any
    stages{
        stage('Build backend'){
            steps{
                bat 'mvn clean package -DskipTests=true'
            }
        }
        
        stage('Test'){
			steps{
				bat 'mvn test'
			}
		}
    }
    
}