package com.example.passapp.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object FirebaseUtils {
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    // Remove the following line. This is automatically generated and causing the conflict.
    // fun <get-auth>(): FirebaseAuth = auth

    // This method is no longer needed if you're just accessing `auth` directly.
    // You can directly use `auth` wherever you need it.


    fun getCurrentUserId(): String? = auth.currentUser?.uid

    fun getUserVaultRef() =
        getCurrentUserId()?.let { uid ->
            db.collection("users").document(uid).collection("vault")
        }

    fun getLoginLogsRef() = db.collection("logins")

    fun getDatabase() = db
}


