package com.gokul.wordscore

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gokul.wordscore.adapter.WordAdapter
import com.gokul.wordscore.databinding.ActivityMainBinding
import com.gokul.wordscore.viewmodel.WordsApplicationVM

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: WordsApplicationVM
    private lateinit var adapter: WordAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        vm = ViewModelProvider(this, factory).get(WordsApplicationVM::class.java)



        binding.btnCheck.setOnClickListener {
            val search = binding.etSearch.text.toString().trim()
            if (search != "") {
                vm.requestData(binding.etSearch.text.toString())
                hitApi()

            }
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != "") {
                    vm.requestData(binding.etSearch.text.toString())
                    binding.pbLoading.visibility = View.VISIBLE
                    hitApi()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })


    }

    private fun hitApi() {
        vm.obverseData().observe(this, Observer {
            binding.ivNoData.visibility = View.GONE
            if (it != null) {
                adapter = WordAdapter(it)
                binding.rvScore.adapter = adapter
                try {
                    binding.pbLoading.visibility = View.GONE
                    binding.ivNoData.visibility = View.GONE
                } catch (e: Exception) {

                }

            }
            if(it ==  null || it.size==0){
                binding.pbLoading.visibility = View.GONE
                if(binding.etSearch.text.toString() == ""){
                    binding.ivNoData.visibility = View.INVISIBLE
                }
                else{
                    binding.ivNoData.visibility = View.VISIBLE
                }

            }
        })

    }


}