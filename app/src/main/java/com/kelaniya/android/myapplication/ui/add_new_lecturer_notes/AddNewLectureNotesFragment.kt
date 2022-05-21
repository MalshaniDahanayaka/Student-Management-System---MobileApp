package com.kelaniya.android.myapplication.ui.add_new_lecturer_notes

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kelaniya.android.myapplication.databinding.FragmentAddNewLectureNotesBinding


class AddNewLectureNotesFragment : Fragment() {

    private var _binding: FragmentAddNewLectureNotesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddNewLectureNotesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var courseDetails = ArrayList<String>()
        courseDetails = requireArguments().getStringArrayList("courseData") as ArrayList<String>


        binding.addNewLectureNoteSelectPdfButton.setOnClickListener {
            selectImage(view)
        }


    }


    private fun selectImage( view: View) {
        val choice = arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Cancel")
        val myAlertDialog: AlertDialog.Builder = AlertDialog.Builder(view.context)
        myAlertDialog.setTitle("Select Image")
        myAlertDialog.setItems(choice, DialogInterface.OnClickListener { dialog, item ->
            when {
                // Select "Choose from Gallery" to pick image from gallery
                choice[item] == "Choose from Gallery" -> {
                    val pickFromGallery = Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    pickFromGallery.type = "/image"
                    startActivityForResult(pickFromGallery, 1)
                }
                // Select "Take Photo" to take a photo
                choice[item] == "Take Photo" -> {
                    val cameraPicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraPicture, 0)
                }
                // Select "Cancel" to cancel the task
                choice[item] == "Cancel" -> {
                    dialog.dismiss()
                }
            }
        })
        myAlertDialog.show()
    }


}