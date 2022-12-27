package com.gerasimosGk.kotlinmvvmsample.presentation.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Created by Gerasimos on 27/11/2021
 *
 * BaseAdapter implementation inspired from a coworker
 */
abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseAdapter<T>.BaseViewHolder>() {

    protected var data: MutableList<T?> = mutableListOf()
    protected lateinit var context: Context

    abstract fun createItemViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder


    abstract inner class BaseViewHolder(binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun onBindData(item: T?)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBindData(if (position < data.size) data[position] else null)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return createItemViewHolder(parent, viewType)
    }

    protected fun inflate(@LayoutRes id: Int, container: ViewGroup): View {
        context = container.context
        return LayoutInflater.from(context).inflate(id, container, false)
    }

    fun set(data: MutableList<T>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }
}