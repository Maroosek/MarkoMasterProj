package pl.marosek.mgrmarko.textUtil

class TextCounterKotlinInline {

    inline fun countWords(text: String): Map<String, Int> =
        text.split(" ").groupingBy { it }.eachCount()
}