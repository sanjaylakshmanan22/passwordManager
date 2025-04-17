package com.example.passapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.passapp.databinding.ActivityVaultBinding
import com.example.passapp.models.VaultEntry
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class VaultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVaultBinding
    private val vaultList = mutableListOf<VaultEntry>()
    private val adapter = VaultAdapter(vaultList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVaultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vaultRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.vaultRecyclerView.adapter = adapter

        fetchVaultEntries()
    }

    private fun fetchVaultEntries() {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(uid).collection("vault")
            .get()
            .addOnSuccessListener { documents ->
                vaultList.clear()
                for (doc in documents) {
                    val entry = doc.toObject(VaultEntry::class.java)
                    vaultList.add(entry)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to load vault", Toast.LENGTH_SHORT).show()
            }
    }
}
