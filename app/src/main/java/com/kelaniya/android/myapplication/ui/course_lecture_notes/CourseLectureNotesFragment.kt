package com.kelaniya.android.myapplication.ui.course_lecture_notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kelaniya.android.myapplication.databinding.FragmentCourseLectureNotesBinding
import com.kelaniya.android.myapplication.model.GetLectureNotesRequest


class CourseLectureNotesFragment : Fragment() {


    private var _binding: FragmentCourseLectureNotesBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCourseLectureNotesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var courseDetails = ArrayList<String>()
        courseDetails = requireArguments().getStringArrayList("courseDetails") as ArrayList<String>
        binding.recyclerViewLectureNotes.layoutManager = LinearLayoutManager(view.context)
        binding.courseIdLectureNotesPage.text = "                        "+courseDetails[0]
        val obj:LectureNotesData = LectureNotesData(courseDetails[0],courseDetails[1],binding.recyclerViewLectureNotes,findNavController())
        obj.setDataInPage()

    }
}