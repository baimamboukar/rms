cd com/baimamboukar/java/rms/src/models
for file in * 
    do
          javadoc -d ../javadocs/ $file
    done