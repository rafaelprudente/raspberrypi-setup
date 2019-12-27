#!/bin/bash

clear

CYAN='\033[0;36m'
NC='\033[0m'

cd ~/

export DEBIAN_FRONTEND=noninteractive

echo -e "${CYAN}---------- ADD REPOSITORY ----------${NC}"
sudo add-apt-repository ppa:linuxuprising/java
echo 
echo -e "${CYAN}---------- FIRST UPDATE ----------${NC}"
sudo apt -yq update
echo 
echo -e "${CYAN}---------- FIRST UPGRADE ----------${NC}"
sudo apt -yq full-upgrade
echo 
echo -e "${CYAN}---------- REMOVE JAVA 8 ----------${NC}"
sudo apt -yq remove --purge oracle-java8-jdk
echo 
echo -e "${CYAN}---------- REMOVE JAVA 11 ----------${NC}"
sudo apt -yq remove --purge oracle-java11-jdk
#echo 
#echo -e "${CYAN}---------- INSTALL JAVA 8 ----------${NC}"
#sudo apt -yq install oracle-java8-jdk
#echo 
#echo -e "${CYAN}---------- INSTALL JAVA 11 ----------${NC}"
#sudo apt -yq install oracle-java11-jdk
