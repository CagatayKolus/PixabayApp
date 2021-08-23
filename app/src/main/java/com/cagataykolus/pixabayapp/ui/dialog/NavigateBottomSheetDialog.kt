package com.cagataykolus.pixabayapp.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import com.cagataykolus.pixabayapp.R
import com.cagataykolus.pixabayapp.databinding.BottomSheetDialogNavigationBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 * Created by Çağatay Kölüş on 22.08.2021.
 * cagataykolus@gmail.com
 */
class NavigateBottomSheetDialog(context: Context) {
    private val bottomSheetDialog: BottomSheetDialog =
        BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
    private var selectionAction: ((NavigateBottomSheetAction) -> Unit)? = null

    init {
        val layoutInflater = LayoutInflater.from(context)
        val binding = BottomSheetDialogNavigationBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(binding.root)
        bottomSheetDialog.window?.setDimAmount(0.5f)

        binding.apply {
            binding.buttonDialogYes.setOnClickListener {
                selectionAction?.invoke(NavigateBottomSheetAction.YesClicked)
            }

            binding.buttonDialogCancel.setOnClickListener {
                selectionAction?.invoke(NavigateBottomSheetAction.CancelClicked)
            }
        }
    }

    fun onAction(action: (NavigateBottomSheetAction) -> Unit) = apply {
        this.selectionAction = action
    }

    fun show() = apply {
        bottomSheetDialog.show()
    }

    fun dismiss() = apply {
        bottomSheetDialog.dismissWithAnimation = true
        bottomSheetDialog.dismiss()
    }
}

sealed class NavigateBottomSheetAction {
    object YesClicked : NavigateBottomSheetAction()
    object CancelClicked : NavigateBottomSheetAction()
}