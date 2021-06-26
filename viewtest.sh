
rm -r out/
javac -cp . -sourcepath src -d ./out  src/*.java src/*/*.java src/*/*/*.java
java -cp out/ ViewTest
