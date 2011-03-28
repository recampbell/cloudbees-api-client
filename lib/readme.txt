#Deploy to the local repo

mvn install:install-file -DgroupId=typica-jaxb -DartifactId=stax-api -Dversion=1.0.1 -Dpackaging=jar -Dfile=stax-api-1.0.1.jar

mvn deploy:deploy-file -DgroupId=net.stax -DartifactId=stax-services-java -Dversion=0.1.0-SNAPSHOT -Dpackaging=jar -Dfile=stax-services-java-0.1.0-SNAPSHOT.jar -Durl=dav:https://repository-cloudbees.forge.cloudbees.com/release/ -DrepositoryId=cloudbees-private-release-repository

 
