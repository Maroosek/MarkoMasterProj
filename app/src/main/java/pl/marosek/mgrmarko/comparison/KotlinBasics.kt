package pl.marosek.mgrmarko.comparison

// Ignore this class, it is for pure educational purposes only

// X. Variable and its initialization

fun main() {
    val x = 5
    // Stała, nie można zmienić wartości
    var y = 10
    // typ zmiennej jest automatycznie wywnioskowany
    var z: Int = 15
    z += 5
}



// X. Class declaration and hello world
class KotlinBasics {
    fun main() {
        println("Hello, World!")
    }
}

// X. Function declaration

fun mainFun() {
    val result = add(5, 10)
}
fun add(a: Int, b: Int): Int {
    return a + b
}

//fun add(a: Int, b: Int) = a + b
//optional return type


// x. Classes and objects

class CarKotlin (val manufacturer : String , val model : String) {
    fun drive() {
        println("Driving: $manufacturer $model")
    }

    fun main() {
        val car = CarKotlin("Toyota", "Corolla")
        car.drive()
    }

}

// x. Inheritance
open class AnimalKotlin {
    fun eat() {
        println("Eating")
    }
}

class DogKotlin : AnimalKotlin() {
    fun bark() {
        println("Barking")
    }

    fun main() {
        val dog = DogKotlin()
        dog.eat()
        dog.bark()
    }
}

// x.loops

fun loops() {
    for (i in 0..10) {
        println(i)
    }

    for (i in 0 until 10) {
        println(i)
    }

    for (i in 10 downTo 0) {
        println(i)
    }

    for (i in 0..10 step 2) {
        println(i)
    }

    var i = 0
    while (i < 10) {
        println(i)
        i++
    }
}

// x. conditions

fun conditionalStatements() {
    val x = 5
    if (x > 0) {
        println("x is positive")
    } else if (x < 0) {
        println("x is negative")
    } else {
        println("x is zero")
    }

    val grade = '5'
    when (grade) {
        '5' -> println("Excellent")
        '4' -> println("Good")
        '3' -> println("Satisfactory")
        else -> println("Fail")
    }
}