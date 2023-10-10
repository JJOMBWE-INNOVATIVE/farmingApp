import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.farmingapp.ApiData.ResponseX
import com.example.farmingapp.databinding.DpRegesteredWorkersBinding

class WorkersAdapter(private var workers: List<ResponseX>) :
    RecyclerView.Adapter<WorkersAdapter.WorkersAdapterViewHolder>() {

    private var worker = ArrayList<ResponseX>()
    var onItemClick : ((ResponseX) -> Unit)? =  null

    fun  setWorkers(worker: ArrayList<ResponseX>){
        this.worker = worker as ArrayList<ResponseX>
        notifyDataSetChanged()
    }

    class WorkersAdapterViewHolder( val binding:DpRegesteredWorkersBinding ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):WorkersAdapterViewHolder {
        return WorkersAdapterViewHolder(DpRegesteredWorkersBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return worker.size
    }

    override fun onBindViewHolder(holder: WorkersAdapterViewHolder, position: Int) {
        val workersItem = worker[position]

        // Check if workersItem is not null and its properties are not null before accessing them
        if (workersItem != null) {
            holder.binding.wkId.text = workersItem.id ?: ""
            holder.binding.wkAge.text = workersItem.age?.toString() ?: ""
            holder.binding.wkFN.text = workersItem.f_name ?: ""
            holder.binding.wkLN.text = workersItem.l_name ?: ""
            holder.binding.wkTitle.text = workersItem.title ?: ""
            holder.binding.wkPassword.text = workersItem.password ?: ""
            holder.binding.wkPhone.text = workersItem.phone ?: ""

            holder.itemView.setOnClickListener {
                onItemClick?.invoke(workersItem)
            }
        }
    }}


