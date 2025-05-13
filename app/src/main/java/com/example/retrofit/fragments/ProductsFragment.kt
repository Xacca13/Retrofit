package com.example.retrofit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.adapter.ProductAdapter
import com.example.retrofit.controller.ProductController
import com.example.retrofit.databinding.FragmentProductsBinding
import com.example.retrofit.model.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsFragment : Fragment() {
    private lateinit var adapter: ProductAdapter
    private lateinit var binding: FragmentProductsBinding
    private val productController = ProductController()
    private val viewModel: LoginViewModel by activityViewModels()
    private var token = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getToken()
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

/*
        CoroutineScope(Dispatchers.IO).launch {
            val list = productController.getFromUserAllProduct(token).products
            requireActivity().runOnUiThread {
                adapter.submitList(list)
            }
        }
*/


        binding.searchView.setOnQueryTextListener(object  : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
/*                CoroutineScope(Dispatchers.IO).launch {
                    val products = query?.let { productController.getFromUserAllProductsByName(token, it).products }
                    requireActivity().runOnUiThread {
                        binding.apply {
                            adapter.submitList(products)
                        }
                    }
                }*/
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                CoroutineScope(Dispatchers.IO).launch {
                    val products = newText?.let { productController.getFromUserAllProductsByName(token, it).products }
                    requireActivity().runOnUiThread {
                        binding.apply {
                            adapter.submitList(products)
                        }
                    }
                }
                return true
            }

        })
    }

    private fun initAdapter() = with(binding) {
        adapter = ProductAdapter()
        rcView.layoutManager = LinearLayoutManager(context)
        rcView.adapter = adapter
    }

    private fun getToken() {
        viewModel.token.observe(viewLifecycleOwner) {
            token = it
        }
        }
}