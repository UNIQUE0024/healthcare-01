pipeline {
    agent any
    
    tools {
        maven 'Maven'
        jdk 'JDK-17'
    }
    
    environment {
        // Docker Configuration
        DOCKER_IMAGE = 'unique/healthcare-app'
        DOCKER_TAG = "${BUILD_NUMBER}"
        DOCKER_CREDENTIALS = credentials('dockerhub-credentials')
        
        // Change these IPs to your node IPs!
        SONAR_HOST = 'http://192.168.0.3:9000'
        SONAR_TOKEN = credentials('sonarqube-token')
        NEXUS_URL = 'http://192.168.0.4:8081'
        TOMCAT_URL = 'http://192.168.0.5:8080'
        
        // Kubernetes
        K8S_NAMESPACE = 'healthcare'
    }
    
    stages {
        stage('1️⃣ Git Checkout') {
            steps {
                echo '════════════════════════════════════════'
                echo '       Cloning Repository from Git       '
                echo '════════════════════════════════════════'
                git branch: 'main', 
                    url: 'https://github.com/yourusername/healthcare-app.git'
                    
                echo '✅ Code cloned successfully!'
            }
        }
        
        stage('2️⃣ Maven Build') {
            steps {
                echo '════════════════════════════════════════'
                echo '          Building with Maven            '
                echo '════════════════════════════════════════'
                sh 'mvn clean compile'
                
                echo '✅ Build completed!'
            }
        }
        
        stage('3️⃣ Unit Tests') {
            steps {
                echo '════════════════════════════════════════'
                echo '           Running Unit Tests            '
                echo '════════════════════════════════════════'
                sh 'mvn test'
                
                echo '✅ Tests passed!'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('4️⃣ SonarQube Analysis') {
            steps {
                echo '════════════════════════════════════════'
                echo '         Code Quality Analysis           '
                echo '════════════════════════════════════════'
                withSonarQubeEnv('SonarQube') {
                    sh """
                        mvn sonar:sonar \
                          -Dsonar.projectKey=healthcare-app \
                          -Dsonar.host.url=${SONAR_HOST} \
                          -Dsonar.login=${SONAR_TOKEN}
                    """
                }
                
                echo '✅ Code analysis completed!'
            }
        }
        
        stage('5️⃣ Quality Gate') {
            steps {
                echo '════════════════════════════════════════'
                echo '       Waiting for Quality Gate          '
                echo '════════════════════════════════════════'
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
                
                echo '✅ Quality gate passed!'
            }
        }
        
        stage('6️⃣ Package WAR') {
            steps {
                echo '════════════════════════════════════════'
                echo '          Creating WAR File              '
                echo '════════════════════════════════════════'
                sh 'mvn package -DskipTests'
                
                echo '✅ WAR file created: target/healthcare.war'
            }
        }
        
        stage('7️⃣ Upload to Nexus') {
            steps {
                echo '════════════════════════════════════════'
                echo '      Uploading Artifact to Nexus        '
                echo '════════════════════════════════════════'
                script {
                    def pom = readMavenPom file: 'pom.xml'
                    nexusArtifactUploader(
                        nexusVersion: 'nexus3',
                        protocol: 'http',
                        nexusUrl: NEXUS_URL.replaceAll('http://', ''),
                        groupId: pom.groupId,
                        version: "${pom.version}-${BUILD_NUMBER}",
                        repository: 'maven-releases',
                        credentialsId: 'nexus-credentials',
                        artifacts: [
                            [
                                artifactId: pom.artifactId,
                                classifier: '',
                                file: "target/${pom.artifactId}-${pom.version}.war",
                                type: 'war'
                            ]
                        ]
                    )
                }
                
                echo "✅ Artifact uploaded to Nexus!"
            }
        }
        
        stage('8️⃣ Build Docker Image') {
            steps {
                echo '════════════════════════════════════════'
                echo '         Building Docker Image           '
                echo '════════════════════════════════════════'
                script {
                    sh """
                        docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} .
                        docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKER_IMAGE}:latest
                    """
                }
                
                echo "✅ Docker image built: ${DOCKER_IMAGE}:${DOCKER_TAG}"
            }
        }
        
        stage('9️⃣ Push to Docker Hub') {
            steps {
                echo '════════════════════════════════════════'
                echo '       Pushing Image to Docker Hub       '
                echo '════════════════════════════════════════'
                script {
                    sh """
                        echo ${DOCKER_CREDENTIALS_PSW} | docker login -u ${DOCKER_CREDENTIALS_USR} --password-stdin
                        docker push ${DOCKER_IMAGE}:${DOCKER_TAG}
                        docker push ${DOCKER_IMAGE}:latest
                        docker logout
                    """
                }
                
                echo "✅ Image pushed to Docker Hub!"
                echo "   Image: ${DOCKER_IMAGE}:${DOCKER_TAG}"
                echo "   Latest: ${DOCKER_IMAGE}:latest"
            }
        }
        
        stage('🔟 Deploy to Tomcat') {
            steps {
                echo '════════════════════════════════════════'
                echo '        Deploying to Tomcat Server       '
                echo '════════════════════════════════════════'
                deploy adapters: [
                    tomcat9(
                        credentialsId: 'tomcat-deployer',
                        path: '',
                        url: "${TOMCAT_URL}"
                    )
                ], 
                contextPath: '/healthcare',
                war: 'target/*.war'
                
                echo "✅ Application deployed to Tomcat!"
                echo "   URL: ${TOMCAT_URL}/healthcare"
            }
        }
        
        stage('1️⃣1️⃣ Deploy to Kubernetes') {
            steps {
                echo '════════════════════════════════════════'
                echo '      Deploying to Kubernetes Cluster    '
                echo '════════════════════════════════════════'
                script {
                    sh """
                        kubectl set image deployment/healthcare-app \
                          healthcare-app=${DOCKER_IMAGE}:${DOCKER_TAG} \
                          -n ${K8S_NAMESPACE}
                        
                        echo "⏳ Waiting for rollout to complete..."
                        kubectl rollout status deployment/healthcare-app -n ${K8S_NAMESPACE}
                    """
                }
                
                echo "✅ Application deployed to Kubernetes!"
            }
        }
        
        stage('1️⃣2️⃣ Verify Deployment') {
            steps {
                echo '════════════════════════════════════════'
                echo '         Verifying Deployment            '
                echo '════════════════════════════════════════'
                script {
                    sh """
                        echo "📋 Pods Status:"
                        kubectl get pods -n ${K8S_NAMESPACE} | grep healthcare-app
                        
                        echo ""
                        echo "📋 Services:"
                        kubectl get svc -n ${K8S_NAMESPACE} | grep healthcare
                        
                        echo ""
                        echo "📋 Deployment Info:"
                        kubectl describe deployment healthcare-app -n ${K8S_NAMESPACE} | grep -A 5 "Image:"
                    """
                }
                
                echo "✅ Verification completed!"
            }
        }
    }
    
    post {
        success {
            echo ''
            echo '╔════════════════════════════════════════════════════════════╗'
            echo '║                                                            ║'
            echo '║            ✅ DEPLOYMENT SUCCESSFUL! ✅                    ║'
            echo '║                                                            ║'
            echo '╚════════════════════════════════════════════════════════════╝'
            echo ''
            echo '📊 Deployment Summary:'
            echo '━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━'
            echo "   🏷️  Build Number: #${BUILD_NUMBER}"
            echo "   🐳 Docker Image: ${DOCKER_IMAGE}:${DOCKER_TAG}"
            echo "   🌐 Tomcat: ${TOMCAT_URL}/healthcare"
            echo "   ☸️  Kubernetes: kubectl get all -n ${K8S_NAMESPACE}"
            echo '━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━'
            echo ''
        }
        
        failure {
            echo ''
            echo '╔════════════════════════════════════════════════════════════╗'
            echo '║                                                            ║'
            echo '║              ❌ DEPLOYMENT FAILED! ❌                      ║'
            echo '║                                                            ║'
            echo '╚════════════════════════════════════════════════════════════╝'
            echo ''
            echo '❌ Check the logs above to see what went wrong!'
            echo "   Build: #${BUILD_NUMBER}"
            echo "   Console: ${BUILD_URL}console"
            echo ''
        }
        
        always {
            echo '🧹 Cleaning workspace...'
            cleanWs()
            echo '✅ Workspace cleaned!'
        }
    }
}


