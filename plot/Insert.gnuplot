set terminal svg size 1980,1024

set output "plot/Insert.svg"
plot \
"out/ArrayList_Insert.out" using 1:2 with linespoints lw 2 lc 1 lt 1, \
"out/BinarySearchTree_Insert.out" using 1:2 with linespoints lw 2 lc 2 lt 1, \
"out/HashSet_Insert.out" using 1:2 with linespoints lw 2 lc 3 lt 1, \
"out/LinkedList_Insert.out" using 1:2 with linespoints lw 2 lc 4 lt 1, \
"out/TreeSet_Insert.out" using 1:2 with linespoints lw 2 lc 5 lt 1
