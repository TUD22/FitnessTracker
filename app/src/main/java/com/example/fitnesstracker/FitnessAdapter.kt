package com.example.fitnesstracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TreningAdapter (private val treningList: List<Trening>, private val clickListener:(Trening)-> Unit, ): RecyclerView.Adapter<TreningAdapter.TreningHolder>(){
    class TreningHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val treningDystans: TextView = itemView.findViewById(R.id.dystans)
        val treningCzas: TextView = itemView.findViewById(R.id.czas)
        val treningRodzaj: ImageView = itemView.findViewById(R.id.rodzaj)
        val treningKalorie: TextView = itemView.findViewById(R.id.kalorie)
        val treningIntensywnosc: TextView= itemView.findViewById(R.id.score)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreningHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.trening, parent, false)
        return TreningHolder(itemView)
    }


    override fun onBindViewHolder(holder: TreningHolder, position: Int) {
        val thisTrening = treningList[position]
        holder.treningDystans.text = ("${thisTrening.dystans}km")
        holder.treningIntensywnosc.text = thisTrening.intensywnosc.toString()
        holder.treningCzas.text = ("${thisTrening.czas}minut")
        holder.treningKalorie.text = ("${thisTrening.kalorie}kalorii")


       if(thisTrening.rodzaj=="Siłowy"){
            holder.treningRodzaj.setImageResource(R.drawable.dumbbell)
       }else if(thisTrening.rodzaj=="Bieg"){

            holder.treningRodzaj.setImageResource(R.drawable.running)
       }else if(thisTrening.rodzaj=="Spacer"){

            holder.treningRodzaj.setImageResource(R.drawable.walking)
       }

        holder.itemView.setOnClickListener {
            clickListener(thisTrening)
        }

    }

    override fun getItemCount(): Int = treningList.size;
}