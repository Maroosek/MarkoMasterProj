package pl.marosek.mgrmarko.CalendarKotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import pl.marosek.mgrmarko.R
import pl.marosek.mgrmarko.databinding.FragmentFirstBinding
import pl.marosek.mgrmarko.moodList
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var selectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("d/M/yyyy"))

        updateListView(selectedDate)

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth/${month + 1}/$year"
            //Toast.makeText(context, "Selected date: $selectedDate", Toast.LENGTH_SHORT).show()
            updateListView(selectedDate)
        }

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position)
            var itemId: Int? = null
            for (i in moodList) {
                if (selectedItem == "Mood: ${i.mood} \nDescription: ${i.description}") {
                    itemId = i.id
                    //Toast.makeText(context, "Selected item: $selectedItem $itemId", Toast.LENGTH_SHORT).show()
                }
            }

            val bundle = Bundle()
            bundle.putString("ItemId", itemId.toString())
            //Toast.makeText(context, "Selected itemId: $itemId", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }

        binding.buttonFirst.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("Date", selectedDate)

            //Toast.makeText(context, "Selected date: $selectedDate", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }
    }

    private fun updateListView(selectedDate: String) {

        val filteredList = moodList.filter { it.date == selectedDate }
        //Toast.makeText(context, "Selected date list: $selectedDate", Toast.LENGTH_SHORT).show()
        //Toast.makeText(context, "List: $moodList", Toast.LENGTH_SHORT).show()

        if (filteredList.isEmpty()) {
            binding.buttonFirst.visibility = View.VISIBLE
        } else {
            binding.buttonFirst.visibility = View.GONE
        }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            filteredList.map { "Mood: ${it.mood} \nDescription: ${it.description}" }
        )
        binding.listView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}