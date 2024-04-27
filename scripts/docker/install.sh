#!/bin/bash

clear

CYAN='\033[0;36m'
NC='\033[0m'

DEBIAN_FRONTEND=noninteractive
export DEBIAN_FRONTEND

echo 
echo "${CYAN}---------- Uninstall Docker Engine ----------${NC}"
for pkg in docker.io docker-doc docker-compose podman-docker containerd runc; do sudo apt-get remove $pkg; done
sudo apt-get purge docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin docker-ce-rootless-extras
sudo rm -rf /var/lib/docker
sudo rm -rf /var/lib/containerd

echo 
echo "${CYAN}---------- Update And Upgrade SO ----------${NC}"
sudo apt-get update -yq
sudo apt-get upgrade -yq

echo 
echo "${CYAN}---------- Add Docker's Official GPG Key ----------${NC}"
sudo apt-get update
sudo apt-get install ca-certificates curl
sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/debian/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc

echo 
echo "${CYAN}---------- Add The Repository To Apt Sources ----------${NC}"
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/debian \
  $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update

echo 
echo "${CYAN}---------- Install The Docker Packages ----------${NC}"
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

echo 
echo "${CYAN}---------- Verify installation ----------${NC}"
sudo docker run hello-world