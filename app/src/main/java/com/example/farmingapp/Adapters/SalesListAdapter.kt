import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.farmingapp.ApiData.SalesData
import com.example.farmingapp.R

class SalesListAdapter(private var salesData: List<SalesData>) :
    RecyclerView.Adapter<SalesListAdapter.SalesAdapterViewHolder>() {

    class SalesAdapterViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView) {
        val salesId = itemsView.findViewById<TextView>(R.id.sId)
        val saleAnimalId = itemsView.findViewById<TextView>(R.id.sAId)
        val salesPrice = itemsView.findViewById<TextView>(R.id.sP)
        val salesQuantity = itemsView.findViewById<TextView>(R.id.sQ)
        val salesselling_date = itemsView.findViewById<TextView>(R.id.sDate)
        val salesSold_By = itemsView.findViewById<TextView>(R.id.sPerson)
        val salesSold_To = itemsView.findViewById<TextView>(R.id.pSoldTo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalesAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.new_sales, parent, false)
        return SalesAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return salesData.size
    }

    override fun onBindViewHolder(holder: SalesAdapterViewHolder, position: Int) {
        val salesItem = salesData[position]
        holder.salesId.setText(salesItem.id)
        holder.saleAnimalId.setText(salesItem.animal_id)
        holder.salesPrice.setText(salesItem.price)
        holder.salesQuantity.setText(salesItem.quantity)
        holder.salesselling_date.setText(salesItem.selling_date)
        holder.salesSold_By.setText(salesItem.sold_by)
        holder.salesSold_To.setText(salesItem.sold_to)
    }

    // Update the adapter's data with a new list of salesData
    fun setSalesData(newSalesData: List<SalesData>) {
        salesData = newSalesData
        notifyDataSetChanged()
    }
}
