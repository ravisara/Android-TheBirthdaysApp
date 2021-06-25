package com.example.thebirthdaysapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thebirthdaysapp.api.Result
import com.example.thebirthdaysapp.R
import com.example.thebirthdaysapp.helpers.convertISO8601DateTimeStringToHumanReadableUTCDateTimeString
import com.example.thebirthdaysapp.helpers.getNameAndInitialsToShow

class CustomAdapterForRecyclerView(private val dataSet: List<Result>) : RecyclerView.Adapter<CustomAdapterForRecyclerView.ViewHolder>() {

    // Define listener member variable
    var listener: OnItemClickListener? = null

    // Define the listener interface - note a functional interface is being used to facilitate shorter code for interfaces with SAM
    fun interface OnItemClickListener {
        fun onItemClick(listIndexInDataSetOfItemTappedOn: Int)
    }

    // Define the method that allows the parent activity or fragment to define the listener
    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    /**
    * Provide a reference to the type of views that you are using
    * (custom ViewHolder).
    */
    inner class ViewHolder(singleBirthdayItemView: View) : RecyclerView.ViewHolder(singleBirthdayItemView), View.OnClickListener {

        // get handles for each of the text views
        val initialsDiscTextView: TextView = singleBirthdayItemView.findViewById(R.id.textViewWithDisc)
        val nameTextView: TextView = singleBirthdayItemView.findViewById(R.id.textViewName)
        val dateOfBirthTextView: TextView = singleBirthdayItemView.findViewById(R.id.textViewDOB)

        init {
            // Setup the click listener
            // Triggers click upwards to the adapter on click
            singleBirthdayItemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {

            if (listener != null) {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener!!.onItemClick(position)
                }
            }
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.single_birthday_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val dataRecord = dataSet[position] // to improve on performance, the row is temporarily saved.

        val (initials, name) = getNameAndInitialsToShow(dataRecord.name.first, dataRecord.name.last)
        viewHolder.initialsDiscTextView.text = initials
        viewHolder.nameTextView.text = name

        viewHolder.dateOfBirthTextView.text = convertISO8601DateTimeStringToHumanReadableUTCDateTimeString(dataRecord.dob.date)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}