package com.media.profilecheck.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.media.profilecheck.UserDetails
import com.media.profilecheck.api.ApiParam
import com.media.profilecheck.databinding.UsersAdapterBinding
import com.media.profilecheck.models.UsersResult
import com.media.profilecheck.utils.loadImage


class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val usersList =  ArrayList<UsersResult>()
    private lateinit var context : Context

    fun setList (data: ArrayList<UsersResult>, context: Context)
    {
        this.context = context
        usersList.clear()
        usersList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding : UsersAdapterBinding = UsersAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userData = usersList[position]
        holder.bind(userData)

    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    inner class UserViewHolder(val view: UsersAdapterBinding) : RecyclerView.ViewHolder(view.root)
    {
        fun bind(userData: UsersResult) {
            with(userData)
            {
                view.tvUserName.text = "${name?.first} ${name?.last}"
                view.tvUserEmail.text = email
                view.tvUserGender.text = gender
                view.tvUserPhone.text = phone
                view.ivUserImage.loadImage(picture?.large)
                view.cvUserLayout.setOnClickListener {
                    val intent = Intent(context, UserDetails::class.java)
                    val gson = Gson()
                    intent.putExtra(ApiParam.USER_DATA, gson.toJson(userData))
                    context.startActivity(intent)
                }
            }
        }
    }

    fun getFilter(searchQuery: String): Boolean {

        val filteredArrayList: ArrayList<UsersResult> = ArrayList()
        if (searchQuery.isEmpty()) {
            filteredArrayList.addAll(usersList)
        } else {
            for (users in usersList) {
                if (users.name?.first?.contains(searchQuery) == true || users.name?.last?.contains(searchQuery) == true) {
                    filteredArrayList.add(users)
                }
            }
        }
        setList(filteredArrayList, context)
        return filteredArrayList.isNotEmpty()
    }


}