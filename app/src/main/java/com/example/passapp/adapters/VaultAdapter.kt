package com.example.passapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.passapp.databinding.ItemVaultEntryBinding
import com.example.passapp.models.VaultEntry

class VaultAdapter(private val vaultList: List<VaultEntry>) : RecyclerView.Adapter<VaultAdapter.VaultViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaultViewHolder {
        val binding = ItemVaultEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VaultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VaultViewHolder, position: Int) {
        val entry = vaultList[position]
        holder.bind(entry)
    }

    override fun getItemCount(): Int = vaultList.size

    class VaultViewHolder(private val binding: ItemVaultEntryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(entry: VaultEntry) {
            binding.titleTextView.text = entry.title
            binding.usernameTextView.text = entry.username
            binding.passwordTextView.text = entry.password
        }
    }
}
