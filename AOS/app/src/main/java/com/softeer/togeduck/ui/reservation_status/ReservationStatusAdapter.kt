package com.softeer.togeduck.ui.reservation_status

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softeer.togeduck.databinding.RvItemReservationListBinding
import com.softeer.togeduck.model.ReservationStatusModel


class ReservationStatusAdapter(private val items: List<ReservationStatusModel>) :
    RecyclerView.Adapter<ViewHolder>() {

    private lateinit var binding: RvItemReservationListBinding

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = RvItemReservationListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[holder.adapterPosition])
    }
}

class ViewHolder(private val binding: RvItemReservationListBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ReservationStatusModel) {
        binding.reserveStatus = item

        itemView.setOnClickListener {
        }
    }

}
