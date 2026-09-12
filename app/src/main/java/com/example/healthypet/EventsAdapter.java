package com.example.healthypet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// WHAT THIS FILE DOES :

// facilitates the display of events in the RecyclerView,
// allows users to modify or delete events,
// and provides flexibility for custom event handling through the listener interface

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {

    //List of events to display
    private List<Event> eventsList;
    //Listener for handling "modify" & "delete" buttons
    private OnEventListener onEventListener;

    // constructor to initialize the adapter with events list and event listener
    public EventsAdapter(List<Event> eventsList, OnEventListener onEventListener) {
        this.eventsList = eventsList;
        this.onEventListener = onEventListener;
    }


    // inflates the layout for each event item
    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(itemView, onEventListener);
    }


    // binds data to the views in each event item
    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventsList.get(position);
        holder.eventNameTextView.setText(event.getName());
        holder.eventDateTextView.setText(String.format("%02d/%02d/%04d %02d:%02d",
                event.getDay(), event.getMonth() + 1, event.getYear(), event.getHour(), event.getMinute()));
    }


    // returns the total number of events in the list
    @Override
    public int getItemCount() {
        return eventsList.size();
    }


    // Updates the list of events with new data
    public void updateEvents(List<Event> newEvents) {
        eventsList.clear();
        eventsList.addAll(newEvents);
        notifyDataSetChanged();
    }


    // View holder class to hold the views for each event item
    static class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView eventNameTextView;
        TextView eventDateTextView;
        ImageButton eventModifyButton;
        ImageButton eventDeleteButton;
        OnEventListener onEventListener;

        public EventViewHolder(@NonNull View itemView, OnEventListener onEventListener) {
            super(itemView);
            eventNameTextView = itemView.findViewById(R.id.eventNameTextView);
            eventDateTextView = itemView.findViewById(R.id.eventDateTextView);
            eventModifyButton = itemView.findViewById(R.id.buttonModify);
            eventDeleteButton = itemView.findViewById(R.id.buttonDelete);
            this.onEventListener = onEventListener;

            // Set click listeners for modify and delete buttons
            eventModifyButton.setOnClickListener(this);
            eventDeleteButton.setOnClickListener(this);
        }

        // Handles button clicks
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.buttonModify) {
                // Notify the listener when the modify button is clicked
                onEventListener.onModifyEvent(getAdapterPosition());
            } else if (v.getId() == R.id.buttonDelete) {
                // Notify the listener when the delete button is clicked
                onEventListener.onDeleteEvent(getAdapterPosition());
            }
        }
    }

    // Interface for handling modify and delete events
    public interface OnEventListener {
        void onModifyEvent(int position);
        void onDeleteEvent(int position);
    }
}
