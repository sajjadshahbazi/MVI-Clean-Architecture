package sajjad.shahbazi.common.mvibase

interface MviResult



//open class Vehicle
//class Bicycle : Vehicle()
//class Container<in T>
//
//fun maindsc() {
//    var containerBdeicycle: Container<Bicycle> = Container<Vehicle>() // OK
//    var containerBicycle: Container<Bicycle> = Container<Bicycle>() // OK
//    var containerVehicle: Container<Vehicle> = Container<Bicycle>() // Error: Type mismatch
//}



//class CovarianceSample<T>
//
//fun maisn() {
//    val firstSample: CovarianceSample<Any> = CovarianceSample<Int>()  // Error: Type mismatch
//    val secondSample: CovarianceSample<out Any> = CovarianceSample<String>() // OK , String is a subtype of Any
//    val thirdSample: CovarianceSample<out String> = CovarianceSample<Any>() // Error: Type mismatch
//}


//class KotlinConsumer<in T> {
//    fun toString(value: T): String {
//        return value.toString()
//    }
//}
//
//fun mainw() {
//    val inValue: KotlinConsumer<Number> = KotlinConsumer()
//    val copyOfInValue: KotlinConsumer<Int> = inValue
//    if (copyOfInValue is KotlinConsumer<Int>) {
//        println(copyOfInValue.toString())
//    }
//}

//class KotlinProducer<out T>(val value: T) {
//    init {
//        println(value)
//    }
//}
//
//fun maiwn() {
//    val outValue = KotlinProducer("Just a string")
//    val copyOfOutValue: KotlinProducer<Any> = outValue
//    println(copyOfOutValue.value.toString())
//}
//
//// single param
//inline fun <reified T> Any.isInstanceOf(): Boolean = this is T
//
//fun main() {
//    val isStringAString = "String".isInstanceOf<String>()
//    val isIntAString = 1.isInstanceOf<String>()
//}
//
//// multiple params
//inline fun <reified T, reified U> haveSameType(first: T, second: U) =
//    first is U && second is T
//
//// extension properties, but the type parameter is used as the receiver type
//inline val <reified T> T.theClass
//    get() = T::class.java