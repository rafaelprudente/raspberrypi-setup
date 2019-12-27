#!/bin/bash

clear

cd ~/

export DEBIAN_FRONTEND=noninteractive

if [ ! -d "~/downloads" ]; then
	mkdir downloads
fi


if [ ! -f "~/downloads/setup.sh" ]; then
	sudo curl https://download.bell-sw.com/java/11.0.5+11/bellsoft-jre11.0.5+11-linux-aarch64.tar.gz --output ~/downloads/bellsoft-jre11.0.5+11-linux-aarch64.tar.gz
fi

