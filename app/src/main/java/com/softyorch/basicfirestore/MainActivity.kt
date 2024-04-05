package com.softyorch.basicfirestore

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class MainActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firestore = Firebase.firestore
        basicInsert()
    }

    private fun basicInsert() {
        val user = hashMapOf(
            "name" to "Yorch",
            "age" to 30,
            "happy" to true,
            "extraInfo" to null
        )

        //firestore.collection("users").add(user)
        //val isSuccessful = firestore.collection("users").add(user).isSuccessful
        //firestore.collection("users").add(user).await()
        firestore.collection("users").add(user)
            .addOnSuccessListener {
                Log.i("LOGTA", "Success")
            }.addOnFailureListener {
                Log.e("LOGTAG", "Error: ${it.message}")
            }
    }
}