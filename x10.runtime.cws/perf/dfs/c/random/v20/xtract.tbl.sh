#!/usr/bin/ksh

if [ $# -ne 1 ]
then
	echo "Usage: xtract.sh file.dat"
	exit 1
fi

in_file=$1
out_file=`echo $in_file | sed -e 's;.dat;.tbl;'`
out_file=dfs.$out_file
sed -n '1,7p' $in_file > $out_file
sed -e 's;^../../../../span;span;g' $in_file | \
egrep '(^# Run|^THREADS|^span|^Best time used|^## Size)' | \
	awk '$0 ~ /^# Run.*/ { seq = $3; \
							printf "\n# %10s %10s %4s %10s %10s [Run: %d]\n", "VERTICS", "EDGES", "PROC", "Time(Secs)", "MEDGES/Sec", seq; \
			} \
			$0 ~ /^## Size.*/ { \
					printf "\n\n";  \
			} \
			$0 ~ /^THREADS:.*/ { nproc = $2; }\
			$0 ~ /^span.*/ { n_vertices = $6; \
								n_edges = $7;} \
			$0 ~ /^Best time used on DFS .*/ { dfs = $7;  \
						printf "  %10d %10d %4d %10.6f %10.6f\n", n_vertices, n_edges, nproc, dfs, (n_edges/(dfs * 1000000)); \
			}' \
		>> $out_file
printf "\n\n" >> $out_file
tail -3 $in_file >> $out_file
