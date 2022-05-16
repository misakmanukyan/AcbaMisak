package com.example.acbacommons.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import com.example.acbacommons.viewmodel.MainViewModel
import com.example.acbacommons.viewmodel.MainViewModelFactory
import com.example.acbacommons.edittext.validators.ValidatorListener
import com.example.acbacommons.databinding.ActivityMainBinding
import com.example.acbacommons.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    private val TAG = "ACBA_BUTTON"
    private var lastTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            validate.setOnClickListener {
                //validate(root)
                testAcbaButton()
                //Toast.makeText(this@MainActivity, "Clicked", Toast.LENGTH_SHORT).show()
            }
        }

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this) { response ->
            if (response.isSuccessful) {
                Log.d("Response", response.body()?.userId.toString())
                Log.d("Response", response.body()?.id.toString())
                Log.d("Response", response.body()?.title!!)
                Log.d("Response", response.body()?.body!!)
                binding.text.text = response.body()?.body!!
            } else {
                Log.d("Response", response.errorBody().toString())
            }
        }
    }

    private fun testAcbaButton() {
        val currentTime = SystemClock.elapsedRealtime()
        Log.d(TAG, "Last click time: $lastTime")
        Log.d(TAG, "Current time: $currentTime")
        Log.d(TAG, "Difference: ${currentTime - lastTime}")
        Log.d(TAG, "CLICKED")
        //lastTime = currentTime
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