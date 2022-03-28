package com.camelloncase.testedeperformance09.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.camelloncase.testedeperformance09.databinding.NotificationItemBinding
import com.camelloncase.testedeperformance09.model.Notification

class NotificationViewHolder (private var binding: NotificationItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(notification: Notification) {
        binding.weekAliasTextView.text = notification.weekAlias
        binding.observationsTextView.text = notification.weekObservations
        binding.createDateTextView.text = notification.weekCreateDate
        binding.updateDateTextView.text = notification.weekUpdateDate
    }
}