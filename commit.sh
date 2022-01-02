cd com/baimamboukar/java/rms/models
ls
for file in * 
    do
            git add $file
            git commit -m "done with $file"
            git push
    done