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

class AnimalListAdapter(private var animalResponse: List<Response>) :
    RecyclerView.Adapter<AnimalListAdapter.AnimalListAdapterViewHolder>() {

    private var animlalsList = ArrayList<Response>()
    var onItemClick : ((Response) -> Unit)? =  null

    fun  setAnimalList(animlalsList: List<Response>){
        this.animlalsList = animlalsList as ArrayList<Response>
        notifyDataSetChanged()
    }

    class AnimalListAdapterViewHolder( val binding:DpAnimalListBinding ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalListAdapterViewHolder {
        return AnimalListAdapterViewHolder(DpAnimalListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return animlalsList.size
    }

    override fun onBindViewHolder(holder: AnimalListAdapterViewHolder, position: Int) {
        Glide.with(holder.itemView).load(animlalsList[position].image)
            .into(holder.binding.imgAnimal)
        holder.binding.animalNAme.text = animlalsList[position].name


        holder.itemView.setOnClickListener {
            onItemClick?.invoke(animlalsList[position])
        }
    }}
