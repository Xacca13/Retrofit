package com.example.retrofit

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.adapter.ProductAdapter
import com.example.retrofit.controller.AuthController
import com.example.retrofit.controller.ProductController
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.model.request.AuthBody
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val productController = ProductController()
    val authController = AuthController()
    private lateinit var adapter: ProductAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        adapter = ProductAdapter()
        binding.rcView.layoutManager = LinearLayoutManager(this)
        binding.rcView.adapter = adapter


        CoroutineScope(Dispatchers.IO).launch {
            val products = productController.getAllProduct().products
            runOnUiThread {
                binding.apply {
                    adapter.submitList(products)
                }
            }
        }

/*        binding.button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
//                val product = productController.getProductById(5)
                val user = authController.getUserInfo(
                    AuthBody(
                        binding.editTextName.text.toString(),
                        binding.editTextPassword.text.toString()
                    )
                )
                runOnUiThread {
                    binding.apply {
                        Picasso.get().load(user.image).into(imageView)
                        firstNameTv.text = user.firstName
                        lastNameTv.text = user.lastName
                    }
                }
            }
        }*/
    }
}