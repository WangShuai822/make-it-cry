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
    }

    stages {
        stage('Test') {
            steps {
                sh 'chmod u+x mvnw'
                sh './mvnw clean test'
            }
        }
        stage('Build') {
            steps {
                sh './mvnw package'
            }
        }

        stage('Deploy') {
            steps {
//                 sh '''
//                 scp target/make-it-cry-1.0-SNAPSHOT.war root@49.235.120.86:~/
//                 scp deploy.sh root@49.235.120.86:~/
//                 '''
                writeFile file: 'deploy.sh', text: 'sh ~/abc-health.sh restart'
                sshScript remote: remote, script: 'deploy.sh'
            }
        }
//         stage('Build Image') {
//             steps {
//                 sh '''
//                 ./mvnw package
//                 docker build -f Dockerfile -t ${IMAGE_NAME}:${VERSION_ID} .
//                 docker tag ${IMAGE_NAME}:${VERSION_ID} ${IMAGE_ADDR}:${VERSION_ID}
//                 docker push ${IMAGE_ADDR}:${VERSION_ID}
//                 docker rmi ${IMAGE_NAME}:${VERSION_ID}
//                 '''
//             }
//         }
//         stage('Build Image') {
//             steps {
//                 sh '''
//                 ./mvnw package
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
//                 sshCommand remote: remote ,command: 'docker -v'

//                 def clearNoneSSH = "n=`docker images | grep  '<none>' | wc -l`; "
//                 if [ \$n -gt 0 ]; then docker rmi `docker images | grep  '<none>' | awk '{print \$3}'`; fi"
//                 sshCommand remote: sshServer, command: "${clearNoneSSH}"

//                 def container_id = "docker ps|grep $IMAGE_ADDR |awk '{print ${1}}'"
//                 sshCommand remote: remote ,command: "docker ps"

//  111                sshCommand remote: remote ,command: 'container_id=\"docker ps |grep \$IMAGE_ADDR\" '
//                 sshCommand remote: remote ,command: "container_id=`docker ps|grep $IMAGE_ADDR |awk '{print ${1}}'`"
//                 sshCommand remote: remote ,command: "if [ -n ${container_id} ]; then docker rm -f ${container_id} fi"
//                 sshCommand remote: remote ,command: 'docker run -d -p ${PORT}:8080 ${IMAGE_ADDR}:${VERSION_ID}'

//                 '''
//                 container_id=`docker ps|grep ${IMAGE_ADDR}|awk '{print $1}'`
//                 if [ -n "${container_id}" ]; then
//                     docker rm -f "${container_id}"
//                 fi
//
//                 docker run -d -p ${PORT}:8080 ${IMAGE_ADDR}:${VERSION_ID}
//                 '''
//             }
//         }

    }
}