package com.subi.scard.view.fragment.health

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import androidx.lifecycle.viewModelScope
import com.subi.pokemonproject.data.network.BaseNetwork
import com.subi.scard.R
import com.subi.scard.base.viewmodel.BaseViewModel
import com.subi.scard.databinding.DialogConfirmBinding
import com.subi.scard.databinding.LayoutInsertHealthBinding
import com.subi.scard.model.Item
import com.subi.scard.utils.ChauManager
import com.subi.scard.utils.Constants
import com.subi.scard.utils.CustomDropDownAdapter
import com.subi.scard.utils.Utils
import io.ghyeok.stickyswitch.widget.StickySwitch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CheckResult")
class HealthViewmodel : BaseViewModel() {
    val TAG = "HomeViewModel"
    val list: ObservableList<Item> = ObservableArrayList()
    var context: Context? = null
    var isEmty = ObservableBoolean(true)
    var emty = ObservableField("Bạn chưa thông tin y tế nào")
    val listHealth = arrayOf(
        Constants.HEALTH_TYPE.BHYT,
        Constants.HEALTH_TYPE.CSSK,
//        Constants.HEALTH_TYPE.COVI,
//        Constants.HEALTH_TYPE.TEST,
    )

    fun deleteItem(id: String) {
        viewModelScope.launch {
            try {
                val res = BaseNetwork.getInstance().deleteItemById(id)
                if (res.isSuccessful) {
                    res.body()?.status?.let {
                        if (it.equals("success")) {
                            load()
                        }
                    }
                } else {
                    Utils.log(TAG, "failed: ${res.errorBody()}")
                }
            } catch (e: Exception) {
                Utils.log(TAG, "erro: ${e.message}")
            }
        }
    }

    fun editItem(item: Item) {
        Log.d("texts", "edit")
        val dialog = context?.let { Dialog(it) }
        val binding = LayoutInsertHealthBinding.inflate(LayoutInflater.from(context))
        dialog?.setContentView(binding.root)
        val window = dialog?.window
        window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        if (dialog?.window != null) {
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        dialog?.window?.setWindowAnimations(R.style.Animation_Design_BottomSheetDialog)

        binding.apply {
            val toolbar= dialog!!.findViewById<RelativeLayout>(R.id.toolbar)

            val left = toolbar.findViewById<ImageView>(R.id.imageLeft)
            val titlex = toolbar.findViewById<TextView>(R.id.textTitle)

            left.setOnClickListener {
                dialog.dismiss()
            }

            titlex.text = "Sửa thông tin y tế"

            val customDropDownAdapter = context?.let { CustomDropDownAdapter(it, listHealth) }
            spinnerItem.adapter = customDropDownAdapter

            for (x in listHealth.indices) {
                if (item.title == listHealth[x]) {
                    spinnerItem.setSelection(x)
                    customDropDownAdapter?.notifyDataSetChanged()
                }
            }

            val des = item.description?.split("@")
            val edt1x = des?.get(0) ?: ""
            val edt2x = des?.get(1) ?: ""

            edtMaSoBH.setText(edt1x)
            edtNoiKham.setText(edt2x)
            binding.stickySwitch.setDirection(if (item.status.equals("0")) StickySwitch.Direction.LEFT else StickySwitch.Direction.RIGHT, false)

            spinnerItem.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (listHealth[position]) {
                        Constants.HEALTH_TYPE.COVI -> {
                            title1 = "Ngày tiêm chủng"
                            edt1 = "Nhập ngày tiêm chủng"
                            edtMaSoBH.inputType = InputType.TYPE_CLASS_TEXT
                            title2 = "Ngày hết hạn"
                            edt2 = "Nhập ngày hết hạn"
                        }

                        else -> {
                            title1 = "Mã số"
                            edt1 = "Nhập mã số"
                            edtMaSoBH.inputType = InputType.TYPE_CLASS_NUMBER
                            title2 = "Họ và tên"
                            edt2 = "Nhập họ và tên"
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    title1 = "Mã số"
                    edt1 = "Nhập mã số"
                    edtMaSoBH.inputType = InputType.TYPE_CLASS_TEXT
                    title2 = "Họ và tên"
                    edt2 = "Nhập họ và tên"
                }

            }

            btnLuuHealth.setOnClickListener {
                val ma = edtMaSoBH.text.toString()
                val ten = edtNoiKham.text.toString()
                if (ma.isNotEmpty() && ten.isNotEmpty()) {
                    val title = binding.spinnerItem.selectedItem.toString()
                    val direction = binding.stickySwitch.getDirection().name
                    context?.let {
                        val status = if (direction == "LEFT") 0 else 1
                        val item = Item(
                            title + Utils.getIdUser(it),
                            title,
                            "$ma@$ten",
                            Constants.ITEM_TYPE.HEALTH,
                            Utils.getIdUser(it),
                            status.toString()
                        )

                        edit(item)
                        dialog?.dismiss()
                    }
                } else {
                    context?.let { it1 -> Utils.showMess(it1, "Không được để trống!") }
                }
            }
        }
        dialog?.show()
    }

    private fun edit(item: Item) {
        viewModelScope.launch {
            try {
                val res = BaseNetwork.getInstance().editItemById(
                    item.id, item.title, item.description, item.type, item.idUser, item.status
                )
                if (res.isSuccessful) {
                    res.body()?.status?.let {
                        if (it.equals("success")) {
                            load()
                        }
                    }

                } else {
                    Utils.log(TAG, "failed: ${res.errorBody()}")
                }
            } catch (e: Exception) {
                Utils.log(TAG, "erro: ${e.message}")
            }
        }
    }

    fun insertItem() {
        val dialog = context?.let { Dialog(it) }
        val binding = LayoutInsertHealthBinding.inflate(LayoutInflater.from(context))
        dialog?.setContentView(binding.root)
        val window = dialog?.window
        window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        if (dialog?.window != null) {
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        dialog?.window?.setWindowAnimations(R.style.Animation_Design_BottomSheetDialog)

        binding.apply {

            val toolbar= dialog!!.findViewById<RelativeLayout>(R.id.toolbar)

            val left = toolbar.findViewById<ImageView>(R.id.imageLeft)
            val titlex = toolbar.findViewById<TextView>(R.id.textTitle)

            left.setOnClickListener {
                dialog.dismiss()
            }

            titlex.text = "Thêm thông tin y tế"
            val customDropDownAdapter = context?.let { CustomDropDownAdapter(it, listHealth) }
            spinnerItem.adapter = customDropDownAdapter

            spinnerItem.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (listHealth[position]) {
                        Constants.HEALTH_TYPE.COVI -> {
                            title1 = "Ngày tiêm chủng"
                            edt1 = "Nhập ngày tiêm chủng"
                            edtMaSoBH.inputType = InputType.TYPE_CLASS_TEXT
                            title2 = "Ngày hết hạn"
                            edt2 = "Nhập ngày hết hạn"
                        }

                        else -> {
                            title1 = "Mã số"
                            edt1 = "Nhập mã số"
                            edtMaSoBH.inputType = InputType.TYPE_CLASS_NUMBER
                            title2 = "Họ và tên"
                            edt2 = "Nhập họ và tên"
                            binding.edtNoiKham.setText(context?.let { Utils.getFullName(it) })
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    title1 = "Mã số"
                    edt1 = "Nhập mã số"
                    edtMaSoBH.inputType = InputType.TYPE_CLASS_TEXT
                    title2 = "Họ và tên"
                    edt2 = "Nhập họ và tên"
                    binding.edtNoiKham.setText(context?.let { Utils.getFullName(it) })
                }

            }

            btnLuuHealth.setOnClickListener {
                val ma = edtMaSoBH.text.toString()
                val ten = edtNoiKham.text.toString()
                if (ma.isNotEmpty() && ten.isNotEmpty()) {
                    val title = binding.spinnerItem.selectedItem.toString()
                    val direction = binding.stickySwitch.getDirection().name
                    context?.let {
                        val status = if (direction == "LEFT") 0 else 1
                        val item = Item(
                            title + Utils.getIdUser(it),
                            title,
                            "$ma@$ten",
                            Constants.ITEM_TYPE.HEALTH,
                            Utils.getIdUser(it),
                            status.toString()
                        )

                        insert(item)
                        dialog?.dismiss()
                    }
                } else {
                    context?.let { it1 -> Utils.showMess(it1, "Không được để trống!") }
                }
            }
        }

        dialog?.show()
    }

    private fun insert(item: Item) {
        viewModelScope.launch {
            try {
                val res = BaseNetwork.getInstance().insertItem(
                    item.id, item.title, item.description, item.type, item.idUser, item.status
                )
                if (res.isSuccessful) {
                    load()
                } else {
                    Utils.log(TAG, "failed: ${res.errorBody()}")
                }
            } catch (e: Exception) {
                Utils.log(TAG, "erro: ${e.message}")
            }
        }
    }


    fun load() {
        val idUser = context?.let { Utils.getIdUser(it) }
        //Get data
        viewModelScope.launch {
            try {
                val response = BaseNetwork.getInstance()
                    .getAllItemByIdUserAndType(idUser ?: "111", Constants.ITEM_TYPE.HEALTH)
                withContext(Dispatchers.Main) {
                    Utils.log(TAG, "response: ${response.body()}")
                    if (response.isSuccessful) {
                        list.clear()
                        response.body()?.getAllList?.let {
                            list.addAll(it)
                            Utils.log(TAG, "size: ${list.size}")
                        }
                        if (!list.isEmpty()){
                            isEmty.set(false)
                        }
                        else{
                            isEmty.set(true)
                        }
                    } else {
                        Utils.log(TAG, "failed: ${response.errorBody()}")
                    }
                }
            } catch (e: Exception) {
                Utils.log(TAG, "erro: ${e.message}")
            }
        }
    }
}