package com.example.healthypet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


// files related to the calendar :

//      item_event.xml (IS FOR RENDER OF THE EVENTS INFO)
//      dialog_add_event.xml (IS FOR THE EVENTS INFO SELECTION)
//      fragment_c_a_l_e_n_d_a_r.xml (IS FOR THE LAYOUT OF THE FRAGMENT CALENDAR)
//      EventsAdapter.java (FACILITATES THE DISPLAY OF EVENTS IN THE RECYCLERVIEW)
//      Event.java (THIS CLASS REPRESENT AN EVENT WITH ITS NAME / DATE / TIME)
//      FragmentCALENDAR (ALLOWS USERS TO ADD / MODIFY / DELETE EVENTS + THAT THEY ARE PROPERLY STORED & DISPLAYED BASED ON THEIR DATES)






public class FragmentCALENDAR extends Fragment implements EventsAdapter.OnEventListener{
    private CalendarView calendarView;
    private RecyclerView recyclerViewEvents;
    private EventsAdapter eventsAdapter;

    // Map to store events for each date
    private Map<String, List<Event>> eventsMap = new HashMap<>(); // Map to store events for each date

    // Events for the currently selected date
    private List<Event> currentEvents = new ArrayList<>(); // Events for the currently selected date






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_c_a_l_e_n_d_a_r, container, false);
        // Initialize views
        calendarView = view.findViewById(R.id.calendarView2);
        recyclerViewEvents = view.findViewById(R.id.recyclerViewEvents);

        // Set layout manager and adapter for RecyclerView
        recyclerViewEvents.setLayoutManager(new LinearLayoutManager(getContext()));
        eventsAdapter = new EventsAdapter(currentEvents, this);
        recyclerViewEvents.setAdapter(eventsAdapter);

        // Set listener for date changes in CalendarView
        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            String dateKey = generateDateKey(year, month, dayOfMonth);
            showAddEventDialog(dateKey, year, month, dayOfMonth);
        });

        return view;
    }



    // Show dialog to add a new event
    private void showAddEventDialog(String dateKey, int year, int month, int dayOfMonth) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Do you want to add an event?")
                .setPositiveButton("Yes", (dialog, which) -> showEventDetailsDialog(dateKey, year, month, dayOfMonth))
                .setNegativeButton("No", (dialog, which) -> displayEventsForDate(dateKey));
        builder.create().show();
    }





    // Show dialog to enter event details (type of event / date / hour)
    private void showEventDetailsDialog(String dateKey, int year, int month, int dayOfMonth) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_event, null);
        builder.setView(dialogView);

        Spinner spinnerEvent = dialogView.findViewById(R.id.spinnerEvent);
        DatePicker datePicker = dialogView.findViewById(R.id.inputDATE);
        TimePicker timePicker = dialogView.findViewById(R.id.inputHOUR);

        // Set the date picker to the selected date
        datePicker.updateDate(year, month, dayOfMonth);

        builder.setPositiveButton("Add", (dialog, which) -> {
            // Get event details and save the event
            String eventName = spinnerEvent.getSelectedItem().toString();
            int eventYear = datePicker.getYear();
            int eventMonth = datePicker.getMonth();
            int eventDay = datePicker.getDayOfMonth();
            int eventHour = timePicker.getCurrentHour();
            int eventMinute = timePicker.getCurrentMinute();

            // Create and save the event
            Event newEvent = new Event(eventName, eventYear, eventMonth, eventDay, eventHour, eventMinute);
            saveEvent(dateKey, newEvent);

            // Refresh events for the selected date
            displayEventsForDate(dateKey);
        }).setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }





    // Display events for a specific date
    private void displayEventsForDate(String dateKey) {
        // Fetch events for the given date from the data source
        List<Event> eventsForDate = eventsMap.getOrDefault(dateKey, new ArrayList<>());

        // Update the adapter with the new list of events
        currentEvents.clear();
        currentEvents.addAll(eventsForDate);
        eventsAdapter.notifyDataSetChanged();
    }



    // Save an event to the data source
    private void saveEvent(String dateKey, Event event) {
        // Save the event to the data source
        List<Event> eventsForDate = eventsMap.getOrDefault(dateKey, new ArrayList<>());
        eventsForDate.add(event);
        eventsMap.put(dateKey, eventsForDate);
    }



    // Handle event modification
    @Override
    public void onModifyEvent(int position) {
        Event event = currentEvents.get(position);
        showModifyEventDialog(event, position);
    }



    // Handle event deletion
    @Override
    public void onDeleteEvent(int position) {
        // Remove the event from the current list and notify the adapter
        Event event = currentEvents.get(position);
        currentEvents.remove(position);
        eventsAdapter.notifyItemRemoved(position);

        // Remove the event from the persistent storage
        String dateKey = generateDateKey(event.getYear(), event.getMonth(), event.getDay());
        eventsMap.get(dateKey).remove(event);
    }



    // Show dialog to modify an existing event
    private void showModifyEventDialog(Event event, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_event, null);
        builder.setView(dialogView);

        Spinner spinnerEvent = dialogView.findViewById(R.id.spinnerEvent);
        DatePicker datePicker = dialogView.findViewById(R.id.inputDATE);
        TimePicker timePicker = dialogView.findViewById(R.id.inputHOUR);

        // Set the existing event details in the dialog
        spinnerEvent.setSelection(getSpinnerIndex(spinnerEvent, event.getName()));
        datePicker.updateDate(event.getYear(), event.getMonth(), event.getDay());
        timePicker.setCurrentHour(event.getHour());
        timePicker.setCurrentMinute(event.getMinute());

        builder.setPositiveButton("Modify", (dialog, which) -> {
            // Update the event details
            String eventName = spinnerEvent.getSelectedItem().toString();
            int eventYear = datePicker.getYear();
            int eventMonth = datePicker.getMonth();
            int eventDay = datePicker.getDayOfMonth();
            int eventHour = timePicker.getCurrentHour();
            int eventMinute = timePicker.getCurrentMinute();

            // Update the event object
            event.setName(eventName);
            event.setYear(eventYear);
            event.setMonth(eventMonth);
            event.setDay(eventDay);
            event.setHour(eventHour);
            event.setMinute(eventMinute);

            // Notify the adapter about the update
            eventsAdapter.notifyItemChanged(position);

            // Update the persistent storage
            String dateKey = generateDateKey(eventYear, eventMonth, eventDay);
            eventsMap.get(dateKey).set(position, event);

            // Refresh events for the selected date
            displayEventsForDate(dateKey);
        }).setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }





    // Find the index of an item in the spinner
    private int getSpinnerIndex(Spinner spinner, String value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(value)) {
                return i;
            }
        }
        return 0;
    }




    // Generate a key for a given date so that each date has a unique key in the map of events
    private String generateDateKey(int year, int month, int dayOfMonth) {
        return String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
    }
}