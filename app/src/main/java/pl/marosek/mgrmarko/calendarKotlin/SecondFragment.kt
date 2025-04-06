package pl.marosek.mgrmarko.calendarKotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import pl.marosek.mgrmarko.MoodData
import pl.marosek.mgrmarko.R
import pl.marosek.mgrmarko.databinding.FragmentSecondBinding
import pl.marosek.mgrmarko.moodList
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random

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

        val selectedDate = arguments?.getString("Date") ?: LocalDate.now().format(DateTimeFormatter.ofPattern("d/M/yyyy"))

        val itemId = arguments?.getString("ItemId")

        binding.textviewDate.text = "Selected Date: $selectedDate"

        binding.numberPickerMood.minValue = 0
        binding.numberPickerMood.maxValue = 10

        //Toast.makeText(context, "Selected item: $itemId", Toast.LENGTH_SHORT).show()

        if (itemId != null) {
            binding.buttonDelete.visibility = View.VISIBLE
        }

        if (itemId != null) {
            for (i in moodList) {
                if (i.id.toString() == itemId) {
                    binding.editTextDayDesc.setText(i.description)
                    binding.numberPickerMood.value = i.mood
                }
            }
        }

        binding.buttonSecond.setOnClickListener {
            if (itemId != null) {
                saveEditedInformation(itemId.toInt(), binding.numberPickerMood.value, binding.editTextDayDesc.text.toString())
            } else {
                saveInformation(selectedDate, binding.numberPickerMood.value, binding.editTextDayDesc.text.toString())
            }

            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.buttonDelete.setOnClickListener {
            if (itemId != null) {
                deleteInformation(itemId.toInt())
            }
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun saveInformation(selectedDate : String, moodNumber: Int, dayDescription: String) {
        val moodData = MoodData(
            id = Random.nextInt(2000000),
            date = selectedDate,
            mood = moodNumber,
            description = dayDescription
        )
        moodList.add(moodData)
    }

    private fun saveEditedInformation(itemId: Int, moodNumber: Int, dayDescription: String) {
        for (i in moodList) {
            if (i.id == itemId) {
                i.mood = moodNumber
                i.description = dayDescription
            }
        }
    }

    private fun deleteInformation(itemId: Int) {
        for (i in moodList) {
            if (i.id == itemId) {
                moodList.remove(i)
                break
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}