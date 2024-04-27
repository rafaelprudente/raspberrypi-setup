# Install Docker

curl -fsSL https://raw.githubusercontent.com/rafaelprudente/raspberrypi-setup/master/scripts/docker/docker_installation.sh -o docker_installation.sh

sudo sh ./docker_installation.sh --dry-run

# Verify installation

:~ $ __sudo netstat -lntp | grep dockerd__

tcp        0      0 127.0.0.1:2375          0.0.0.0:*               LISTEN      993/dockerd  

:~ $ __docker run hello-world__

Unable to find image 'hello-world:latest' locally
latest: Pulling from library/hello-world
478afc919002: Pull complete 
Digest: sha256:a26bff933ddc26d5cdf7faa98b4ae1e3ec20c4985e6f87ac0973052224d24302
Status: Downloaded newer image for hello-world:latest

Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
    (arm64v8)
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash

Share images, automate workflows, and more with a free Docker ID:
 https://hub.docker.com/

For more examples and ideas, visit:
 https://docs.docker.com/get-started/