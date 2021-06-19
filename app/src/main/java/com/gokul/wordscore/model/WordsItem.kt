package com.gokul.wordscore.model

data class WordsItem(
    val score: Int,
    val tags: List<String>,
    val word: String
)