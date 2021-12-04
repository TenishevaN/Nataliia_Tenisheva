
pipeline { 
agent{any}
stages {
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
