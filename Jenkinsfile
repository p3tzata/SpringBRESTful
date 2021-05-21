pipeline {
    
    agent any 
    
    tools {
       maven 'maven-opt-3.8.1'
     } 
    
    stages {
    	
    	stage("build") {
    	
    	     steps {
    	     	echo "Building stage...";
    	     	sh "mvn clean package"
    	     }
    	}
    	
    	stage("test") {
    	
    	     steps {
    	     	echo "Testing stage...";    
    	     }
    	}
    	
    	stage("deploy") {
    	
    	     steps {
    	     	echo "Deploying stage...";
    	     }
    	    
    	}
    	

        
        
    }

    
    
}
