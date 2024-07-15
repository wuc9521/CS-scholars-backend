export JAVA_ARGS="$*"

mvn exec:java -Dexec.mainClass="site.wuct.scholars.App" -Dexec.args="$JAVA_ARGS"