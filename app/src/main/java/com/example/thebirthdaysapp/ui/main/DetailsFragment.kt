package com.example.thebirthdaysapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thebirthdaysapp.R
import com.example.thebirthdaysapp.helpers.getAgeTextToShow
import com.example.thebirthdaysapp.helpers.getNameAndInitialsToShow
import com.example.thebirthdaysapp.viewmodels.MainViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val indexOfItemClickedOn = args.idInDataSetOfItemClickedOn

        val initialsTextView = view.findViewById<TextView>(R.id.textViewLargeDiscWithInitials)
        val dataRecord = viewModel.originalBirthdayResultsFetched!![indexOfItemClickedOn]

        val (initials, name) = getNameAndInitialsToShow(dataRecord.name.first, dataRecord.name.last)
        initialsTextView.text = initials

        val nameTextView = view.findViewById<TextView>(R.id.textViewNameLarge)
        nameTextView.text = name

        val ageTextView = view.findViewById<TextView>(R.id.textViewAge)
        ageTextView.text = getAgeTextToShow(dataRecord.dob.age)

        val goBackButton = view.findViewById<TextView>(R.id.buttonGoBack)
        goBackButton.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToListOfResultsFragment()
            view.findNavController().navigate(action)
        }
    }

}