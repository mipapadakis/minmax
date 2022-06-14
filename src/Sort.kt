import java.util.*
import kotlin.collections.ArrayList

class Sort { companion object{

    fun minMaxSort2(list: ArrayList<Int>): List<Int>{
        var start = 0
        var end = list.lastIndex
        var minSize: Int
        var maxSize: Int
        var addedMax: Int
        var step = 0
        var i: Int

        fun setFirst(index: Int){
            val tmp = list[index]
            list.removeAt(index)
            list.add(start, tmp)
        }

        fun setLast(index: Int){
            val tmp = list[index]
            list.removeAt(index)
            list.add(end, tmp)
        }

        while(start<end) {
            addedMax = 0
            minSize = 1
            maxSize = 0
            //println("step $step:  $list")////////////////////
            i = start
            while(i<=end-addedMax){
                if(list[i]>=list[end]){
                    if(list[i] == list[end]) maxSize++
                    else maxSize = 0
                    addedMax++
                    setLast(i)
                    //println("\ti $i: $list")////////////////////
                    continue
                }
                if(list[i]<=list[start]){
                    if(list[i] == list[start]) minSize++
                    else minSize = 1
                    setFirst(i)
                }
                //println("\ti $i: $list")////////////////////
                i++
            }
            start += minSize
            end -= maxSize
            step++
        }
        //println("List: $list\t${ if (list.isSorted()) "SORTED" else "NOT SORTED"}")////////////////////
        //println()////////////////////
        return list
    }

    fun minMaxSort1(list: ArrayList<Int>): List<Int>{
        var step = 0

        fun setFirst(index: Int){
            val tmp = list[index]
            list.removeAt(index)
            list.add(step, tmp)
        }

        fun setLast(index: Int){
            val tmp = list[index]
            list.removeAt(index)
            list.add(list.size-step, tmp)
        }

        while(step<list.size/2) {
            for(i in step until list.size-step){
                if(list[i]<=list[step]) setFirst(i)
                if(list[i]>=list[list.lastIndex-step]) setLast(i)
            }
            step++
        }
        //println("List: $list\t${ if (list.isSorted()) "SORTED" else "NOT SORTED"}")////////////////////
        //println()////////////////////
        return list
    }

    //https://chercher.tech/kotlin/quick-sort-kotlin
    fun quickSort(list: List<Int>): List<Int>{

        if (list.count() < 2){
            return list
        }
        val pivot = list[list.count()/2]
        val equal = list.filter { it == pivot }
        val less = list.filter { it < pivot }
        val greater = list.filter { it > pivot }

        return quickSort(less) + equal + quickSort(greater)
    }

    fun mergeSort(list: List<Int>): List<Int>{

        fun merge(left: List<Int>, right: List<Int>): List<Int>  {
            var indexLeft = 0
            var indexRight = 0
            val newList : MutableList<Int> = mutableListOf()

            while (indexLeft < left.count() && indexRight < right.count()) {
                if (left[indexLeft] <= right[indexRight]) {
                    newList.add(left[indexLeft])
                    indexLeft++
                } else {
                    newList.add(right[indexRight])
                    indexRight++
                }
            }

            while (indexLeft < left.size) {
                newList.add(left[indexLeft])
                indexLeft++
            }

            while (indexRight < right.size) {
                newList.add(right[indexRight])
                indexRight++
            }
            return newList
        }

        if (list.size <= 1) {
            return list
        }

        val middle = list.size / 2
        val left = list.subList(0,middle)
        val right = list.subList(middle,list.size)

        return merge(mergeSort(left), mergeSort(right))
    }

    fun selectionSort(list: ArrayList<Int>): List<Int>{

        fun swap(i: Int, j: Int){
            val tmp = list[i]
            list[i] = list[j]
            list[j] = tmp
        }

        for(i in 0 until list.size-1){
            var minj = i
            for(j in i+1 until list.size){
                if(list[j]<list[minj]) minj = j
            }
            if(minj!=i) swap(i,minj)
        }

        return list
    }

    fun insertionSort(list: ArrayList<Int>): List<Int>{

        for(j in 1 until list.size){
            val key = list[j]
            var i = j-1
            while (i>=0 && list[i]>key){
                list[i+1] = list[i]
                i--
            }
            list[i+1] = key
        }

        return list
    }

    fun countingSort(list: ArrayList<Int>): List<Int>{

        val min = list.minOrNull()!!
        val max = list.maxOrNull()!!
        val count = IntArray(max - min + 1)  // all elements zero by default
        for (number in list) count[number - min]++
        var z = 0
        for (i in min..max)
            while (count[i - min] > 0) {
                list[z++] = i
                count[i - min]--
            }
        return list
    }

    fun bubbleSort(list: ArrayList<Int>): List<Int>{

        var swap = true
        while(swap){
            swap = false
            for(i in 0 until list.size-1){
                if(list[i] > list[i+1]){
                    val temp = list[i]
                    list[i] = list[i+1]
                    list[i + 1] = temp
                    swap = true
                }
            }
        }
        return list
    }

    fun shellSort(list: ArrayList<Int>): List<Int>{

        val n = list.size

        // decide the gap, then reduce the gap
        var gap = n / 2
        while (gap > 0) {
            // Do a gapped insertion sort for this gap size.
            // The first gap elements a[0..gap-1] are already
            // in gapped order keep adding one more element
            // until the entire array is gap sorted
            var i = gap
            while (i < n) {
                // add a[i] to the elements that
                // have been gap sorted save a[i] in temp and make a hole at
                // position i
                val temp = list[i]

                // shift earlier gap-sorted elements up until
                //the correct location for a[i] is found
                var j = i
                while (j >= gap && list[j - gap] > temp) {
                    list[j] = list[j - gap]
                    j -= gap
                }

                // put temp (the original a[i]) in its correct
                // location
                list[j] = temp
                i += 1
            }
            gap /= 2
        }
        return list
    }

    fun cycleSort(list: ArrayList<Int>): List<Int>{

        var writes = 0

        // Loop through the array to find cycles to rotate.
        for (cycleStart in 0 until list.size - 1) {
            var item = list[cycleStart]

            // Find where to put the item.
            var pos = cycleStart
            for (i in cycleStart + 1 until list.size){
                if (list[i] < item){
                    pos++
                }
            }

            // If the item is already there, this is not a cycle.
            if (pos == cycleStart) continue

            // Otherwise, put the item there or right after any duplicates.
            while (item == list[pos]){
                pos++
            }
            val temp = list[pos]
            list[pos] = item
            item = temp
            writes++

            // Rotate the rest of the cycle.
            while (pos != cycleStart) {
                // Find where to put the item.
                pos = cycleStart
                for (i in cycleStart + 1 until list.size){
                    if (list[i] < item) pos++
                }

                // Otherwise, put the item there or right after any duplicates.
                while (item == list[pos]){
                    pos++
                }
                val temp2 = list[pos]
                list[pos] = item
                item = temp2
                writes++
            }
        }
        return list
    }

    fun shakerSort(list: ArrayList<Int>): List<Int>{

        fun swap(i: Int, j: Int) {
            val temp = list[i]
            list[i] = list[j]
            list[j] = temp
        }
        do {
            var swapped = false
            for (i in 0 until list.size - 1)
                if (list[i] > list[i + 1]) {
                    swap(i, i + 1)
                    swapped = true
                }
            if (!swapped) break
            swapped = false
            for (i in list.size - 2 downTo 0)
                if (list[i] > list[i + 1]) {
                    swap(i, i + 1)
                    swapped = true
                }
        }while (swapped)
        return list
    }

    fun strandSort(unsortedList: List<Int>): List<Int>{
        var list = ArrayList<Int>(unsortedList)

        fun merge(left: ArrayList<Int>, right: ArrayList<Int>): ArrayList<Int> {
            val res = ArrayList<Int>()
            while (left.isNotEmpty() && right.isNotEmpty()) {
                if (left[0] <= right[0]) {
                    res.add(left[0])
                    left.removeAt(0)
                }
                else {
                    res.add(right[0])
                    right.removeAt(0)
                }
            }
            res.addAll(left)
            res.addAll(right)
            return res
        }
        var result = ArrayList<Int>()
        while (list.isNotEmpty()) {
            val sorted = arrayListOf(list[0])
            list.removeAt(0)
            val leftover = ArrayList<Int>()
            for (item in list) {
                if (sorted.last() <= item)
                    sorted.add(item)
                else
                    leftover.add(item)
            }
            result = merge(sorted, result)
            list = leftover
        }
        return result
    }

    fun patienceSort(list: ArrayList<Int>): List<Int>{

        val piles = mutableListOf<MutableList<Int>>()
        outer@ for (el in list) {
            for (pile in piles) {
                if (pile.last() > el) {
                    pile.add(el)
                    continue@outer
                }
            }
            piles.add(mutableListOf(el))
        }

        for (i in 0 until list.size) {
            var min = piles[0].last()
            var minPileIndex = 0
            for (j in 1 until piles.size) {
                if (piles[j].last() < min) {
                    min = piles[j].last()
                    minPileIndex = j
                }
            }
            list[i] = min
            val minPile = piles[minPileIndex]
            minPile.removeAt(minPile.lastIndex)
            if (minPile.size == 0) piles.removeAt(minPileIndex)
        }
        return list
    }

    fun combSort(list: ArrayList<Int>): List<Int>{

        var gap = list.size
        var swaps = false
        while (gap > 1 || swaps) {
            gap = (gap / 1.247331).toInt()
            if (gap < 1) gap = 1
            var i = 0
            swaps = false
            while (i + gap < list.size) {
                if (list[i] > list[i + gap]) {
                    val tmp = list[i]
                    list[i] = list[i + gap]
                    list[i + gap] = tmp
                    swaps = true
                }
                i++
            }
        }
        return list
    }

    fun gnomeSort(list: ArrayList<Int>): List<Int>{

        var i = 1
        var j = 2
        while (i < list.size)
            if (list[i - 1] <= list[i])
                i = j++
            else {
                val temp = list[i - 1]
                list[i - 1] = list[i]
                list[i--] = temp
                if (i == 0) i = j++
            }
        return list
    }

    fun pigeonholeSort(list: ArrayList<Int>): List<Int>{

        var min = list[0]
        var max = list[0]
        var index = 0
        var j = 0
        var i = 0

        for (a in list.indices) {
            if (list[a] > max)
                max = list[a]
            if (list[a] < min)
                min = list[a]
        }
        val range: Int = max - min + 1
        val pigeonHole = IntArray(range)
        Arrays.fill(pigeonHole, 0)
        while (i < list.size) {
            pigeonHole[list[i] - min]++
            i++
        }
        while (j < range) {
            while (pigeonHole[j]-- > 0)
                list[index++] = j + min
            j++
        }
        return list
    }

    fun pancakeSort(list: ArrayList<Int>): List<Int>{

        fun indexOfMax(n: Int): Int {
            var index = 0
            for (i in 1 until n) {
                if (list[i] > list[index]) index = i
            }
            return index
        }

        fun flip(index: Int) {
            var i = index
            var j = 0
            while (j < i) {
                val temp = list[j]
                list[j] = list[i]
                list[i] = temp
                j++
                i--
            }
        }
        for (n in list.size downTo 2) { // successively reduce size of array by 1
            val index = indexOfMax(n)   // find index of largest
            if (index != n - 1) {       // if it's not already at the end
                if (index > 0) {        // if it's not already at the beginning
                    flip(index)         // move largest to beginning
                }
                flip(n - 1)       // move largest to end
            }
        }
        return list
    }

    fun trollSort(list: ArrayList<Int>): List<Int>{
        while (!list.isSorted()) list.shuffle()
        return list
    }
    }
}