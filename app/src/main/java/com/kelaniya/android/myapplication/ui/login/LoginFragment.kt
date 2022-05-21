package com.kelaniya.android.myapplication.ui.login

import android.content.Context
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.databinding.FragmentLoginBinding
import com.kelaniya.android.myapplication.model.JwtUserRequest
import com.kelaniya.android.myapplication.utils.Authentication
import com.kelaniya.android.myapplication.utils.EmailValidation

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
            var userEmail:String = binding.userEmail.text.toString()
            var password:String = binding.password.text.toString()
            if(userEmail!="" && password != ""){
                val obj = EmailValidation()
                if (obj.isValidString(userEmail)){

                    val user = JwtUserRequest(userEmail,password)
                    val authObj = Authentication(user,view.context,findNavController())
                    authObj.authenticate()

                }
                else{
                    Toast.makeText(view.context,"please enter valid email.",Toast.LENGTH_SHORT).show()

                }
            }else if(userEmail == "" && password==""){
                Toast.makeText(view.context,"please provide user email and password",Toast.LENGTH_SHORT).show()

        }else if(userEmail == ""){
                    Toast.makeText(view.context,"please provide user email",Toast.LENGTH_SHORT).show()

        }else if(password==""){
                    Toast.makeText(view.context,"please provide user password",Toast.LENGTH_SHORT).show()

            }


        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}