package pl.marosek.mgrmarko.calendarJava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Random;

import pl.marosek.mgrmarko.CalendarActivityJava;
import pl.marosek.mgrmarko.R;
import pl.marosek.mgrmarko.databinding.FragmentSecondJavaBinding;

public class SecondFragmentJava extends Fragment {

    private FragmentSecondJavaBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondJavaBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String selectedDate;
        String itemIdStr;


        if (getArguments() != null) {
            selectedDate = getArguments().getString("Date");
            itemIdStr = getArguments().getString("itemId");
            //Toast.makeText(getContext(), "itemId: " + itemIdStr, Toast.LENGTH_SHORT).show();
        } else {
            selectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("d/M/yyyy"));
            itemIdStr = null;
        }

        binding.textviewDate.setText("Selected Date: " + selectedDate);

        binding.numberPickerMood.setMinValue(0);
        binding.numberPickerMood.setMaxValue(10);

        if (itemIdStr != null) {
            binding.buttonDelete.setVisibility(View.VISIBLE);

            int itemId = Integer.parseInt(itemIdStr);
            for (CalendarActivityJava.MoodData item : CalendarActivityJava.moodList) {
                if (item.id == itemId) {
                    binding.numberPickerMood.setValue(item.mood);
                    binding.editTextDayDesc.setText(item.description);
                    break;
                }
            }
        }

        binding.buttonSecond.setOnClickListener(v -> {
            if (itemIdStr != null) {
                int itemId = Integer.parseInt(itemIdStr);
                //Toast.makeText(getContext(), "itemId: " + itemId, Toast.LENGTH_SHORT).show();
                saveEditedInformation(itemId,
                        binding.numberPickerMood.getValue(),
                        binding.editTextDayDesc.getText().toString());
            } else {
                saveInformation(selectedDate,
                        binding.numberPickerMood.getValue(),
                        binding.editTextDayDesc.getText().toString());
            }

            NavHostFragment.findNavController(SecondFragmentJava.this)
                    .navigate(R.id.action_Second2Fragment_to_First2Fragment);
        });

        binding.buttonDelete.setOnClickListener(v -> {
            if (itemIdStr != null) {
                int itemId = Integer.parseInt(itemIdStr);
                deleteInformation(itemId);
            }
            NavHostFragment.findNavController(SecondFragmentJava.this)
                    .navigate(R.id.action_Second2Fragment_to_First2Fragment);
        });

//        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(SecondFragmentJava.this)
//                        .navigate(R.id.action_Second2Fragment_to_First2Fragment);
//            }
//        });
    }

    private void saveInformation(String date, int mood, String description) {
        CalendarActivityJava.MoodData newData = new CalendarActivityJava.MoodData(
                new Random().nextInt(2_000_000),
                date,
                mood,
                description
        );
        CalendarActivityJava.moodList.add(newData);
    }

    private void saveEditedInformation(int itemId, int mood, String description) {
        for (CalendarActivityJava.MoodData item : CalendarActivityJava.moodList) {
            if (item.id == itemId) {
                item.mood = mood;
                item.description = description;
                break;
            }
        }
    }

    private void deleteInformation(int itemId) {
        Iterator<CalendarActivityJava.MoodData> iterator = CalendarActivityJava.moodList.iterator();
        while (iterator.hasNext()) {
            CalendarActivityJava.MoodData item = iterator.next();
            if (item.id == itemId) {
                iterator.remove();
                break;
            }
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}