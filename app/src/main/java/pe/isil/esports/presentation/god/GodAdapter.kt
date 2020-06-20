package pe.isil.esports.presentation.god

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.isil.esports.databinding.ViewGodBinding
import pe.isil.esports.domain.model.God
import pe.isil.esports.utils.posterLoading

class GodAdapter(
    private var list: List<God> = emptyList(),
    private val listener: (God) -> Unit
) :
    RecyclerView.Adapter<GodAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewGodBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    override fun getItemCount() = list.size

    fun update(list: List<God>) {
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ViewGodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(god: God, listener: (God) -> Unit) = with(binding) {
            godPoster.posterLoading(god.poster_path)

            root.setOnClickListener { listener(god) }
        }
    }
}