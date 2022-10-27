package com.anthonyyoab.androdev.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.anthonyyoab.androdev.R
import com.anthonyyoab.androdev.databinding.FragmentHomeBinding
import com.anthonyyoab.androdev.ui.adapter.JobListAdapter
import com.anthonyyoab.androdev.ui.model.JobListModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: JobListAdapter
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.jobList.layoutManager = LinearLayoutManager(requireActivity())
//
//        val listJobList = ArrayList<JobListModel>()
//        listJobList.add(JobListModel("Job Title", R.drawable.github, "Company Name"))
//        listJobList.add(JobListModel("Job Title", R.drawable.github, "Company Name"))
//        listJobList.add(JobListModel("Job Title", R.drawable.github, "Company Name"))
//        listJobList.add(JobListModel("Job Title", R.drawable.github, "Company Name"))
//        listJobList.add(JobListModel("Job Title", R.drawable.github, "Company Name"))
//        listJobList.add(JobListModel("Job Title", R.drawable.github, "Company Name"))
//        listJobList.add(JobListModel("Job Title", R.drawable.github, "Company Name"))
//        listJobList.add(JobListModel("Job Title", R.drawable.github, "Company Name"))
//        listJobList.add(JobListModel("Job Title", R.drawable.github, "Company Name"))
//        listJobList.add(JobListModel("Job Title", R.drawable.github, "Company Name"))
//        listJobList.add(JobListModel("Job Title", R.drawable.github, "Company Name"))
//        listJobList.add(JobListModel("Job Title", R.drawable.github, "Company Name"))
//        listJobList.add(JobListModel("Job Title", R.drawable.github, "Company Name"))
//
//
//        adapter = JobListAdapter(listJobList)
//
//        binding.jobList.adapter = adapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}