 #cd com/baimamboukar/java/rms/class
 cd javadocs
# ls
for file in * 
    do
            git add $file
            git commit -m "java doc $file"
            #git push
    done