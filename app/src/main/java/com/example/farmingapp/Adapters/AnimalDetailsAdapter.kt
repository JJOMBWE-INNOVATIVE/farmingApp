import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.farmingapp.ApiData.Response
import com.example.farmingapp.databinding.DpAnimalDetailsBinding

class AnimalDetailsAdapter : RecyclerView.Adapter<AnimalDetailsAdapter.AnimalDetailsAdapterViewHolder>() {

    private var animalsDetails = ArrayList<Response>()
    var onItemClick: ((Response) -> Unit)? = null

    fun setAnimalsDetails(animalsDetails: List<Response>) {
        this.animalsDetails = ArrayList(animalsDetails)
        notifyDataSetChanged()
    }

    class AnimalDetailsAdapterViewHolder(val binding: DpAnimalDetailsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalDetailsAdapterViewHolder {
        return AnimalDetailsAdapterViewHolder(
            DpAnimalDetailsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return animalsDetails.size
    }

    override fun onBindViewHolder(holder: AnimalDetailsAdapterViewHolder, position: Int) {
        Glide.with(holder.itemView).load(animalsDetails[position].image)
            .into(holder.binding.animImageDetails)
        holder.binding.AN.text = animalsDetails[position].name
        holder.binding.AID.text = animalsDetails[position].id
        holder.binding.AB.text = animalsDetails[position].breed
        holder.binding.AG.text = animalsDetails[position].gender
        holder.binding.AV.text = animalsDetails[position].available
        holder.binding.AC.text = animalsDetails[position].color
        holder.binding.AW.text = animalsDetails[position].weight
        holder.binding.T.text = animalsDetails[position].type
        holder.binding.AA.text = animalsDetails[position].age
        holder.binding.TN.text = animalsDetails[position].tag_number

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(animalsDetails[position])
        }
    }
}
