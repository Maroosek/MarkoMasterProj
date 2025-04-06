package pl.marosek.mgrmarko.textUtil

class TextCounterKotlin {
    fun countWords(text: String): Map<String, Int> {
        val words = text.split(" ")
        val wordCount = mutableMapOf<String, Int>()
        for (word in words) {
            if (wordCount.containsKey(word)) {
                wordCount[word] = wordCount[word]!! + 1
            } else {
                wordCount[word] = 1
            }
        }
        return wordCount
    }
}