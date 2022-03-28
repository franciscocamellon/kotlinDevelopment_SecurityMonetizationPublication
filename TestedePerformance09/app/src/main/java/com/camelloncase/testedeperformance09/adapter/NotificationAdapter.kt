package com.camelloncase.testedeperformance09.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.camelloncase.testedeperformance09.adapter.viewholder.NotificationViewHolder
import com.camelloncase.testedeperformance09.databinding.NotificationItemBinding
import com.camelloncase.testedeperformance09.model.Notification

class NotificationAdapter(
    var notificationsList: List<Notification>,
    var onClickListener: OnItemClickListener
    ) : RecyclerView.Adapter<NotificationViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val itemBinding = NotificationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notificationsList[position]

        holder.bind(notification)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(notification, position)
        }
    }

    override fun getItemCount(): Int {
        return notificationsList.size
    }

    interface OnItemClickListener {
        fun onClick(notification: Notification, position: Int)
    }
}