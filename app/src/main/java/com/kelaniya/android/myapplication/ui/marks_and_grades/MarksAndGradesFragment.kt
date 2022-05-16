package com.kelaniya.android.myapplication.ui.marks_and_grades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kelaniya.android.myapplication.adapter.MarksAndGradesViewAdapter
import com.kelaniya.android.myapplication.databinding.FragmentMarksAndGradesBinding
import com.kelaniya.android.myapplication.ui.my_courses.EnrollCourseListData


class MarksAndGradesFragment : Fragment() {

    private var _binding: FragmentMarksAndGradesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMarksAndGradesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.marksAndGradesRecyclerView.layoutManager = LinearLayoutManager(view.context)
        MarksAndGradesData(binding.marksAndGradesRecyclerView,findNavController()).setStudentRecordsView()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}