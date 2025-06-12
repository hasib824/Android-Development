suspend fun flowTester()
{
    val myFlow = flowOf(10,15,20) // Flow is created but not yet active

    println("\nStarting to collect from the Flow...")
    // 2. Consumer: Collect values from the Flow
    myFlow.collect { value -> // 'collect' is a terminal operator that starts the flow
        println("Collected $value")
        Log.e("Flow : ", "flowTester: $value")
    }

    fun myFlower(x:Int) : Unit
    {
        Log.e("Listy F", x.toString())

    }

    myFlow.collect(::myFlower)

    suspend fun myMapper(x:Int) : Int
    {
        Log.e("Listy", x.toString())
        return x*2
    }

    val newOne = listOf<Int>(1, 2, 3, 4, 5).map( fun(x:Int) : Int { return x*2})
    Log.e("Listy", newOne.toString())

    val suspendingDouble: suspend (Int) -> Int = { it * 2 }

    flowOf(4, 2, 5, 1, 7).map(suspendingDouble)
        .collect {
            Log.d("Listy l", it.toString())
        }

    /*myFlow.map(myMapper).collect { result ->
        Log.d("FlowCollect", "Collected (anonymous suspend): $result")
    }
*/
}