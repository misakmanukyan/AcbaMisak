package com.example.acbacommons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.acbacommons.ACBAEditText.validators.ValidatorListener
import com.example.acbacommons.databinding.ActivityMainBinding
import com.example.acbacommons.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            validate.setOnClickListener {
                validate(root)
            }
        }

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.d("Response", response.body()?.userId.toString())
                Log.d("Response", response.body()?.id.toString())
                Log.d("Response", response.body()?.title!!)
                Log.d("Response", response.body()?.body!!)
                binding.text.text = response.body()?.body!!
            } else {
                Log.d("Response", response.errorBody().toString())
            }
        })
    }

    private fun validate(root: View) {
        if (root is ViewGroup) {
            for (view in root.children) {
                validate(view)
            }
        } else if (root is ValidatorListener) {
            if (root.isRequiredForValidation()) {
                root.isValid()
            }
        }
    }
}