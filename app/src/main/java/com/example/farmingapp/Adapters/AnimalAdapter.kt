import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.farmingapp.ApiData.Response
import com.example.farmingapp.ApiData.SalesData
import com.example.farmingapp.R
import com.example.farmingapp.databinding.DpAnimalListBinding
import com.example.farmingapp.databinding.FragmentAnimalListBinding
import com.example.farmingapp.databinding.HomeDesignBinding

class AnimalAdapter(private var animalResponse: List<Response>) :
    RecyclerView.Adapter<AnimalAdapter.AnimalAdapterViewHolder>() {

    private var animlal = ArrayList<Response>()
    var onItemClick : ((Response) -> Unit)? =  null

    fun  setAnimal(animlal: List<Response>){
        this.animlal = animlal as ArrayList<Response>
        notifyDataSetChanged()
    }

    class AnimalAdapterViewHolder( val binding:HomeDesignBinding ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalAdapterViewHolder {
        return AnimalAdapterViewHolder(HomeDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return animlal.size
    }

    override fun onBindViewHolder(holder: AnimalAdapterViewHolder, position: Int) {
        Glide.with(holder.itemView).load(animlal[position].image)
            .into(holder.binding.aImg)

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(animlal[position])
        }
    }}
