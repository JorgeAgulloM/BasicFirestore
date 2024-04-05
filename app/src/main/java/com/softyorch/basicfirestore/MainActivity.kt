package com.softyorch.basicfirestore

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MainActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firestore = Firebase.firestore
        //basicInsert()
        //multipleInserts()
        //basicReadData()
        basicReadDocument()
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

    private fun multipleInserts() {
        for (i in 0..50) {
            val user = hashMapOf(
                "name" to "Yorch$i",
                "age" to 30 + 1,
                "happy" to (i % 2 == 0),
                "extraInfo" to null
            )
            firestore.collection("users").add(user)
        }
    }

    private fun basicReadData() {
        firestore.collection("users").get()
            .addOnSuccessListener { snapshot ->
                Log.i("LOGTAG", "success")
                snapshot.forEach { doc ->
                    val id = doc.id
                    Log.i("LOGTAG", "id: $id -> value: ${doc.data}")
                }
            }.addOnFailureListener {

            }
    }

    private fun basicReadDocument() {
        lifecycleScope.launch {
            val result =
                firestore.collection("users").document("joKyHcRGrUHvTDKIJEnu").get().await()
            val id = result.id
            Log.i("LOGTAG", "id: $id -> value: ${result.data}")
        }
    }
}