 cd com/baimamboukar/java/rms/class
# ls
for file in * 
    do
            git add $file
            git commit -m "compiling $file"
            #git push
    done