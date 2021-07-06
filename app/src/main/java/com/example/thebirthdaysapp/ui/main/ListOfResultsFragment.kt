package com.example.thebirthdaysapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.thebirthdaysapp.R
import com.example.thebirthdaysapp.api.Result
import com.example.thebirthdaysapp.helpers.Resource
import com.example.thebirthdaysapp.viewmodels.MainViewModel

class ListOfResultsFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels() // as data is shared between the fragments, this is scoped to the activity
    private lateinit var theRecyclerView: RecyclerView
    private lateinit var customAdapterForRecyclerView: CustomAdapterForRecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.list_of_results_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadData()
        initializeRecyclerViewer()

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBarOnRV)
        progressBar.isVisible = true

        val observer = Observer<Resource<List<Result>>> { birthdaysResource ->
            when (birthdaysResource) {
                is Resource.Success -> { // data won't be null if it gets here. Error conditions have been checked and guarded against in the repository.
                    progressBar.isVisible = false
                    customAdapterForRecyclerView.dataSet = birthdaysResource.data
                    customAdapterForRecyclerView.notifyDataSetChanged()
                    // customAdapterForRecyclerView.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW
                }
                is Resource.Error -> {
                    progressBar.isVisible = false
                    Toast.makeText(context, birthdaysResource.message, Toast.LENGTH_SHORT).show()

                }
                is Resource.Loading -> {
                    progressBar.isVisible = false
                    Toast.makeText(context, "Loading...please wait", Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.birthdayResultsResource.observe(viewLifecycleOwner, observer)

    }

    private fun initializeRecyclerViewer() {

        customAdapterForRecyclerView = CustomAdapterForRecyclerView(null)

        theRecyclerView = requireView().findViewById(R.id.recyclerViewBirthdays)

        theRecyclerView.apply {
            adapter = customAdapterForRecyclerView
            layoutManager = LinearLayoutManager(requireContext())

            val itemDecoration: ItemDecoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            addItemDecoration(itemDecoration)
        }

        val listener =  CustomAdapterForRecyclerView.OnItemClickListener { listIndexInDataSetOfItemTappedOn ->
                val action = ListOfResultsFragmentDirections.actionListOfResultsFragmentToDetailsFragment(listIndexInDataSetOfItemTappedOn)
                view?.findNavController()?.navigate(action)
            }

        customAdapterForRecyclerView.setOnItemClickListener(listener)

        // customAdapterForRecyclerView.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT  // TODO this has failed to restore the position of the recylerview. When Up button is pressed position is restored but not when the "GO BACK" button is pressed.

    }

}