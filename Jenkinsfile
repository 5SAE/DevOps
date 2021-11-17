pipeline {
    agent any
   tools {

        
        maven 'MAVEN_HOME'
         jdk 'JDK_HOME'
    }
    stages {

        stage('clone git repo'){
            steps {
                bat "if exist DevOps rmdir /s /q DevOps"
                bat "git clone -b meysenbranch https://github.com/5SAE/DevOps.git"
            }
        }
        
          stage('sonar'){
            steps {
                dir("DevOps"){
                    bat "mvn sonar:sonar"
                }
            }
        }
        
         stage("mvn test") {
            steps {
                dir("DevOps"){
                     bat "mvn test"
                }
            }
        }
       
       
        
       
     stage("mvn package") {
            steps {
                dir("DevOps"){
                    bat "mvn package -Dmaven.test.skip"
                }
            }
        }
        stage("Deployment stage") {
            steps {
                dir('DevOps') {
                    bat "mvn deploy"
                }
            }
        }    
         
         
         
        stage('Building docker image') {
          steps{
                dir("DevOps") {
                    
			bat "docker build -t devops ."
			
			bat "docker tag devops  meysensnene/devops:1"
			bat"docker login -u meysensnene -p najiba53161819"
			bat "docker push  meysensnene/devops:1"			
                }
            }    
        }
            
            
          
        
       
      
          
       
    }
    
    
     
    
    
    
    
    
}
