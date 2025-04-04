package pl.marosek.mgrmarko

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import pl.marosek.mgrmarko.databinding.FragmentSecondBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedDate = arguments?.getString("Date") ?: LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

        //Toast.makeText(context, "Selected date2: $selectedDate", Toast.LENGTH_SHORT).show()

        binding.textviewDate.text = selectedDate

        binding.numberPickerMood.minValue = 0
        binding.numberPickerMood.maxValue = 10



        binding.buttonSecond.setOnClickListener {

            val moodNumber = binding.numberPickerMood.value
            val dayDescription = binding.editTextDayDesc.text.toString()

            saveInformation(selectedDate, moodNumber, dayDescription)

            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun saveInformation(selectedDate : String, moodNumber: Int, dayDescription: String) {
        val moodData = MoodData(
            id = Random.nextInt(2000000), // You can set a proper ID if needed
            date = selectedDate, // Replace with the actual date
            mood = moodNumber,
            description = dayDescription
        )
        moodList.add(moodData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}