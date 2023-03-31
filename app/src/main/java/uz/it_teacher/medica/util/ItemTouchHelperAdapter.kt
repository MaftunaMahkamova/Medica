package uz.it_teacher.medica.util

interface ItemTouchHelperAdapter {

    fun onItemMove(fromPosition:Int, toPosition:Int)

    fun onItemDismiss(position:Int)
}