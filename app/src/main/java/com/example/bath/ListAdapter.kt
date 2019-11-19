package com.example.bath

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cell_layout.view.*

class ListAdapter(var list:List<People>, val context: Context):RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private val rItemDragHelperCallback = RecyclerViewItemTouchHelper()
    private val rItemTouchHelper = ItemTouchHelper(rItemDragHelperCallback)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        rItemTouchHelper.attachToRecyclerView(recyclerView)
    }


    private var deleteListener1: deleteListener? = null

    interface deleteListener{
        fun delete(id:Int, name:String)
        fun remove(id:Int, name:String)
    }

    fun setdeleteListener(deleteListener:deleteListener){
        this.deleteListener1 = deleteListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.cell_layout, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindViewHolder(list[position])
    }

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        fun bindViewHolder(person: People){

            val name = itemView.textName
            val drink = itemView.textDrink
            name.text = person.name
            drink.text = person.drink
            itemView.setOnClickListener {
                deleteListener1?.delete(person.id, person.name)
            }
        }
    }

    inner class RecyclerViewItemTouchHelper : ItemTouchHelper.Callback(){

        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            var swipe = 0
            var move = 0
            swipe = ItemTouchHelper.START or ItemTouchHelper.END
            move = ItemTouchHelper.UP  or ItemTouchHelper.DOWN

            return makeMovementFlags(move, swipe)
        }

        override fun isLongPressDragEnabled(): Boolean {
            return true
        }

        override fun isItemViewSwipeEnabled(): Boolean {
            return true
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            deleteListener1?.remove(list[position].id, list[position].name)
        }
    }
}
