package pl.marosek.mgrmarko

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import pl.marosek.mgrmarko.databinding.ActivityCalendarBinding

data class MoodData(
    val id: Int,
    var date: String,
    var mood: Int,
    var description: String
)
var moodList = mutableListOf<MoodData>()

class CalendarActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityCalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_calendar)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Select Date and click Add to add day mood\n To edit entry simply click on it", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_calendar)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}