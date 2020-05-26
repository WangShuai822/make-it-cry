def remote = [:]
  remote.name = 'dev-server'
  remote.host = '49.235.120.86'
  remote.user = 'root'
  remote.password = 'Jianxiang@1207'
  remote.allowAnyHosts = true

pipeline {
    agent any
    options { disableConcurrentBuilds() }
    environment {
        IMAGE_NAME="make-it-cry"
        PORT="8091"
        IMAGE_ADDR="127.0.0.1:5000/${IMAGE_NAME}"
        VERSION_ID="${BUILD_ID}"
        WORKSPACE="${WORKSPACE}"
    }

    stages {
        stage('Test') {
            steps {
                sh 'chmod u+x mvnw'
                sh './mvnw clean test'
            }
        }
//         stage('Test') {
//             steps {
//                 sh '''
//                     chmod u+x mvnw
//                     ./mvnw org.jacoco:jacoco-maven-plugin:prepare-agent -f pom.xml clean test -Dmaven.test.skip=false -Dautoconfig.skip=true -Dmaven.test.failure.ignore=true
//                 '''
//                 junit 'target/surefire-reports/*.xml'
//                 jacoco changeBuildStatus: true, maximumLineCoverage: '0'
//
//             }
//         }
        stage('Generate Test Report') {
            steps {
              script {
                 try {
                        sh 'mvn org.jacoco:jacoco-maven-plugin:report'
                    } finally {
                        junit 'target/surefire-reports/*.xml'
                 }
              }
            }
        }

        stage('Publish Test Coverage Report') {
         steps {
           step([$class: 'JacocoPublisher',
                execPattern: 'target/*.exec',
                classPattern: 'target/classes',
                sourcePattern: 'src/main/java',
                exclusionPattern: 'src/test*'
                ])
            }
        }

//         stage('publishHTML & Clean Workspace') {
//             steps {
//                 publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: false, reportDir: "report", reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: ''])
//                 cleanWs()
//             }
//         }
//         stage('Build') {
//             steps {
//                 sh 'pwd'
//                 sh './mvnw package'
//             }
//         }
//         stage('Build Image') {
//             steps {
//                 sh '''
//                 docker build -f Dockerfile -t ${IMAGE_NAME}:${VERSION_ID} .
//                 docker tag ${IMAGE_NAME}:${VERSION_ID} ${IMAGE_ADDR}:${VERSION_ID}
//                 docker push ${IMAGE_ADDR}:${VERSION_ID}
//                 docker rmi ${IMAGE_NAME}:${VERSION_ID}
//                 '''
//             }
//         }
//         stage('Deploy') {
//             steps {
//                 echo 'Deploying....'
//
//                 writeFile file: 'deploy.sh', text: "docker ps -a | grep ${IMAGE_NAME} | gawk '{cmd=\"docker rm -f \"\$1; system(cmd)}' \n"+
//                                     "docker run -d -p ${PORT}:8080 ${IMAGE_ADDR}:${VERSION_ID}"
//
//                 sshScript remote: remote, script: "deploy.sh"
//             }
//         }

    }
}