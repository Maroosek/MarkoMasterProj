package pl.marosek.mgrmarko

import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.marosek.mgrmarko.fileManager.FileManager
import pl.marosek.mgrmarko.textUtil.TextCounterJava
import pl.marosek.mgrmarko.textUtil.TextCounterKotlin
import pl.marosek.mgrmarko.textUtil.TextCounterKotlinInline
import java.text.DecimalFormat


class StringActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_string)

        val numberPickerString = findViewById<NumberPicker>(R.id.NumberPickerString)
        val stringTextView = findViewById<TextView>(R.id.StringText)
        val stringButton = findViewById<Button>(R.id.StringButton)
        numberPickerString.minValue = 500
        numberPickerString.maxValue = 18000

        stringButton.setOnClickListener {
            val numberOfLoops = numberPickerString.value
            val df = DecimalFormat("#.###")
            stringTextView.text = "Generating"

            lifecycleScope.launch {
               val text = withContext(Dispatchers.Default) { extendLoremIpsum(numberOfLoops) }

                val (wordsKtInline, kotlinInlineTime) = withContext(Dispatchers.Default) {
                    val start = System.currentTimeMillis()
                    val result = TextCounterKotlinInline().countWords(text)
                    val end = System.currentTimeMillis()
                     result to df.format((end - start) * 0.001).toString()
                }

                val (wordsKt, kotlinTime) = withContext(Dispatchers.Default) {
                    val start = System.currentTimeMillis()
                    val result = TextCounterKotlin().countWords(text)
                    val end = System.currentTimeMillis()
                    result to df.format((end - start) * 0.001).toString()
                }

                val (wordsJava, javaTime) = withContext(Dispatchers.Default) {
                    val start = System.currentTimeMillis()
                    val result = TextCounterJava().countWords(text)
                    val end = System.currentTimeMillis()
                    result to df.format((end - start) * 0.001).toString()
                }

                val combinedTimes = "Kotlin inline: $kotlinInlineTime s\n" +
                        "Kotlin: $kotlinTime s\n" +
                        "Java: $javaTime s\n"

                FileManager().saveDataToFile(
                        this@StringActivity, // lub inna nazwa aktywności
                        "String",
                        "Lorem Ipsum extended $numberOfLoops Times \n$combinedTimes"
                )

                stringTextView.text = "Lorem Ipsum extended $numberOfLoops Times \n$combinedTimes"
                }
            }
        }

        fun checkEquality(map1: Map<String, Int>, map2: Map<String, Int>): Boolean {
                if (map1.size != map2.size) return false
                for ((key, value) in map1) {
                        if (map2[key] != value) return false
                }
                return true
        }

        private fun extendLoremIpsum(n: Int): String {
                val loremIpsum =
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec suscipit pharetra dolor, a vulputate nisl blandit in. Pellentesque ante mauris, tempor non diam eget, bibendum porttitor ipsum. Aliquam eros nunc, auctor quis vulputate sed, posuere et urna. Nulla condimentum purus quis magna iaculis, vel viverra mi tincidunt. Sed convallis a sapien eu suscipit. Maecenas a enim tellus. Maecenas suscipit sapien nec dui vehicula pellentesque. Nulla suscipit massa mauris, a efficitur neque mattis vel. Maecenas a urna vel nunc finibus bibendum finibus non enim. Donec pretium massa mi, at scelerisque ipsum ornare eget. Quisque elementum tristique elementum." +
                                "Cras pharetra porttitor ipsum, vel ultrices sem imperdiet vulputate. Nunc luctus, ipsum non eleifend vulputate, felis ante commodo velit, a tempor massa nisl non metus. Duis lacinia ante eget commodo euismod. Quisque placerat ultrices nisl nec pretium. Ut elementum felis lacinia commodo luctus. Nam a bibendum augue, ac facilisis urna. Morbi venenatis finibus nisi, et fringilla risus aliquam ac. Cras euismod urna urna, a rhoncus dui finibus quis. Morbi commodo ligula ac felis suscipit, id lacinia tellus consequat. Proin vehicula dolor ut nunc dignissim fermentum. Sed porttitor rhoncus ex, eu faucibus purus feugiat a. Curabitur sit amet dictum lorem, in condimentum nulla. Nam vel efficitur lorem. Phasellus imperdiet, ante vel dapibus lacinia, nisl nibh hendrerit metus, dignissim eleifend nibh metus nec tortor." +
                                "Etiam sagittis odio ac elit pharetra, nec dictum nibh venenatis. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla a ligula eu diam congue posuere. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. In pellentesque justo quis tristique accumsan. Pellentesque nec urna sed mi gravida tempus sed at purus. Ut egestas rutrum mauris, vitae elementum mauris tincidunt quis. Etiam ac laoreet ex. Vestibulum vel tellus ut risus varius pulvinar sed nec ante. Proin congue enim non massa fermentum ultricies. Quisque porttitor sem quam, vel placerat est pellentesque tincidunt. Donec vulputate augue sit amet enim hendrerit, quis tincidunt leo dignissim. Aenean dapibus bibendum urna, non hendrerit diam rutrum sed." +
                                "Sed sit amet libero condimentum, suscipit diam et, semper nunc. Donec eu risus vitae odio porttitor volutpat eget sed nisl. Nam sit amet quam vel tortor maximus placerat. Quisque et ultricies elit. Donec dui urna, placerat eu tempor eget, blandit ac sem. Aenean sem mauris, tincidunt vitae est ut, laoreet rutrum leo. Pellentesque accumsan a enim at varius. Praesent id velit dignissim, tincidunt erat a, semper eros. Phasellus leo purus, iaculis non dapibus quis, semper nec urna. Aenean eu leo sit amet magna egestas fringilla et vel enim. Donec nec elementum odio." +
                                "Suspendisse imperdiet vitae ipsum eget vehicula. Vivamus dictum elit at purus sollicitudin fermentum. Donec fermentum malesuada massa vel cursus. Duis at tincidunt justo. Proin ullamcorper neque at odio rhoncus rutrum. Duis pellentesque egestas porta. Proin sed tortor vitae eros ultrices tincidunt. Donec elit metus, posuere in risus pulvinar, convallis cursus ex. Ut lacinia, ipsum sit amet congue pretium, mi eros fringilla nibh, eget porttitor dui lectus eu enim. Quisque pulvinar justo id dolor malesuada semper. Suspendisse ac risus varius, blandit leo vel, finibus nisl. Vivamus faucibus imperdiet massa. Fusce nec tempor risus. Proin velit massa, lacinia molestie tempor sit amet, suscipit ut nibh. Vivamus in tortor vitae nibh cursus interdum."

            return buildString(loremIpsum.length * n) {
                repeat(n) {
                    append(loremIpsum)
                }
            }
        }

}
