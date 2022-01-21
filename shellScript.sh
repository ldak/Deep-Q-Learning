#!/usr/bin/bash

#set variables
EPISODES="100"
MESSEGE="played $EPISODES  episodes"

while true
do 

	#play episodes
	java -jar ./DeepQLearning.jar -e $EPISODES

	#commit to git
	git add .
	git commit -m "$MESSEGE"
	git push origin training
	sleep 10
done
