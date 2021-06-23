package com.example.thebirthdaysapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thebirthdaysapp.api.Result
import com.example.thebirthdaysapp.R

class CustomAdapterForRecyclerView(private val dataSet: List<Result>) : RecyclerView.Adapter<CustomAdapterForRecyclerView.ViewHolder>() {

    /**
    * Provide a reference to the type of views that you are using
    * (custom ViewHolder).
    */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val initialsDiscTextView: TextView
        val nameTextView: TextView
        val dateOfBirthTextView: TextView

        init {
            // get handles for each of the text views
            initialsDiscTextView = view.findViewById(R.id.textViewWithDisc)
            nameTextView = view.findViewById(R.id.textViewName)
            dateOfBirthTextView = view.findViewById(R.id.textViewDOB)
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
        viewHolder.initialsDiscTextView.text = "Temp"//dataSet[position] // TODO
        viewHolder.nameTextView.text = dataSet[position].name.toString()
        viewHolder.dateOfBirthTextView.text = dataSet[position].dob.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}