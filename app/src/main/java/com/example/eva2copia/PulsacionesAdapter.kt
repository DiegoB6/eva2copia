package com.example.eva2copia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class PulsacionesAdapter(private val itemClickListener: OnItemClickListener): ListAdapter<Pulsaciones, PulsacionesAdapter.ViewHolder> (DiffCallback()) {

    private class DiffCallback: DiffUtil.ItemCallback<Pulsaciones>(){
        override fun areItemsTheSame(oldItem: Pulsaciones, newItem: Pulsaciones): Boolean {
            return oldItem.pulsaciones == newItem.pulsaciones
        }

        override fun areContentsTheSame(oldItem: Pulsaciones, newItem: Pulsaciones): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {

        private val tvPulsaciones = itemView.findViewById(R.id.tvPulsaciones) as TextView
        private val btnEditar = itemView.findViewById(R.id.btnEditar) as Button
        private val btnEliminar = itemView.findViewById(R.id.btnEliminar) as Button

        fun bind(item: Pulsaciones, clickListener: OnItemClickListener) {
            tvPulsaciones.text = item.pulsaciones.toString()

            btnEditar.setOnClickListener{clickListener.onItemEditar(adapterPosition, item)}
            btnEliminar.setOnClickListener{clickListener.onItemBorrar(adapterPosition)}
        }
    }

    interface OnItemClickListener{
        fun onItemEditar(position: Int, item: Pulsaciones)
        fun onItemBorrar(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.rv_pulsaciones, parent, false)
        return ViewHolder(vista)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }
}