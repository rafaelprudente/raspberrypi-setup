#!/bin/bash

clear

CYAN='\033[0;36m'
NC='\033[0m'

cd ~/setup

export DEBIAN_FRONTEND=noninteractive

#echo -e "${CYAN}---------- ADD REPOSITORY ----------${NC}"
#sudo add-apt-repository ppa:linuxuprising/java

echo 
echo -e "${CYAN}---------- FIRST UPDATE ----------${NC}"
sudo apt -yq update

echo 
echo -e "${CYAN}---------- FIRST UPGRADE ----------${NC}"
sudo apt -yq full-upgrade

echo 
echo -e "${CYAN}---------- REMOVE JAVA ----------${NC}"
sudo apt -yq remove --purge openjdk*

echo 
echo -e "${CYAN}---------- INSTALL JAVA 8 ----------${NC}"
sudo apt -yq install openjdk-8-jre

echo 
echo -e "${CYAN}---------- INSTALL JAVA 11 ----------${NC}"
sudo apt -yq install openjdk-11-jre

echo 
echo -e "${CYAN}---------- DELETE OLD FILES ----------${NC}"
sudo rm -rf ~/setup/raspberrypi-setup.jar
sudo rm -rf ~/setup/raspberry_pi_setup.xml
sudo rm -rf ~/setup/setup_commands.txt

echo 
echo -e "${CYAN}---------- DOWNLOAD RASPBERRY PI SETUP ----------${NC}"
sudo curl https://raw.githubusercontent.com/rafaelprudente/raspberrypi-setup/master/target/raspberrypi-setup-0.0.1-SNAPSHOT.jar --output ~/setup/raspberrypi-setup.jar
sudo curl https://raw.githubusercontent.com/rafaelprudente/raspberrypi-setup/master/target/classes/raspberry_pi_setup.xml --output ~/setup/raspberry_pi_setup.xml
sudo curl https://raw.githubusercontent.com/rafaelprudente/raspberrypi-setup/master/target/classes/setup_commands.txt --output ~/setup/setup_commands.txt

echo 
echo -e "${CYAN}---------- BECOME EXECUTABLE ----------${NC}"
sudo chmod +x ~/setup/raspberrypi-setup.jar


echo 
echo -e "${CYAN}---------- RUNNING ----------${NC}"
sudo java -jar ~/setup/raspberrypi-setup.jar
