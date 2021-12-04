
pipeline { 
agent any
stages {
  
   stage ("Build Maven") {
      steps{
        bat """mvn clean package install"""
    } // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe reports and FindBugs reports
  }
  
  stage('Scan with Sonar'){
steps{
withSonarQubeEnv(installationName: 'sonarrun'){
 bat "D:/sonar-scanner-4.6.2.2472-windows/bin/sonar-scanner"
}
}
}
   
  stage("Build Docker Image") {
    steps{
     bat """ docker build -t starr . """
    }
  }
stage("Run Docker Image"){
steps{
bat """ docker run -d -p 8081:8080 starr """
}
}
}
}
