package ru.mirea.udalov.mireaproject

data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)