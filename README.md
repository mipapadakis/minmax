# minmax
A sorting algorithm called minmax, being compared with other famous sorting algorithms.
Concept: In each iteration, setFirst() the min values and setLast() the max values of the array.


VERSIONS:
First version: minmax1
Improved version: minmax2

The project runs a series of tests (Main.kt) and, for each test, prints the time elapsed for the minmax1 & minmax2 functions, as well as each of the famous algorithms. A conclusion from these results is that the minmax algorithm is comparable to (or even surpasses) some of the most efficient famous algorithms, in cases in which the array contains many instances of the same numbers, like: [5,4,1,2,5,2,4,3,1,5,4,2,1,5,3,1]


The algorithm can take several modifications and improvements. The implementation of "minmax2" is illustrated in the pseudo-code below:
///////////////////////////////////////////////////////////////////////
START = 0         //START = first index of subarray that needs sorting
END = lastIndex   //END = last index of subarray that needs sorting
while (START <= END) {
    maxSize = 1  //How many duplicates of the max exist
    addedMax = 0 //How many numbers have been setLast()
    minSize = 1  //How many duplicates of the min exist
    i = START
    while ( i<=END-addedMax){
        if(a[i] >= a[END]) 
            if(a[i] == a[END]) maxSize++
            else maxSize = 1
            setLast(i) //a[i] toPosition a[END]
            addedMax++
            continue //don't do i++
        if(a[i] <= a[START]) 
            if(a[i] == a[START]) minSize++
            else minSize = 1
            setFirst(i) //a[i] toPosition a[START]
        i++
    }
    START = START + minSize
    END = END - maxSize + 1
}
///////////////////////////////////////////////////////////////////////

Credit to John Papadakis, my brother, who came up with the minmax concept.
