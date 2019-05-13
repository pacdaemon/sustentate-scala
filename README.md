# Kubernetes

## Build local jre8 image

    cat <<EOF | docker build -t local/openjdk-jre-8-bash:latest -
    FROM openjdk:8-jre-alpine
    RUN apk --no-cache add --update bash coreutils curl
    EOF
    
## Build docker image
    
    sbt docker:publishLocal
    
## Publish docker image

Tag an push the image to the repository

    docker image tag pacdaemon/sustentate-scala:latest registry.ng.bluemix.net/sustentate/pacdaemon
    docker image push registry.ng.bluemix.net/sustentate/pacdaemon

https://github.com/IBM/Akka-cluster-deploy-kubernetes

## Expose HTTP service

    kubectl expose service sustentate --port=8080 --target-port=8080 --type="NodePort" --name sustentate-http
    
After issue that command selector and labels should be set according
TODO Generate a json

TODO

    export KUBECONFIG=/home/<user>/.bluemix/plugins/container-service/clusters/sustentate-scala-cluster/kube-config-hou02-sustentate-scala-cluster.yml