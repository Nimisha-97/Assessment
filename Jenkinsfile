@Library('Nimisha-97')_

pipeline {
    environment { 
        registry = "n4792541/nodepapp" 
        registryCredential = 'dockerhub' 
        dockerImage = '' 
    }
    agent any
    stages{

        stage('Checkout'){
            steps{
     
              gitcheckout("main", "https://github.com/Nimisha-97/Assessment.git") 
             
            }
        }
          stage('Initialize')
        {
            steps {
                script {
              
        def dockerHome = tool 'myDocker'
        env.PATH = "${dockerHome}/bin:${env.PATH}"
                }
            }
        }
          stage('Building our image')
        {
            steps
            {
                sh 'docker --version'
                 script
                   {
                  
                      dockerImage = docker.build registry + ":$BUILD_NUMBER"
                   }
            }
        }
        stage('Deploy our image') {
            steps {
                script {
                    docker.withRegistry ${registryCredential} {
                        dockerImage.push()
                    }
                }
            }
        }
        stage('Pull our image') {
            steps{
                script{
                    docker.withRegistry ${registryCredential} {
                        dockerImage.pull()
                   
                }
            }
            sh  'docker images'
        }
    }
    stage('Run Image'){
           steps{
               sh ''' 
               if [ $(docker ps -qf "name=cool_wright") ]
                then
                echo "from if block"
                docker kill cool_wright && docker rm cool_wright
                docker run -d -p 1234:8080 --name cool_wright "${registry}":"${BUILD_NUMBER}"
                docker ps
                else
                echo "from else block"
                docker run -d -p 1234:8080 --name cool_wright "${registry}":"${BUILD_NUMBER}"
                docker ps
                fi
               '''
           }
       }   
    }
}
