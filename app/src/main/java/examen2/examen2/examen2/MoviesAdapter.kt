package examen2.examen2.examen2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import examen2.examen2.examen2.databinding.MoviesBinding
import examen2.examen2.examen2.db.entity.OfflineData


class MoviesAdapter(var items : ArrayList<OfflineData>, var clickListner: OnMVItemClickListner) : RecyclerView.Adapter<MVViewHolder>(){

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MVViewHolder {
        lateinit var mvViewHolder : MVViewHolder
        mvViewHolder = MVViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movies,parent,false ))
        return mvViewHolder
    }

    override fun onBindViewHolder(holder: MVViewHolder, position: Int) {

        holder.initialize(items.get(position),clickListner)

    }
}

class MVViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

    private val binding = MoviesBinding.bind(itemView)


    fun initialize(item: OfflineData, action:OnMVItemClickListner){
        binding.originalTitle.text = "Original title: " + item.original_title





        itemView.setOnClickListener{
            action.onItemClick(item,adapterPosition)
        }

    }

}

interface OnMVItemClickListner{
    fun onItemClick(item: OfflineData, position: Int)
}