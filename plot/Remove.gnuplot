set terminal svg size 1980,1024

set output "plot/Remove.svg"
plot \
"out/ArrayList_Remove.out" using 1:2 with linespoints lw 2 lc 1 lt 1, \
"out/BinarySearchTree_Remove.out" using 1:2 with linespoints lw 2 lc 2 lt 1, \
"out/HashSet_Remove.out" using 1:2 with linespoints lw 2 lc 3 lt 1, \
"out/LinkedList_Remove.out" using 1:2 with linespoints lw 2 lc 4 lt 1, \
"out/TreeSet_Remove.out" using 1:2 with linespoints lw 2 lc 5 lt 1
