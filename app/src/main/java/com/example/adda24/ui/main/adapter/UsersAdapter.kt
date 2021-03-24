package com.example.adda24.ui.main.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.adda24.R
import com.example.adda24.data.api.model.Data

internal class UsersAdapter(list: ArrayList<Data>, val context: Activity) :
    RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {

    private var showList = ArrayList<Data>()


    init {
        showList.clear()
        showList.addAll(list)
    }

    fun refreshList(list: ArrayList<Data>) {
        showList.clear()
        showList.addAll(list)
        kotlin.run {
            notifyDataSetChanged()
        }
    }

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_title: TextView = view.findViewById(R.id.tv_title)
        var tv_status: TextView = view.findViewById(R.id.tv_status)
        var tv_email: TextView = view.findViewById(R.id.tv_email)
        var tv_gender: TextView = view.findViewById(R.id.tv_gender)

    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_users, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return showList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = showList[position]
        if (!TextUtils.isEmpty(item.name))
            holder.tv_title.text = item.name
        if (!TextUtils.isEmpty(item.status))
            holder.tv_status.text = item.status
        if (!TextUtils.isEmpty(item.email))
            holder.tv_email.text = item.email
        if (!TextUtils.isEmpty(item.gender))
            holder.tv_gender.text = item.gender
    }
}