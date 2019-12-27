#!/bin/bash

clear

CYAN='\033[0;36m'
NC='\033[0m'

cd ~/

export DEBIAN_FRONTEND=noninteractive

echo -e "${CYAN}---------- FIRST UPDATE ----------${NC}"
echo 
sudo apt -yq update
echo -e "${CYAN}---------- FIRST UPGRADE ----------${NC}"
echo 
sudo apt -yq full-upgrade
echo -e "${CYAN}---------- REMOVE JAVA 8 ----------${NC}"
echo 
sudo apt -yq remove --purge oracle-java8-jdk
echo -e "${CYAN}---------- REMOVE JAVA 11 ----------${NC}"
echo 
sudo apt -yq remove --purge oracle-java11-jdk
echo -e "${CYAN}---------- INSTALL JAVA 8 ----------${NC}"
echo 
sudo apt -yq install oracle-java8-jdk
echo -e "${CYAN}---------- INSTALL JAVA 11 ----------${NC}"
echo 
sudo apt -yq install oracle-java11-jdk