package com.gerasimosGk.kotlinmvvmsample.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.gerasimosGk.kotlinmvvmsample.R
import com.gerasimosGk.kotlinmvvmsample.data.model.domain.CustomToolbarModel
import com.gerasimosGk.kotlinmvvmsample.databinding.LayoutCustomToolbarBinding
import com.gerasimosGk.kotlinmvvmsample.presentation.visible
import java.lang.ref.WeakReference

/**
 * Created by Gerasimos on 30/12/2021
 *
 *  Custom Toolbar that imitates the native Toolbar.
 *  This view extends the FrameLayout and inflates a custom layout.
 */
class CustomToolbar(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    private var toolbarBinding: LayoutCustomToolbarBinding
    private var listener: WeakReference<BackButtonListener?>? = null

    init {
        val inflater = LayoutInflater.from(context)
        toolbarBinding = LayoutCustomToolbarBinding.inflate(inflater, this, true)

        initView(attrs = attrs)
    }

    private fun initView(attrs: AttributeSet) {
        val attributeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar)
        setTitle(attributeArray.getString(R.styleable.CustomToolbar_title) ?: "")
        attributeArray.recycle()

        // Back Button Click listener
        toolbarBinding.backButtonView.setOnClickListener {
            listener?.get()?.onBackButtonCLickListener()
        }
    }

    /**
     * Sets a weak reference of BackButtonListener interface BackButtonListener
     * @param listener of type WeakReference<BackButtonListener>
     */
    fun setListener(listener: WeakReference<BackButtonListener?>?) {
        this.listener = listener
    }

    /**
     * Provide a CustomToolbarModel to set the view of CustomToolbar
     * @param customToolbarModel of type [CustomToolbarModel]
     */
    fun setView(customToolbarModel: CustomToolbarModel) {
        toolbarBinding.titleTextView.text = context?.getString(customToolbarModel.title) ?: ""
        toolbarBinding.backButtonView.visible(customToolbarModel.enableBackButton)
    }

    /**
     * Sets the Toolbar title
     * @param title of type String
     */
    fun setTitle(title: String) {
        toolbarBinding.titleTextView.text = title
        toolbarBinding.titleTextView.setTextAppearance(R.style.Toolbar_TextStyle)
    }

    /**
     * Custom toolbar interface for on back button click listener
     */
    interface BackButtonListener {
        fun onBackButtonCLickListener()
    }
}