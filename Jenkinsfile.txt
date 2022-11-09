pipeline {
    agent any

    stages {
        stage('GIT'){
            steps{
                echo 'Getting Project from GIT';
                git branch:"AlaaMoalla",
                url:'https://github.com/MEJRIWIEM/Groupe_Techers_5SAE5.git'
            }
}
stage('MVN CLEAN'){
    steps{
        sh 'mvn clean'
    }
}
stage('MVN COMPILE'){
    steps{
        sh 'mvn compile'
    }
}
stage('MVN SONARQUBE'){
    steps{
        sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
    }
}

}
    }