
rm -r out/
javac -cp . -sourcepath ./src/hearthclone -d ./out  ./src/*.java ./src/hearthclone/*/*.java ./src/hearthclone/*/*/*.java
java -cp out/ LocalMain
