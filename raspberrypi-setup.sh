#!/bin/bash

clear

cd ~/

export DEBIAN_FRONTEND=noninteractive

if [ ! -d "~/downloads" ]; then
	mkdir ~/downloads
	mkdir ~/downloads/java
fi


if [ ! -f "~/downloads/java/bellsoft-jre11.0.5+11-linux-aarch64.tar.gz" ]; then
	sudo curl https://download.bell-sw.com/java/11.0.5+11/bellsoft-jre11.0.5+11-linux-aarch64.tar.gz --output ~/downloads/java/bellsoft-jre11.0.5+11-linux-aarch64.tar.gz
	
	if [ ! -d "~/downloads/java/jre11.0.5" ]; then
		mkdir ~/downloads/java/jre11.0.5
	fi

	tar xvzf ~/downloads/java/bellsoft-jre11.0.5+11-linux-aarch64.tar.gz -C ~/downloads/java/jre11.0.5
fi

