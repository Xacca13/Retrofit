package com.example.retrofit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.retrofit.R
import com.example.retrofit.controller.AuthController
import com.example.retrofit.controller.ProductController
import com.example.retrofit.databinding.FragmentAuthBinding
import com.example.retrofit.model.LoginViewModel
import com.example.retrofit.model.request.AuthBody
import com.example.retrofit.model.user.User
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AuthFragment : Fragment() {

    private val authController = AuthController()
    private lateinit var binding: FragmentAuthBinding
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonNext.setOnClickListener {
                findNavController().navigate(R.id.action_AuthFragment_to_ProductsFragment)

            }

            buttonSignIn.setOnClickListener {
                authUser(
                    AuthBody(
                    loginEditText.text.toString(),
                    passwordEditText.text.toString()
                )
                )
            }
        }

    }

    private fun authUser(authBody: AuthBody) {
        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<User> = authController.getUserInfo(authBody)
            val messageError = response.errorBody()?.string()?.let { JSONObject(it).getString("message") }
            requireActivity().runOnUiThread {
                binding.errorText.text = messageError
                val user = response.body()
                if (user != null) {
                    Picasso.get().load(user.image).into(binding.avatar)
                    binding.nameUser.text = user.firstName
                    binding.buttonNext.visibility = View.VISIBLE
                    viewModel.token.value = user.accessToken
                }
            }
        }
    }
}