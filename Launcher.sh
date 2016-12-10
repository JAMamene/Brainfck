#!/bin/bash

#Usage: Frist arg = name of archive, Second arg = path of main

rm -r bin
rm Bfck
rm $1.jar
mkdir bin
javac -d bin src/*/*/*.java
cd bin
jar cf ../$1.jar */*/*.class
cd ..
touch Bfck
chmod u+x Bfck
echo "#!/bin/bash
java -cp $1.jar $2 \$@" > Bfck
