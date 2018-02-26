CUR=`pwd`
DIR=$1
cd $DIR 

for f in *; do
    if [[ -f $f ]]; then
    	echo $f
    	fbase=`basename $f .cpp`
    	mkdir src_$fbase
    	mv $f src_$fbase
    	mkdir dest_$fbase
    	for i in {0..9}
		do
			java -jar /home/dyaln/Desktop/bishe/joern-0.3.1/bin/joern.jar src_$fbase  -outdir /tmp/rubbish > dest_$fbase/$i.cpp
		done
    fi
done

cd $CUR