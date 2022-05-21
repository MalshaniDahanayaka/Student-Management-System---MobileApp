package com.kelaniya.android.myapplication.ui.add_new_course

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import com.kelaniya.android.myapplication.databinding.FragmentAddNewCourseModuleBinding
import com.kelaniya.android.myapplication.model.Courses


class AddNewCourseModuleFragment : Fragment() {


    private var _binding: FragmentAddNewCourseModuleBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddNewCourseModuleBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addNewCourseAddButton.setOnClickListener {
            val courseID:String = binding.addNewCourseCourseId.text.toString()
            val courseName:String = binding.addNewCourseCourseName.text.toString()
            val facultyName:String = binding.addNewCourseFacultyName.selectedItem.toString()
            val departmentName:String = binding.addNewCourseDepartmentName.selectedItem.toString()
            val level:String = binding.addNewCourseLevel.selectedItem.toString()
            val academicYear:String = binding.addNewCourseAcademicYear.selectedItem.toString()
            val semester:String = binding.addNewCourseSemester.selectedItem.toString()
            val description:String = binding.addNewCourseDescription.text.toString()



            val course:Courses = Courses(courseID,
                courseName,
                "name",
                description,
                facultyName,
                departmentName,
                level,
                academicYear,
                semester)

            SaveNewCourseModule(course,view).saveDataAboutCourse()

            binding.addNewCourseCourseId.setText("")
            binding.addNewCourseCourseName.setText("")
            binding.addNewCourseFacultyName[0]
            binding.addNewCourseDepartmentName[0]
            binding.addNewCourseLevel[0]
            binding.addNewCourseAcademicYear[0]
            binding.addNewCourseSemester[0]
            binding.addNewCourseDescription.setText("")


        }



    }

}