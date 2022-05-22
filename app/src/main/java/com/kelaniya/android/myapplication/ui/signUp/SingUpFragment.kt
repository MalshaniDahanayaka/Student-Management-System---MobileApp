package com.kelaniya.android.myapplication.ui.signUp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.api.RetrofitBuilder
import com.kelaniya.android.myapplication.databinding.FragmentSingUpBinding
import com.kelaniya.android.myapplication.model.OtpRequest
import com.kelaniya.android.myapplication.model.OtpResponse
import com.kelaniya.android.myapplication.model.UserSignupRequest
import com.kelaniya.android.myapplication.model.UserSignupResponse
import com.kelaniya.android.myapplication.utils.EmailValidation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class SingUpFragment : Fragment() {

    private var _binding: FragmentSingUpBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSingUpBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signupSignupButton.setOnClickListener {
            val userID:String  = binding.signUpUserId.text.toString()
            val userEmail:String = binding.signUpUserEmail.text.toString()
            val userFirstName:String = binding.signUpUserFirstName.text.toString()
            val userLastName:String = binding.signUpUserLastName.text.toString()
            val password:String = binding.signUpUserPassword.text.toString()
            val conformPassword:String = binding.signUpUserConformPassword.text.toString()
            val studentCheckbox:Boolean = binding.signupStudentCheckbox.isChecked
            val lectureCheckBox:Boolean = binding.signupLecturerCheckbox.isChecked
            val obj = EmailValidation()

            if(userID=="" || userEmail =="" || userFirstName == "" || userLastName == "" || conformPassword == "" )
            {
                Toast.makeText(view.context,"Fill Blank Details", Toast.LENGTH_SHORT).show()
            }else if(!studentCheckbox && !lectureCheckBox){
                Toast.makeText(view.context,"Select User Role", Toast.LENGTH_SHORT).show()
            }else if(studentCheckbox && lectureCheckBox){
                Toast.makeText(view.context,"Only select One user Role", Toast.LENGTH_SHORT).show()
            }else if(password != conformPassword){
                Toast.makeText(view.context,"Confirm Password Not Matching", Toast.LENGTH_SHORT).show()
            }else if (!obj.isValidString(userEmail)){
                Toast.makeText(view.context,"not valid email", Toast.LENGTH_SHORT).show()
            }else{



                var otpRequest:OtpRequest = OtpRequest(userEmail)
                val retrofit = RetrofitBuilder()
                var retrofitData = retrofit.retrofitBuilder.getOTP(otpRequest)

                retrofitData!!.enqueue(object : Callback<OtpResponse> {
                    override fun onResponse(call: Call<OtpResponse>, response: Response<OtpResponse>) {

                        val otpResponse = response.body()


                        try{
                            var role = ""
                            role = if(studentCheckbox){
                                "Student"
                            }else{
                                "Lecturer"
                            }

                            println("otppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp" + otpResponse!!.otp)
                            var userSignupRequest:UserSignupRequest = UserSignupRequest(userEmail,role,userFirstName,userLastName,userID,password)
                            showDialog(view.context, otpResponse!!.otp,userSignupRequest)

                        }catch (e: Exception){

                            Log.i("error","empty results")
                        }

                    }
                    override fun onFailure(call: Call<OtpResponse>, t: Throwable) {
                        Log.i("FirstFragment", t.message!!)
                    }
                })


            }

        }
    }

    fun showDialog(context: Context, otp: String, userSignupRequest: UserSignupRequest) {
        val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(context)
        builder.setTitle("OTP Code")

// Set up the input
        val input = EditText(context)
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setHint("Enter OTP")
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

// Set up the buttons
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            // Here you get get input text from the Edittext
            var enterValue = input.text.toString()
            if(enterValue == otp){

                val retrofit = RetrofitBuilder()
                var retrofitData = retrofit.retrofitBuilder.userSignup(userSignupRequest)
                retrofitData!!.enqueue(object : Callback<UserSignupResponse> {
                    override fun onResponse(call: Call<UserSignupResponse>, response: Response<UserSignupResponse>) {
                        val otpResponse = response.body()
                        try{
                            if(otpResponse!!.username != null){
                                Toast.makeText(context,"Successfully signup",Toast.LENGTH_SHORT).show()
                                findNavController().navigate(R.id.action_signUp_to_login)
                            }
                        }catch (e: Exception){
                            Log.i("error","empty results")
                        }
                    }
                    override fun onFailure(call: Call<UserSignupResponse>, t: Throwable) {
                        Log.i("FirstFragment", t.message!!)
                    }
                })

            }
            else{
                Toast.makeText(context,"Wrong OTP Code",Toast.LENGTH_SHORT).show()
            }
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }


}