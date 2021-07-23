package com.subi.scard.utils

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.subi.scard.R
import com.subi.scard.databinding.LayoutInsertBankBinding
import com.subi.scard.databinding.LayoutInsertInfoBinding
import com.subi.scard.databinding.LayoutInsertItemBinding
import com.subi.scard.model.Item
import io.ghyeok.stickyswitch.widget.StickySwitch

class ChauManager {

    companion object {
        @SuppressLint("SetTextI18n")
        fun setupViewInsert(
            context: Context,
            list: Array<String>,
            type: String,
            insert: (Item) -> Unit
        ) {
            val dialog = Dialog(context)
            val binding = LayoutInsertItemBinding.inflate(LayoutInflater.from(context))
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
            when (type) {
                Constants.ITEM_TYPE.SOCIAL -> {
                    binding.apply {
                        tvTitle.text = "Thêm MXH"
                        tvLayoutInsertItemDes.text = "Link URL"
                        tvLayoutInsertItemTitle.text = "Tên mạng xã hội"
                        edtLink.hint = "Vui lòng nhập link URL"
                    }
                }
                Constants.ITEM_TYPE.INFO -> {
                    binding.apply {
                        tvTitle.text = "THÊM THÔNG TIN"
                        tvLayoutInsertItemTitle.text = "Loại INFO"
                        tvLayoutInsertItemDes.text = "Thông tin cá nhân"
                        edtLink.hint = "Vui lòng nhập thông tin"
                    }
                }
                Constants.ITEM_TYPE.HEALTH -> {
                    binding.apply {
                        tvTitle.text = "THÊM BỆNH VIỆN"
                        tvLayoutInsertItemTitle.text = "BỆNH VIỆN"
                        tvLayoutInsertItemDes.text = "Thông tin bệnh viện"
                        edtLink.hint = "Vui lòng nhập thông tin"
                    }
                }
                Constants.ITEM_TYPE.FRIEND -> {
                    binding.apply {
                        tvTitle.text = "THÊM BẠN BÈ"
                        tvLayoutInsertItemTitle.text = "Tên bạn"
                        tvLayoutInsertItemDes.text = "Thông tin bạn"
                        edtLink.hint = "Vui lòng nhập thông tin"
                    }
                }
                Constants.ITEM_TYPE.BANK -> {
                    binding.apply {
                        tvTitle.text = "THÊM NGÂN HÀNG"
                        tvLayoutInsertItemTitle.text = "Tên ngân hàng"
                        tvLayoutInsertItemDes.text = "Thông tin ngân hàng"
                        edtLink.hint = "Vui lòng nhập thông tin"
                    }
                }
            }

            val customDropDownAdapter = CustomDropDownAdapter(context, list)

            binding.spinnerItem.adapter = customDropDownAdapter


            binding.btnInsert.setOnClickListener {
                val link = binding.edtLink.text.toString()

                val direction = binding.stickySwitch.getDirection().name

                val status = if (direction == "LEFT") 0 else 1
                if (link.isNotEmpty()) {
                    val title = binding.spinnerItem.selectedItem.toString()
                    insert(
                        Item(
                            title + Utils.getIdUser(context),
                            title,
                            link,
                            type,
                            Utils.getIdUser(context),
                            status.toString()
                        )
                    )
                    dialog.dismiss()
                } else {
                    Utils.showMess(context, "Không được để trống!")
                }
            }
            binding.btnCancel.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }

        @SuppressLint("SetTextI18n")
        fun setupViewEdit(
            context: Context,
            list: MutableList<String>,
            type: String,
            item: Item,
            edit: (Item) -> Unit
        ) {
            val dialog = Dialog(context)
            val binding = LayoutInsertItemBinding.inflate(LayoutInflater.from(context))
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
            binding.btnInsert.text = "SỬA"
            binding.edtLink.setText(item.description)

            when (type) {
                Constants.ITEM_TYPE.SOCIAL -> {
                    binding.apply {
                        tvTitle.text = "Sửa MXH"
                        tvLayoutInsertItemDes.text = "Link URL"
                        tvLayoutInsertItemTitle.text = "Tên mạng xã hội"
                    }
                }
                Constants.ITEM_TYPE.INFO -> {
                    binding.apply {
                        tvTitle.text = "Sửa thông tin cá nhân"
                        tvLayoutInsertItemTitle.text = "Loại INFO"
                        tvLayoutInsertItemDes.text = "Thông tin cá nhân"
                    }
                }
                Constants.ITEM_TYPE.HEALTH -> {
                    binding.apply {
                        tvTitle.text = "SỬA THÔNG TIN HEALTH"
                        tvLayoutInsertItemTitle.text = "BỆNH VIỆN"
                        tvLayoutInsertItemDes.text = "Thông tin bệnh viện"
                    }
                }
                Constants.ITEM_TYPE.FRIEND -> {
                    binding.apply {
                        tvTitle.text = "SỬA THÔNG TIN FRIEND"
                        tvLayoutInsertItemTitle.text = "Tên bạn"
                        tvLayoutInsertItemDes.text = "Thông tin bạn"
                    }
                }
                Constants.ITEM_TYPE.BANK -> {
                    binding.apply {
                        tvTitle.text = "SỬA THÔNG TIN BANK"
                        tvLayoutInsertItemTitle.text = "Tên Ngân Hàng"
                        tvLayoutInsertItemDes.text = "Thông tin ngân hàng"
                    }
                }
            }

            var position = 0

            for (i in 0..list.size) {
                if (list[i].equals(item.title)) {
                    position = i
                    break
                }
            }

            //set vi tri item cho spinner
            val customDropDownAdapter = CustomDropDownAdapter(context, list.toTypedArray())
            binding.spinnerItem.adapter = customDropDownAdapter
            binding.spinnerItem.setSelection(position)

            //set public private cho status
            binding.stickySwitch.setDirection(if (item.status.equals("0")) StickySwitch.Direction.LEFT else StickySwitch.Direction.RIGHT, false)
            binding.btnInsert.setOnClickListener {
                val link = binding.edtLink.text.toString()

                val direction = binding.stickySwitch.getDirection().name

                val status = if (direction == "LEFT") 0 else 1

                if (link.isNotEmpty()) {
                    val title = binding.spinnerItem.selectedItem.toString()
                    edit(
                        Item(
                            item.id,
                            title,
                            link,
                            type,
                            Utils.getIdUser(context),
                            status.toString()
                        )
                    )
                    dialog.dismiss()
                } else {
                    Utils.showMess(context, "Không được để trống!")
                }
            }
            binding.btnCancel.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }

        fun setupItemTouchHelper(
            listItem: MutableList<Item>,
            context: Context,
            onDelete: (String) -> Unit,
            onEdit: (Item) -> Unit,
            onLoad: (Int) -> Unit,
        ): ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            val p = Paint()

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val item = listItem.get(position)
                //Xoá item
                if (direction == ItemTouchHelper.LEFT) {
                    onLoad(position)
                    var dialogx: Dialog? = null
                    dialogx = context.let {
                        ShowDialog.Builder(it).title("XOÁ DỮ LIỆU")
                            .message("Bạn có chắc chắn muốn xoá ${item.title}?")
                            .setLeftButton("XOÁ", object : LeftInterface {
                                override fun onClick() {
                                    onDelete(item.id!!)
                                    dialogx?.dismiss()
                                }
                            })
                            .setRightButton("KHÔNG", object : RightInterface {
                                override fun onClick() {
                                    dialogx?.dismiss()
                                }
                            })
                            .miniDialog()
                    }
                    dialogx?.show()
                    //Sửa item
                } else if (direction == ItemTouchHelper.RIGHT) {
                    onEdit(item)
                    onLoad(position)
                }
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    val itemView = viewHolder.itemView
                    val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                    val width = height / 3

                    if (dX < 0) {
                        p.color = Color.WHITE
                        val background = RectF(
                            itemView.right.toFloat() + dX,
                            itemView.top.toFloat(),
                            itemView.right.toFloat(),
                            itemView.bottom.toFloat()
                        )
                        c.drawRect(background, p)
                        val icon =
                            BitmapFactory.decodeResource(context.resources, R.drawable.trash)
                        val margin = (dX / 5 - width) / 2
                        val iconDest = RectF(
                            itemView.right.toFloat() + margin,
                            itemView.top.toFloat() + width,
                            itemView.right.toFloat() + (margin + width),
                            itemView.bottom.toFloat() - width
                        )
                        c.drawBitmap(icon, null, iconDest, p)
                    }
                    if (dX > 0) {
                        p.color = Color.WHITE
                        val background = RectF(
                            itemView.left.toFloat(),
                            itemView.top.toFloat(),
                            itemView.left.toFloat() + dX,
                            itemView.bottom.toFloat()
                        )
                        c.drawRect(background, p)
                        val icon = BitmapFactory.decodeResource(context.resources, R.drawable.edit)
                        val margin = (dX / 5 - width) / 2
                        val iconDest = RectF(
                            margin,
                            itemView.top.toFloat() + width,
                            margin + width,
                            itemView.bottom.toFloat() - width
                        )
                        c.drawBitmap(icon, null, iconDest, p)
                    }
                } else {
                    c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
                }
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX / 5,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }

        fun setupViewInsertNew(
            context: Context,
            list: Array<String>,
            type: String,
            insert: (Item) -> Unit
        ) {
            when (type) {
                Constants.ITEM_TYPE.INFO -> {
                    val dialog = Dialog(context)
                    val binding = LayoutInsertInfoBinding.inflate(LayoutInflater.from(context))
                    dialog.setContentView(binding.root)
                    val window = dialog.window
                    window?.setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    if (dialog.window != null) {
                        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    }

                    dialog.window?.setWindowAnimations(R.style.Animation_Design_BottomSheetDialog)

                    val customDropDownAdapter = CustomDropDownAdapter(context, list)
                    binding.spinnerItem.adapter = customDropDownAdapter

                    binding.btnInsert.setOnClickListener {
                        val maSo = binding.edtMaSo.text.toString()
                        val hoTen = binding.edtHoTen.text.toString()

                        val direction = binding.stickySwitch.getDirection().name

                        val status = if (direction == "LEFT") 0 else 1
                        if (maSo.isNotEmpty() && hoTen.isNotEmpty()) {
                            val title = binding.spinnerItem.selectedItem.toString()
                            insert(
                                Item(
                                    title + Utils.getIdUser(context),
                                    title,
                                    "$maSo@$hoTen",
                                    type,
                                    Utils.getIdUser(context),
                                    status.toString()
                                )
                            )
                            dialog.dismiss()
                        } else {
                            Utils.showMess(context, "Không được để trống!")
                        }
                    }
                    binding.toolbar.imageLeft.setOnClickListener {
                        dialog.dismiss()
                    }
                    dialog.show()
                }
                Constants.ITEM_TYPE.BANK -> {
                    val dialog = Dialog(context)
                    val binding = LayoutInsertBankBinding.inflate(LayoutInflater.from(context))
                    dialog.setContentView(binding.root)
                    val window = dialog.window
                    window?.setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    if (dialog.window != null) {
                        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    }

                    dialog.window?.setWindowAnimations(R.style.Animation_Design_BottomSheetDialog)

                    val customDropDownAdapter = CustomDropDownAdapter(context, list)
                    binding.spinnerItem.adapter = customDropDownAdapter

                    binding.btnInsert.setOnClickListener {
                        val maSo = binding.edtMaSo.text.toString()
                        val hoTen = binding.edtHoTen.text.toString()

                        val direction = binding.stickySwitch.getDirection().name

                        val status = if (direction == "LEFT") 0 else 1
                        if (maSo.isNotEmpty() && hoTen.isNotEmpty()) {
                            val title = binding.spinnerItem.selectedItem.toString()
                            insert(
                                Item(
                                    title + Utils.getIdUser(context),
                                    title,
                                    "$maSo@$hoTen",
                                    type,
                                    Utils.getIdUser(context),
                                    status.toString()
                                )
                            )
                            dialog.dismiss()
                        } else {
                            Utils.showMess(context, "Không được để trống!")
                        }
                    }
                    binding.toolbar.imageLeft.setOnClickListener {
                        dialog.dismiss()
                    }
                    dialog.show()
                }
            }
        }

        fun setupViewEditNew(
            context: Context,
            list: MutableList<String>,
            type: String,
            item: Item,
            edit: (Item) -> Unit
        ) {

            when (type) {
                Constants.ITEM_TYPE.INFO -> {
                    val dialog = Dialog(context)
                    val binding = LayoutInsertInfoBinding.inflate(LayoutInflater.from(context))
                    dialog.setContentView(binding.root)
                    val window = dialog.window
                    window?.setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    if (dialog.window != null) {
                        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    }

                    dialog.window?.setWindowAnimations(R.style.Animation_Design_BottomSheetDialog)


                    val des = item.description?.split("@")

                    binding.toolbar.textTitle.text = "SỬA THÔNG TIN"

                    binding.btnInsert.text = "SỬA"
                    binding.edtHoTen.setText(des?.get(1))
                    binding.edtMaSo.setText(des?.get(0))

                    binding.stickySwitch.setDirection(if (item.status.equals("0")) StickySwitch.Direction.LEFT else StickySwitch.Direction.RIGHT, false)

                    var position = 0

                    for (i in 0..list.size) {
                        if (list[i].equals(item.title)) {
                            position = i
                            break
                        }
                    }

                    //set vi tri item cho spinner
                    val customDropDownAdapter = CustomDropDownAdapter(context, list.toTypedArray())
                    binding.spinnerItem.adapter = customDropDownAdapter
                    binding.spinnerItem.setSelection(position)

                    binding.btnInsert.setOnClickListener {
                        val maSo = binding.edtMaSo.text.toString()
                        val hoTen = binding.edtHoTen.text.toString()

                        val direction = binding.stickySwitch.getDirection().name

                        val status = if (direction == "LEFT") 0 else 1
                        if (maSo.isNotEmpty() && hoTen.isNotEmpty()) {
                            val title = binding.spinnerItem.selectedItem.toString()
                            edit(
                                Item(
                                    item.id,
                                    title,
                                    "$maSo@$hoTen",
                                    type,
                                    Utils.getIdUser(context),
                                    status.toString()
                                )
                            )
                            dialog.dismiss()
                        } else {
                            Utils.showMess(context, "Không được để trống!")
                        }
                    }
                    binding.toolbar.imageLeft.setOnClickListener {
                        dialog.dismiss()
                    }
                    dialog.show()
                }
                Constants.ITEM_TYPE.BANK -> {
                    val dialog = Dialog(context)
                    val binding = LayoutInsertBankBinding.inflate(LayoutInflater.from(context))
                    dialog.setContentView(binding.root)
                    val window = dialog.window
                    window?.setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    if (dialog.window != null) {
                        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    }

                    dialog.window?.setWindowAnimations(R.style.Animation_Design_BottomSheetDialog)


                    val des = item.description?.split("@")

                    binding.toolbar.textTitle.text = "SỬA THÔNG TIN"

                    binding.btnInsert.text = "SỬA"
                    binding.edtHoTen.setText(des?.get(1))
                    binding.edtMaSo.setText(des?.get(0))

                    binding.stickySwitch.setDirection(if (item.status.equals("0")) StickySwitch.Direction.LEFT else StickySwitch.Direction.RIGHT, false)

                    var position = 0

                    for (i in 0..list.size) {
                        if (list[i].equals(item.title)) {
                            position = i
                            break
                        }
                    }

                    //set vi tri item cho spinner
                    val customDropDownAdapter = CustomDropDownAdapter(context, list.toTypedArray())
                    binding.spinnerItem.adapter = customDropDownAdapter
                    binding.spinnerItem.setSelection(position)

                    binding.btnInsert.setOnClickListener {
                        val maSo = binding.edtMaSo.text.toString()
                        val hoTen = binding.edtHoTen.text.toString()

                        val direction = binding.stickySwitch.getDirection().name

                        val status = if (direction == "LEFT") 0 else 1
                        if (maSo.isNotEmpty() && hoTen.isNotEmpty()) {
                            val title = binding.spinnerItem.selectedItem.toString()
                            edit(
                                Item(
                                    item.id,
                                    title,
                                    "$maSo@$hoTen",
                                    type,
                                    Utils.getIdUser(context),
                                    status.toString()
                                )
                            )
                            dialog.dismiss()
                        } else {
                            Utils.showMess(context, "Không được để trống!")
                        }
                    }
                    binding.toolbar.imageLeft.setOnClickListener {
                        dialog.dismiss()
                    }
                    dialog.show()
                }

            }
        }
    }


}