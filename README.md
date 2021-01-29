This is a simple docker example of a java kafka project 

<h3>How to run it</h3>
To run it from docker...  

	1. Change your working directory to the projects root directory  
	2. docker network create --driver bridge app-net
	3. docker build -t app-zk -f docker/Dockerfile.zk .
	4. Run	  docker run \
			--name app-zk \
			--publish=2181:2181  \
			--publish=9092:9092  \
			--restart always \
			--network app-net  \
			-d app-zk:latest
			
	5. Do a clean install from maven  
	6. Run    docker build -t app -f docker/Dockerfile.app .   
	  (note if you are making an image for kubernetes use docker build -t app -f docker/Dockerfile.kub.app . )
	
	7. Run    docker run \
			--name=app \
			--publish=8080:8080  \
			--network app-net  \
			app:latest  

The project has one dynamic page and one static page you can visit to verify the project is working (see links below)

This project has different branches for angular, vue and plain jquery
 
<h3>urls</h3>

static link <http://localhost:8080>


<h3>other docker commands</h3>

remove it  

	docker stop app
	docker stop app-zk
	docker rm app
	docker rm app-zk