package com.kelaniya.android.myapplication.ui.login

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.databinding.FragmentLoginBinding

class LoginFragment : Fragment(){


    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSignup.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_signUp)
        }

        binding.buttonLogin.setOnClickListener{
            findNavController().navigate(R.id.action_login_to_nav_home)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}