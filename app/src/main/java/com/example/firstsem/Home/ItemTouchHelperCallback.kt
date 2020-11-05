package com.example.firstsem.Home

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(listener: ItemTouchHelperListener) :
    ItemTouchHelper.Callback() {
    private val listener: ItemTouchHelperListener = listener
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val drag_flags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipe_flags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(drag_flags, swipe_flags)
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return listener.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onItemSwipe(viewHolder.adapterPosition)
    }
}
