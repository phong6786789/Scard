package com.subi.scard.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.subi.scard.R
import com.subi.scard.databinding.DialogConfirmBinding

interface OnButtonDialog {

    /**
     * function handler button Right Click
     */
    fun onButtonRight()

    /**
     *
     */
    fun onButtonLeft()

}

interface LeftInterface {
    fun onClick()
}


interface RightInterface {
    fun onClick()
}

class ShowDialog(
    val title: String?,
    val message: String?,
    val leftButton: String?,
    val rightButton: String?,
    val handlerOnClick: OnButtonDialog?
) {


    private constructor(builder: Builder) : this(
        builder.title,
        builder.message,
        builder.leftButton,
        builder.rightButton,
        builder.handlerOnClick
    )

    class Builder {

        constructor(context: Context) {
            this.context = context
        }

        var context: Context? = null
            private set
        var title: String? = null
            private set

        var message: String? = null
            private set

        var leftButton: String? = null
            private set

        var rightButton: String? = null
            private set

        var handlerOnClick: OnButtonDialog? = null
            private set

        var leftListener: LeftInterface? = null
            private set

        var rightListener: RightInterface? = null
            private set

        fun title(title: String) = apply { this.title = title }

        fun message(mes: String) = apply { this.message = mes }

        fun leftButton(left: String) = apply { this.leftButton = left }

        fun rightButton(right: String) = apply { this.rightButton = right }

        fun handlerOnClick(click: OnButtonDialog?) = apply { this.handlerOnClick = click }

        fun setLeftButton(text: String, onClick: LeftInterface) = apply {
            this.leftButton = text
            this.leftListener = onClick
        }

        fun setRightButton(text: String, onClick: RightInterface) = apply {
            this.rightButton = text
            this.rightListener = onClick
        }


        fun miniDialog(): Dialog? {
            val dialog= context?.let { Dialog(it) }
            val binding = DialogConfirmBinding.inflate(LayoutInflater.from(context))
            dialog?.setContentView(binding.root)
            dialog?.setCancelable(false)
            val window = dialog?.window
            window?.setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            if (dialog?.window != null) {
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }

            dialog?.window?.setWindowAnimations(R.style.Animation_Design_BottomSheetDialog)


            val btn_left = binding.btnCloseMini
            val btn_right = binding.btnOkMini
            binding.left = this.leftButton
            binding.right =this.rightButton
            binding.des = this.message
            binding.title = this.title
            binding.left = this.leftButton
            binding.isRight = true
//
//            btn_right.text = this.leftButton
//            btn_right.text = this.rightButton

            if (this.leftButton.isNullOrEmpty()){
                btn_left.visibility = View.GONE
            }

            btn_left.setOnClickListener {
                leftListener?.onClick()
            }

            btn_right.setOnClickListener {
                rightListener?.onClick()
            }
            return dialog
        }
    }
}