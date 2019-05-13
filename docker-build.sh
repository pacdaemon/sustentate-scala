#!/bin/bash
sbt clean docker:publishLocal
docker image tag pacdaemon/sustentate-scala registry.ng.bluemix.net/sustentate/pacdaemon