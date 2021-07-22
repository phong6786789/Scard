package com.subi.scard.view.fragment.friends

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.*
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.subi.scard.BR
import com.subi.scard.R
import com.subi.scard.base.fragment.BaseBindingFragment
import com.subi.scard.databinding.FragmentFriendsBinding
import com.subi.scard.model.Item
import com.subi.scard.utils.*
import com.subi.scard.view.activity.loginGG.LoginActivity
import com.subi.scard.view.adapter.FriendsAdapter
import com.subi.scard.view.adapter.HealthAdapter

@Suppress("DEPRECATION")
class FriendsFragment : BaseBindingFragment<FragmentFriendsBinding, FriendsViewmodel>() {
    override val bindingVariable: Int
        get() = BR.viewmodel
    override val viewModel: FriendsViewmodel
        get() = ViewModelProviders.of(this).get(FriendsViewmodel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_friends
    var adapterX: FriendsAdapter? = null

    override fun initVariable(savedInstanceState: Bundle?, view: View) {

        //Load list
        viewModel.load(context?.let { Utils.getIdUser(it) } ?:"")
        viewDataBinding?.rcvHome?.apply {
            adapterX = FriendsAdapter(viewModel.list){clickItem(it)}
            adapter = adapterX
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            hasFixedSize()
            val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
            itemTouchHelper.attachToRecyclerView(this)
        }
    }

    fun clickItem(item: Item){
        val bundle = bundleOf("id" to item.id?.replace(Constants.FRIEND_TYPE.FRIEND,""))
        findNavController().navigate(R.id.action_friendsFragment_to_showCardFragment, bundle)
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
        viewModel.context = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarTitleAndBack(Constants.TITLE.FRIENDS)
    }

    val simpleItemTouchCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
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
                val item = viewModel.list[position]
                //Xoá item
                if (direction == ItemTouchHelper.LEFT) {
                    adapterX?.notifyItemChanged(position)
                    var dialogx: Dialog? = null
                    dialogx = requireContext().let {
                        ShowDialog.Builder(it).title("XOÁ DỮ LIỆU")
                            .message("Bạn có chắc chắn muốn xoá ${item.title}?")
                            .setLeftButton("XOÁ", object : LeftInterface {
                                override fun onClick() {
//                                    viewModel.deleteItem(item.id!!)
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
                    adapterX?.notifyItemChanged(position)

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
                            BitmapFactory.decodeResource(context?.resources, R.drawable.trash)
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
                        val icon = BitmapFactory.decodeResource(context?.resources, R.drawable.edit)
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
}