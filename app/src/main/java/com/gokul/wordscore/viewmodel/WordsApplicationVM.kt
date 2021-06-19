package com.gokul.wordscore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.gokul.wordscore.model.Words


class WordsApplicationVM(application: Application): AndroidViewModel(application) {
    private val wordsVM: WordsVM = WordsVM.instance!!

    fun requestData(words: String){
        wordsVM.requestWordData(words)

    }

    fun obverseData(): MutableLiveData<Words>{
        return wordsVM.observeWordData()
    }
}