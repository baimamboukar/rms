# javac -d com/baimamboukar/java/rms/class com/baimamboukar/java/rms/models/*.java
# javac -d com/baimamboukar/java/rms/class com/baimamboukar/java/rms/services/interfaces/*.java
# javac -d com/baimamboukar/java/rms/class com/baimamboukar/java/rms/services/impl/*.java
# javac -d com/baimamboukar/java/rms/class com/baimamboukar/java/rms/*.java
# javac -d com/baimamboukar/java/rms/class com/baimamboukar/java/rms/database/*.java

# jar cvfm baimamboukar.jar MANIFEST.MF class/* rms.sqlite
cd com/baimamboukar/java/rms
#javac -cp ./:jars/* -d class/ src/ui/* src/database/* src/models/*
java -cp jars/* com.baimamboukar.java.rms.src.ui.Dashboard