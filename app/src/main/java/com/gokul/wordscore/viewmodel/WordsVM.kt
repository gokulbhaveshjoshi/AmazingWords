package com.gokul.wordscore.viewmodel

import androidx.databinding.adapters.NumberPickerBindingAdapter.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gokul.wordscore.api.ServiceBuilder
import com.gokul.wordscore.api.WordApi
import com.gokul.wordscore.model.Words
import com.gokul.wordscore.model.WordsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WordsVM: ViewModel() {
    private val words: MutableLiveData<Words> = MutableLiveData()

   fun requestWordData(search: String){
       val wordsCall: Call<Words> = myInterface.getWords(search)
       wordsCall.enqueue(object : Callback<Words> {
           override fun onResponse(call: Call<Words>, response: Response<Words>) {
               words.value = response.body()
           }

           override fun onFailure(call: Call<Words>, t: Throwable) {
               words.postValue(null)
           }

       })
   }

    fun observeWordData(): MutableLiveData<Words> {
        return words

    }

    companion object {
        private lateinit var myInterface: WordApi
        private var wordsVM: WordsVM? = null
        val instance: WordsVM?
            get() {
                if (wordsVM == null) {
                    wordsVM = WordsVM()
                }
                return wordsVM
            }
    }

    init {
        myInterface = ServiceBuilder.buildService(WordApi::class.java)
    }

}