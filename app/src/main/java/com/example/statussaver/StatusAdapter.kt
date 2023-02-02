package com.example.statussaver

import android.content.Context
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class StatusAdapter(private val context: Context,
                    private var modelClass:ArrayList<ModelClass>,private val clickListener:(ModelClass)->Unit):
    RecyclerView.Adapter<StatusAdapter.StatusViewHolder>() {
class StatusViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
{
   val ivStatus:ImageView=itemView.findViewById(R.id.iv_status)
    val ivVideoIcon:ImageView=itemView.findViewById(R.id.iv_video_icon)
    val cvVideoCard: CardView =itemView.findViewById(R.id.cv_video_card)
}

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): StatusViewHolder {
        return StatusViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.status_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: StatusViewHolder , position: Int) {
        if(modelClass[position].fileUri.endsWith(".mp4")){
            holder.cvVideoCard.visibility=View.VISIBLE
            holder.ivVideoIcon.visibility=View.VISIBLE
        }else{
            holder.cvVideoCard.visibility=View.GONE
            holder.ivVideoIcon.visibility=View.GONE

        }
       Glide.with(context).load(Uri.parse(modelClass[position].fileUri)).into(holder.ivStatus)
holder.ivStatus.setOnClickListener{
    clickListener(modelClass[position])
}
    }

    override fun getItemCount(): Int {
     return modelClass.size
    }
}