package com.kelaniya.android.myapplication.ui.lecturer_upload_lecture_notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.databinding.FragmentLecturerUploadedLectureNotesBinding
import com.kelaniya.android.myapplication.ui.course_lecture_notes.LectureNotesData


class LecturerUploadedLectureNotesFragment : Fragment() {


    private var _binding: FragmentLecturerUploadedLectureNotesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLecturerUploadedLectureNotesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var courseDetails = ArrayList<String>()
        courseDetails = requireArguments().getStringArrayList("courseDetailsData") as ArrayList<String>
        binding.lecturerUploadedLectureNotesRecyclerView.layoutManager = LinearLayoutManager(view.context)
        binding.lecturerUploadedLecNotesCourseId.text = "         " + courseDetails[0]
        val obj: LecturerUploadedLectureNotesData = LecturerUploadedLectureNotesData(courseDetails[0],courseDetails[1],binding.lecturerUploadedLectureNotesRecyclerView,findNavController())
        obj.setDataInPage()


        binding.lecturerUploadedLecNotesAddNewButton.setOnClickListener {
            var courseData = ArrayList<String>()
            courseData.add(courseDetails[0])
            courseData.add(courseDetails[1])
            findNavController().navigate(R.id.action_lecturer_Uploaded_lecture_notes_to_lecturer_add_new_lecture_notes, Bundle().apply {
                putStringArrayList("courseData",courseData)
            })
        }


        binding.lecturerUploadedLecNotesNotificationButton.setOnClickListener {
            var courseData = ArrayList<String>()
            courseData.add(courseDetails[0])
            courseData.add(courseDetails[1])
            findNavController().navigate(R.id.action_lecturer_Uploaded_lecture_notes_to_lecturer_add_announcements_list, Bundle().apply {
                putStringArrayList("courseData",courseData)
            })
        }

        binding.lecturerUploadedLecNotesAddMarksAndGrades.setOnClickListener {
            findNavController().navigate(R.id.action_lecturer_Uploaded_lecture_notes_to_lecturer_enroll_students_list, Bundle().apply {
                putString("courseID",courseDetails[0])
            })
        }

    }

}