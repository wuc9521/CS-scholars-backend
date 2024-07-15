export JAVA_ARGS="$*"

mvn exec:java -Dexec.mainClass="site.wuct.scholars.App" -Djava.net.preferIPv4Stack=true -Dexec.args="$JAVA_ARGS"