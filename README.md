# Install Docker

curl -fsSL https://raw.githubusercontent.com/rafaelprudente/raspberrypi-setup/master/scripts/docker/docker_installation.sh -o docker_installation.sh

sudo sh ./docker_installation.sh --dry-run

# Verify installation

sudo netstat -lntp | grep dockerd

docker run hello-world