package com.kelaniya.android.myapplication.ui.notifications

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.databinding.FragmentNotificationFullDetailsBinding


class NotificationFullDetailsFragment : Fragment() {


    private var _binding: FragmentNotificationFullDetailsBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNotificationFullDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val notificationDetails = requireArguments().getStringArrayList("announcementDetails") as ArrayList
        binding.notifiacationDetailsLeturerEmail.text = notificationDetails[2]
        binding.notificationDetailsTitle.text = notificationDetails[0]+" ( "+ notificationDetails[3]+" )"
        binding.notificationDetailsBody.text = "                "+ notificationDetails[1]
        binding.notificationDetailsTitle.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG)

    }


}