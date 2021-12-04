
node('docker') {
    stage 'Checkout'
        checkout scm
    stage 'Build & UnitTest'
          bat 'docker build -t starr .'

}

