package pl.marosek.mgrmarko.calendarJava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import pl.marosek.mgrmarko.CalendarActivityJava;
import pl.marosek.mgrmarko.R;
import pl.marosek.mgrmarko.databinding.FragmentFirstJavaBinding;

public class FirstFragmentJava extends Fragment {

    private FragmentFirstJavaBinding binding;
    private String selectedDate;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstJavaBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        selectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("d/M/yyyy"));
        updateListView(selectedDate);

        binding.calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            updateListView(selectedDate);
        });

//        binding.listView.setOnItemClickListener((AdapterView<?> parent, View v, int position, long id) -> {
//            Object selectedItem = parent.getItemAtPosition(position);
//            Integer itemId = null;
//
//            for (CalendarActivityJava.MoodData item : CalendarActivityJava.moodList) {
//                String displayText = "Mood: " + item.mood + " \nDescription: " + item.description;
//                if (selectedItem.equals(displayText)) {
//                    itemId = item.id;
//                    break;
//                }
//            }
//
//            if (itemId != null) {
//                Bundle bundle = new Bundle();
//                bundle.putString("ItemId", itemId.toString());
//                NavHostFragment.findNavController(FirstFragmentJava.this)
//                        .navigate(R.id.action_First2Fragment_to_Second2Fragment, bundle);
//            }
//        });

        binding.listView.setOnItemClickListener((parent, view12, position, id) -> {
            Object selectedItem = parent.getItemAtPosition(position);
            Integer itemId = null;

            //Toast.makeText(getContext(), "Selected item: " + selectedItem, Toast.LENGTH_SHORT).show();

            for (CalendarActivityJava.MoodData item : CalendarActivityJava.moodList) {
                String displayText = "Mood: " + item.mood + " \n Description: " + item.description;
                //Toast.makeText(getContext(), "Display Text: " + displayText, Toast.LENGTH_SHORT).show();
                if (selectedItem.equals(displayText)) {
                    itemId = item.id;
                    //Toast.makeText(getContext(), "Selected item ID: " + itemId, Toast.LENGTH_SHORT).show();
                    break;
                }
            }

            if (itemId != null) {
                Bundle bundle = new Bundle();
                bundle.putString("itemId", itemId.toString());
                NavHostFragment.findNavController(FirstFragmentJava.this)
                        .navigate(R.id.action_First2Fragment_to_Second2Fragment, bundle);
            }
        });

        binding.buttonFirst.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("Date", selectedDate);
            NavHostFragment.findNavController(FirstFragmentJava.this)
                    .navigate(R.id.action_First2Fragment_to_Second2Fragment, bundle);
        });
    }

    private void updateListView(String selectedDate){
        List<CalendarActivityJava.MoodData> filteredList = new ArrayList<>();
        for (CalendarActivityJava.MoodData item : CalendarActivityJava.moodList) {
            if (selectedDate.equals(item.date)) {
                filteredList.add(item);
            }
        }

        if(filteredList.isEmpty()){
            binding.buttonFirst.setVisibility(View.VISIBLE);
        } else {
            binding.buttonFirst.setVisibility(View.GONE);
        }

        List<String> displayItems = new ArrayList<>();

        for (CalendarActivityJava.MoodData item : filteredList) {
            displayItems.add("Mood: " + item.mood + " \n Description: " + item.description);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                displayItems);
        binding.listView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}