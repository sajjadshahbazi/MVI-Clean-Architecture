package sajjad.shahbazi.common

interface Mapper<T : Any?, R> {
    fun map(item: T): R
}