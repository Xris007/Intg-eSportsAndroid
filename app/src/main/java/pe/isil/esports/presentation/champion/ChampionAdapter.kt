package pe.isil.esports.presentation.champion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.isil.esports.databinding.ViewChampionBinding
import pe.isil.esports.domain.model.Champion
import pe.isil.esports.utils.loading

class ChampionAdapter(
    private var list: List<Champion> = emptyList(),
    private val listener: (Long) -> Unit
) :
    RecyclerView.Adapter<ChampionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewChampionBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    override fun getItemCount() = list.size

    fun update(list: List<Champion>) {
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ViewChampionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(champion: Champion, listener: (Long) -> Unit) = with(binding) {
            championPoster.loading(champion.poster_path, progress)
            championName.text = champion.name

            root.setOnClickListener { listener(champion.id!!) }
        }
    }
}