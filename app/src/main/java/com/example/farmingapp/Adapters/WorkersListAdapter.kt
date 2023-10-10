import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.farmingapp.ApiData.PurchaseData
import com.example.farmingapp.ApiData.WorkersData
import com.example.farmingapp.R

private fun TextView.setText(age: Any?) {

}

private fun ImageView.setText(id: String) {

}

class WorkersListAdapter(private var workersData : List<WorkersData>) :
    RecyclerView.Adapter<WorkersListAdapter.WorkersListAdapterViewHolder>() {

    class WorkersListAdapterViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView) {

        val wksID = itemsView.findViewById<TextView>(R.id.wkId)
        val wksAge = itemsView.findViewById<TextView>(R.id.wkAge)
        val wkFirstName = itemsView.findViewById<TextView>(R.id.wkFN)
        val wkLastName = itemsView.findViewById<TextView>(R.id.wkLN)
        val wksTitle = itemsView.findViewById<TextView>(R.id.wkTitle)
        val wksPassword = itemsView.findViewById<TextView>(R.id.wkPassword)
        val wksPhoneNumber = itemsView.findViewById<TextView>(R.id.wkPhone)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkersListAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dp_regestered_workers, parent, false)
        return WorkersListAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return workersData.size
    }

    override fun onBindViewHolder(holder: WorkersListAdapterViewHolder, position: Int) {
        val workersItem = workersData[position]
        holder.wksID.text = workersItem.id
        holder.wksAge.text = workersItem.age.toString()
        holder.wkFirstName.text = workersItem.f_name
        holder.wkLastName.text = workersItem.l_name
        holder.wksTitle.text = workersItem.title
        holder.wksPassword.text = workersItem.password
        workersItem.phone?.let { holder.wksPhoneNumber.text = it.toString() }

    }

    // Update the adapter's data with a new list of salesData
    fun getWorkersData(newWorkersData: List<WorkersData>) {
        workersData = newWorkersData
        notifyDataSetChanged()
    }
}
