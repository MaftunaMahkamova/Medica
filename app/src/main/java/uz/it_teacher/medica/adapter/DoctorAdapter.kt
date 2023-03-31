package uz.it_teacher.medica.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import uz.it_teacher.medica.R
import uz.it_teacher.medica.databinding.ItemDoctorBinding
import uz.it_teacher.medica.model.Doctor
import uz.it_teacher.medica.util.ItemTouchHelperAdapter
import java.util.Collections

class DoctorAdapter(var context: Context, var list:MutableList<Doctor>)
    :RecyclerView.Adapter<DoctorAdapter.DoctorHolder>(), ItemTouchHelperAdapter {


    class DoctorHolder(itemView: ItemDoctorBinding) :RecyclerView.ViewHolder(itemView.root){
        var name = itemView.doctorName
        var main = itemView.doctorMain
        var img = itemView.doctorImg
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorHolder {
        return DoctorHolder(ItemDoctorBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: DoctorHolder, position: Int) {
        var doctor = list[position]
        holder.img.setImageResource(doctor.img)
        holder.name.text = doctor.name
        var anim = AnimationUtils.loadAnimation(context, R.anim.item_anim)
        holder.main.startAnimation(anim)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition<toPosition){
            for (i in fromPosition until toPosition){
                Collections.swap(list, i , i+1)
            }
        }else{
            for (i in fromPosition downTo  toPosition+1){
                Collections.swap(list, i , i-1)
            }
        }
        notifyItemMoved(fromPosition,toPosition)
    }

    override fun onItemDismiss(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }
}