package pl.marosek.mgrmarko

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import pl.marosek.mgrmarko.databinding.FragmentFirstBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

//TODO Add edit option to second fragment and recheck list view behavior
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

        var selectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

        updateListView(selectedDate)

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth/${month + 1}/$year"
            updateListView(selectedDate)
        }

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position)
            var itemId: Int? = null
            for (i in moodList){
                if (selectedItem == "${i.date} - ${i.mood} - ${i.description}"){
                    itemId = i.id
                    Toast.makeText(context, "Selected item: $selectedItem $itemId", Toast.LENGTH_SHORT).show()
                }
            }

            val bundle = Bundle()
            bundle.putString("ItemId", itemId.toString())
            //Toast.makeText(context, "Selected item: $selectedItem $itemId", Toast.LENGTH_SHORT).show()
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }

        binding.buttonFirst.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("Date", selectedDate)

            //Toast.makeText(context, "Selected date: $selectedDate", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }
    }

    fun updateListView(selectedDate: String) {

        val filteredList = moodList.filter { it.date == selectedDate }
//        Toast.makeText(context, "Selected date: $selectedDate", Toast.LENGTH_SHORT).show()
//        Toast.makeText(context, "Filtered list size: $moodList", Toast.LENGTH_SHORT).show()

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            filteredList.map { "${it.date} - ${it.mood} - ${it.description}" }
        )
        binding.listView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}