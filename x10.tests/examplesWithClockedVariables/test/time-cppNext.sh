 example=(TestNext)
export X10RT_STANDALONE_NUMPLACES=1
rm out.txt
COUNT=4
for ((i = 0; i < ${#example[@]}; i++))
do
		../../../x10.dist/bin/x10c++ -SAFE_PARALLELIZATION_CHECK=true -x10rt standalone ../${example[$i]}.x10 >> out.txt 
	for ((j = 0; j < $COUNT; j++))
	do
	START=`perl -MTime::HiRes=gettimeofday -e 'print
int(1000*gettimeofday()).qq(\n);'` 
		./a.out $j >> out.txt
	END=`perl -MTime::HiRes=gettimeofday -e 'print
int(1000*gettimeofday()).qq(\n);'` 
	DIFF1=$(( $END - $START ))

	 echo ""|awk -v sn=$j  -v n1=$DIFF1 '{print sn+1, n1}'
	done
	
done

rm *.cc *.h *.inc

