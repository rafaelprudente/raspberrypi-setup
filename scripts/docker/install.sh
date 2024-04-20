#!/bin/bash

clear

CYAN='\033[0;36m'
NC='\033[0m'

export DEBIAN_FRONTEND=noninteractive

echo 
echo -e "${CYAN}---------- Uninstall Docker Engine ----------${NC}"
for pkg in docker.io docker-doc docker-compose podman-docker containerd runc; do sudo apt-get remove $pkg; done
sudo apt-get purge docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin docker-ce-rootless-extras
sudo rm -rf /var/lib/docker
sudo rm -rf /var/lib/containerd

echo 
echo -e "${CYAN}---------- Uninstall Docker Engine ----------${NC}"