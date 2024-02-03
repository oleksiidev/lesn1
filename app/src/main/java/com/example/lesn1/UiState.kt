package com.example.lesn1

class UiState<T> (
    val data: T? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)