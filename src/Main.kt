import java.util.*
import kotlin.collections.ArrayList

const val LIST_SIZE = 40 //LIST_SIZE tables, each containing the ARRAY_SIZE integers
const val ARRAY_SIZE = 400

fun main(){
    test123shuffled()
    test112233Shuffled()
    testRandom(10000)
    testRandom(1000)
    testRandom(100)
    testRandom(10)
    testRandom(5)
    test123()
    test321()
}

fun test123shuffled(){
    val array = arrayListOf<Int>()
    for (i in 0 until ARRAY_SIZE) array.add(i+1) //arrayListOf(1, 2, 3, ..., ARRAY_LENGTH)

    val lists = ArrayList<ArrayList<Int>>()
    for( i in 0 until LIST_SIZE) lists.add(array.shuffle())

    println("TEST 123SHUFFLED:\nSorting $LIST_SIZE tables, each containing the integers [1,2,3,...,$ARRAY_SIZE], shuffled.")
    println("Avg running times for each algorithm (in milliseconds):\n")
    callSortingAlgorithms(lists)
    println()
}

fun test112233Shuffled(){
    val array = arrayListOf<Int>()
    for (i in 0 until ARRAY_SIZE/2){
        array.add(i+1)
        array.add(i+1)
    } //arrayListOf(1, 1, 2, 2, 3, 3, ..., ARRAY_SIZE/2, ARRAY_SIZE/2)

    val lists = ArrayList<ArrayList<Int>>()
    for(i in 0 until LIST_SIZE) lists.add(array.shuffle())
    //lists.add(arrayListOf(3,8,9,7,4,3,2,9,5,7,2,1,4,8,3))

    println("TEST 112233SHUFFLED:\nSorting $LIST_SIZE tables, each containing the integers [1,1,2,2,3,3, ...,${ARRAY_SIZE/2},${ARRAY_SIZE/2}], shuffled.")
    println("Avg running times for each algorithm (in milliseconds):\n")
    callSortingAlgorithms(lists)
    println()
}

fun testRandomShuffled(max: Int){
    val array = arrayListOf<Int>()
    val rand = Random()
    for (i in 0 until ARRAY_SIZE) array.add(rand.nextInt()%max) //arrayListOf(random numbers)

    val lists = ArrayList<ArrayList<Int>>()
    lists.add(array)
    for( i in 1 until LIST_SIZE) lists.add(array.shuffle())

    println("TEST RANDOM SHUFFLED:\nFirst creating an array of $ARRAY_SIZE random positive integers (max: $max), then sorting $LIST_SIZE tables, each containing a shuffled version of that array.")
    println("Avg running times for each algorithm (in milliseconds):\n")
    callSortingAlgorithms(lists)
    println()
}

fun testRandom(max: Int){
    val array = arrayListOf<Int>()
    val rand = Random()

    val lists = ArrayList<ArrayList<Int>>()
    for( i in 1 until LIST_SIZE) {
        array.clear()
        for (j in 0 until ARRAY_SIZE) array.add(rand.nextInt()%max)
        lists.add(array)
    }

    println("TEST RANDOM:\nSorting $LIST_SIZE tables, each containing $ARRAY_SIZE random positive integers (max: $max).")
    println("Avg running times for each algorithm (in milliseconds):\n")
    callSortingAlgorithms(lists)
    println()
}

fun test123(){
    val array = arrayListOf<Int>()
    for (i in 0 until ARRAY_SIZE) array.add(i+1) //arrayListOf(1, 2, 3, ..., ARRAY_LENGTH)
    val lists = ArrayList<ArrayList<Int>>()
    for( i in 1 until LIST_SIZE) lists.add(array)

    println("TEST 123:\nSorting the table [1,2,3,...,$ARRAY_SIZE], $LIST_SIZE times")
    println("Running time for each algorithm (in milliseconds):\n")
    callSortingAlgorithms(lists)
    println()
}

fun test321(){
    val array = arrayListOf<Int>()
    for (i in 0 until ARRAY_SIZE) array.add(i+1) //arrayListOf(1, 2, 3, ..., ARRAY_LENGTH)
    array.reverse()
    val lists = ArrayList<ArrayList<Int>>()
    for( i in 1 until LIST_SIZE) lists.add(array)
    println("TEST 321:\nSorting the table [$ARRAY_SIZE, ${ARRAY_SIZE-1}, ...., 1], $LIST_SIZE times")
    println("Running time for each algorithm (in milliseconds):\n")
    callSortingAlgorithms(lists)
    println()
}

fun callSortingAlgorithms(lists: ArrayList<ArrayList<Int>>){
    println("•  min-max 1 sort: ${String.format("%.2f",calculateAverageRunningTime(lists){ Sort.minMaxSort1(it) })}")
    println("•  min-max 2 sort: ${String.format("%.2f",calculateAverageRunningTime(lists){ Sort.minMaxSort2(it) })}")
    println("•      quick sort: ${String.format("%.2f",calculateAverageRunningTime(lists){ Sort.quickSort(it) })}")
    println("•      merge sort: ${String.format("%.2f",calculateAverageRunningTime(lists){ Sort.mergeSort(it) })}")
    println("•     select sort: ${String.format("%.2f",calculateAverageRunningTime(lists){ Sort.selectionSort(it) })}")
    println("•     insert sort: ${String.format("%.2f",calculateAverageRunningTime(lists){ Sort.insertionSort(it) })}")
    println("•   counting sort: ${String.format("%.2f",calculateAverageRunningTime(lists){ Sort.countingSort(it) })}")
    println("•     bubble sort: ${String.format("%.2f",calculateAverageRunningTime(lists){ Sort.bubbleSort(it) })}")
    println("•      shell sort: ${String.format("%.2f",calculateAverageRunningTime(lists){ Sort.shellSort(it) })}")
    println("•      cycle sort: ${String.format("%.2f",calculateAverageRunningTime(lists){ Sort.cycleSort(it) })}")
    println("•     shaker sort: ${String.format("%.2f",calculateAverageRunningTime(lists){ Sort.shakerSort(it) })}")
    println("•     strand sort: ${String.format("%.2f",calculateAverageRunningTime(lists){ Sort.strandSort(it) })}")
    println("•   patience sort: ${String.format("%.2f",calculateAverageRunningTime(lists){ Sort.patienceSort(it) })}")
    println("•       comb sort: ${String.format("%.2f",calculateAverageRunningTime(lists){ Sort.combSort(it) })}")
    println("•      gnome sort: ${String.format("%.2f",calculateAverageRunningTime(lists){ Sort.gnomeSort(it) })}")
    println("• pigeonhole sort: ${String.format("%.2f",calculateAverageRunningTime(lists){ Sort.pigeonholeSort(it) })}")
    println("•    pancake sort: ${String.format("%.2f",calculateAverageRunningTime(lists){ Sort.pancakeSort(it) })}")
}

fun List<Int>.isSorted(): Boolean{
    for(i in 0 until this.size-1){
        if(this[i]>this[i+1]) return false
    }
    return true
}

fun ArrayList<Int>.shuffle(): ArrayList<Int> {
    val rand = Random()
    val n = this.size
    for (i in 0 until n) {
        val t = this[i]
        val r = rand.nextInt(10000) % n
        this[i] = this[r]
        this[r] = t
    }
    return this
}

fun calculateAverageRunningTime(lists: ArrayList<ArrayList<Int>>, sortingAlgorithm: (ArrayList<Int>) -> List<Int>): Double{
    var avg = 0L
    for(l in lists){
        val start = System.currentTimeMillis()
        sortingAlgorithm(ArrayList(l))
        avg += System.currentTimeMillis() - start
    }
    return avg.toDouble()/lists.size
}