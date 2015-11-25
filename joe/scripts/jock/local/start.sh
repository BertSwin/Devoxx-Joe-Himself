rm -rf joe.log
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=18001,suspend=n -jar ../../../target/devoxx-joe-himself-0.0.1-SNAPSHOT.jar -Dsun.net.inetaddr.ttl=0
