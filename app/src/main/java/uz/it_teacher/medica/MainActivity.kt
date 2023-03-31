package uz.it_teacher.medica

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import uz.it_teacher.medica.adapter.DoctorAdapter
import uz.it_teacher.medica.databinding.ActivityMainBinding
import uz.it_teacher.medica.model.Doctor
import java.util.Collections


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val background = ColorDrawable(Color.RED)

        var doctors = mutableListOf<Doctor>()
        for (i in 1..50) {
            doctors.add(
                Doctor(
                    "Aliyev Ali",
                    R.drawable.img1,
                    "Jarroh",
                    "EraMed",
                    "4.5"
                )
            )
            doctors.add(
                Doctor(
                    "Usmanova Salima",
                    R.drawable.img2,
                    "Tish shifokori",
                    "Doctor D",
                    "4.2"
                )
            )
            doctors.add(
                Doctor(
                    "Qosimova Hadicha",
                    R.drawable.img3,
                    "Nevropatolog",
                    "Jo'rabekov",
                    "4.8"
                )
            )

        }

        val adapter = DoctorAdapter(this,doctors)

        val touch = object : ItemTouchHelper.SimpleCallback(
            ANIMATION_TYPE_DRAG or UP or DOWN or START,
            0
        ){

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                Collections.swap(doctors,viewHolder.adapterPosition, target.adapterPosition)
                adapter.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.onItemDismiss(viewHolder.adapterPosition)
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
                val itemView = viewHolder.itemView
                background.setBounds(
                    itemView.right + dX.toInt(),
                    itemView.top,
                    itemView.right,
                    itemView.bottom
                )
                background.draw(c)

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }

        }
        val itemTouchHelper = ItemTouchHelper(touch)
        itemTouchHelper.attachToRecyclerView(binding.rv)

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rv)

        binding.rv.adapter = adapter
    }
}