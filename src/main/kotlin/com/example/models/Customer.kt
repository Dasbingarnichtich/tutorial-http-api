package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Customer (val id: String, val firstName: String, val lastName: String, val eMail: String)

val customerStorage = mutableListOf<Customer>()
