import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.farmingapp.ApiData.PurchaseData
import com.example.farmingapp.R

class PurchaseListAdapter(private var purchaseData: List<PurchaseData>) :
    RecyclerView.Adapter<PurchaseListAdapter.PurchaseListAdapterViewHolder>() {

    class PurchaseListAdapterViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView) {
        val purchaseId = itemsView.findViewById<TextView>(R.id.lId)
        val productPrice = itemsView.findViewById<TextView>(R.id.lpP)
        val productName = itemsView.findViewById<TextView>(R.id.lpN)
        val productDescription = itemsView.findViewById<TextView>(R.id.lpDescr)
        val purchaseDate = itemsView.findViewById<TextView>(R.id.lpDate)
        val purchaseQuantity = itemsView.findViewById<TextView>(R.id.lpQnty)
        val purchasedFrom = itemsView.findViewById<TextView>(R.id.lpFrom)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseListAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.purchased_items, parent, false)
        return PurchaseListAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return purchaseData.size
    }

    override fun onBindViewHolder(holder: PurchaseListAdapterViewHolder, position: Int) {
        val purchasedItem = purchaseData[position]
        holder.purchaseId.setText(purchasedItem.id)
        holder.productPrice.setText(purchasedItem.price)
        holder.productName.setText(purchasedItem.price)
        holder.productDescription.setText(purchasedItem.description)
        holder.purchaseQuantity.setText(purchasedItem.quantity)
        holder.purchaseDate.setText(purchasedItem.purchased_on)
        holder.purchasedFrom.setText(purchasedItem.purchased_from)
    }

    // Update the adapter's data with a new list of salesData
    fun setPurchaseData(newPurchaseData: List<PurchaseData>) {
        purchaseData = newPurchaseData
        notifyDataSetChanged()
    }
}
