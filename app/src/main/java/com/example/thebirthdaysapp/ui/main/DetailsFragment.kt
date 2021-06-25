package com.example.thebirthdaysapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thebirthdaysapp.R
import com.example.thebirthdaysapp.viewmodels.MainViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {

    val viewModel: MainViewModel by activityViewModels()
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
        initialsTextView.text = viewModel.getInitialsToShowFromIndex(indexOfItemClickedOn)

        val nameTextView = view.findViewById<TextView>(R.id.textViewNameLarge)
        nameTextView.text = viewModel.getNameToShowFromIndex(indexOfItemClickedOn)

        val ageTextView = view.findViewById<TextView>(R.id.textViewAge)
        ageTextView.text = viewModel.getAgeTextToShowFromIndex(indexOfItemClickedOn)

        val goBackButton = view.findViewById<TextView>(R.id.buttonGoBack)
        goBackButton.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToListOfResultsFragment()
            view.findNavController().navigate(action)
        }
    }

}