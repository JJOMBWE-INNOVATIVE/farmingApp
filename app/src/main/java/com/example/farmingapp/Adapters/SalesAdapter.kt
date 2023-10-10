import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.farmingapp.ApiData.ResponseX
import com.example.farmingapp.ApiData.SalesData
import com.example.farmingapp.databinding.DpRegesteredWorkersBinding
import com.example.farmingapp.databinding.NewSalesBinding

class SalesAdapter(private var salesData: List<SalesData>) :
    RecyclerView.Adapter<SalesAdapter.SalesAdapterViewHolder>() {

    private var sales = ArrayList<SalesData>()
    var onItemClick : ((SalesData) -> Unit)? =  null

    fun  setSales(sales : ArrayList<SalesData>){
        this.sales  = sales  as ArrayList<SalesData>
        notifyDataSetChanged()
    }

    class SalesAdapterViewHolder( val binding:NewSalesBinding ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):SalesAdapterViewHolder {
        return SalesAdapterViewHolder(NewSalesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return sales .size
    }

    override fun onBindViewHolder(holder: SalesAdapterViewHolder, position: Int) {
        val salesItem = sales [position]

        // Check if workersItem is not null and its properties are not null before accessing them
        if (salesItem != null) {
            holder.binding.sId.text = (salesItem.id).toString()
            holder.binding.sAId.text =(salesItem.animal_id)
            holder.binding.sP.text = (salesItem.price)
            holder.binding.sQ.text = (salesItem.quantity)
            holder.binding.sDate.text = (salesItem.selling_date)
            holder.binding.sPerson.text = (salesItem.sold_by)
            holder.binding.pSoldTo.text = (salesItem.sold_to)

            holder.itemView.setOnClickListener {
                onItemClick?.invoke(salesItem)
            }
        }
    }}


