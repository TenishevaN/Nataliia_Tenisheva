
pipeline { 
agent any
stages {
  
   stage ("Build") {
   withMaven {
      bat """mvn clean package install"""
    } // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe reports and FindBugs reports
  }
  
  stage("build") {
    steps{
     bat """ docker build -t starr . """
    }
  }
stage("run"){
steps{
bat """ docker run -d -p 8081:8080 starr """
}
}
}
}
