package com.kelaniya.android.myapplication.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.kelaniya.android.myapplication.MainActivity
import com.kelaniya.android.myapplication.R
import com.kelaniya.android.myapplication.databinding.FragmentConnectionErrorBinding
import com.kelaniya.android.myapplication.databinding.FragmentHomeBinding


class ConnectionError : Fragment() {


    private var _binding: FragmentConnectionErrorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentConnectionErrorBinding.inflate(inflater, container, false)
        return binding.root

    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var fromLocation:String? = null
        fromLocation = requireArguments().getString("last_location") as String

        binding.tryAgain.setOnClickListener {

            if(checkForInternet(view.context)) {
                if (fromLocation == "homePage") {
                    findNavController().navigate(R.id.action_connetion_error_to_nav_home)
                } else if (fromLocation == "loginPage") {
                    findNavController().navigate(R.id.action_connetion_error_to_login)
                } else {
                    findNavController().navigate(R.id.action_connetion_error_to_login)
                }

            }else{
                Toast.makeText(view.context,"Still Internet Connection Not Working",Toast.LENGTH_SHORT).show()
            }
        }


    }


    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }




}