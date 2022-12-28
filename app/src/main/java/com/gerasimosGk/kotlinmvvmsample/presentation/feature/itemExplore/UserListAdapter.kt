package com.gerasimosGk.kotlinmvvmsample.presentation.feature.itemExplore

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gerasimosGk.kotlinmvvmsample.databinding.ListItemBinding
import com.gerasimosGk.kotlinmvvmsample.presentation.base.BaseAdapter
import com.gerasimosGk.kotlinmvvmsample.presentation.loadImage
import java.lang.ref.WeakReference

class UserListAdapter : BaseAdapter<UIUserListModel>() {

    private var weakListener: WeakReference<AdapterListener?>? = null

    /**
     * Sets tha listener interface
     * @param weakListener WeakReference<UserListFragmentContract.Adapter>
     */
    fun setListener(weakListener: WeakReference<AdapterListener?>?) {
        this.weakListener = weakListener
    }

    // This method takes an instance of a ViewHolder with our inflated layout
    override fun createItemViewHolder(parent: ViewGroup, viewType: Int) : BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(itemBinding)
    }

    inner class ViewHolder(private val binding: ListItemBinding) : BaseViewHolder(binding) {

        // We bind the layout with our model
        override fun onBindData(item: UIUserListModel?) {
            binding.apply {

                coverImageView.loadImage(url = item?.coverImage ?: "")
                descriptionTextView.text = item?.description ?: ""

                // Click Listener
                cardViewContainer.setOnClickListener {
                    item?.let {
                        weakListener?.get()?.onCardListClicked(id = it.id)
                    }
                }
            }
        }
    }

    interface AdapterListener {
        fun onCardListClicked(id: String)
    }
}