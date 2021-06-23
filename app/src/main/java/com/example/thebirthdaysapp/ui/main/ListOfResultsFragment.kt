package com.example.thebirthdaysapp.ui.main

import android.content.DialogInterface
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thebirthdaysapp.R
import com.example.thebirthdaysapp.helpers.Resource
import com.example.thebirthdaysapp.viewmodels.MainViewModel
import com.example.thebirthdaysapp.api.Result

class ListOfResultsFragment : Fragment() {

    companion object {
        fun newInstance() = ListOfResultsFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var theRecyclerView: RecyclerView
    private lateinit var customAdapterForRecyclerView: CustomAdapterForRecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.list_of_results_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        theRecyclerView = view.findViewById(R.id.recyclerViewBirthdays)
        val viewModel: MainViewModel by viewModels()

        viewModel.getBirthdaysAndOtherData()

        val observer = Observer<Resource<List<Result>>> { birthdaysResource ->
            when (birthdaysResource) {
                is Resource.Success -> { // data won't be null if it gets here. Error conditions have been checked and guarded against in the repository.
                    customAdapterForRecyclerView = CustomAdapterForRecyclerView(birthdaysResource.data!!)
                    theRecyclerView.apply {
                        adapter = customAdapterForRecyclerView
                        layoutManager = LinearLayoutManager(requireContext())
                    }
                }
                is Resource.Error -> {

                    Toast.makeText(context, birthdaysResource.message, Toast.LENGTH_SHORT).show()

                }
                is Resource.Loading -> {

                    Toast.makeText(context, "Loading...please wait", Toast.LENGTH_SHORT).show()

                }
            }
        }
        viewModel.birthdayResultsResource.observe(viewLifecycleOwner, observer)

    }

    private fun initializeRecyclerViewer() {

        val listener =
            CustomAdapterForRecyclerView.OnItemClickListener { listIndexInDataSetOfItemTappedOn ->

                /*val action =
                    DetailsFragmentDirections.actionDetailsFragmentToCollectionOfPhotoPagesFragment(
                        listIndexInDataSetOfItemTappedOn
                    )*/


                //view?.findNavController()?.navigate(action)
            }
    }

}