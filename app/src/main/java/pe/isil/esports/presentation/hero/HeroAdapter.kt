package pe.isil.esports.presentation.hero

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.isil.esports.databinding.ViewHeroBinding
import pe.isil.esports.domain.model.Hero
import pe.isil.esports.utils.loading

class HeroAdapter(
    private var list: List<Hero> = emptyList(),
    private val listener: (Long) -> Unit
) :
    RecyclerView.Adapter<HeroAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHeroBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    override fun getItemCount() = list.size

    fun update(list: List<Hero>) {
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ViewHeroBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: Hero, listener: (Long) -> Unit) = with(binding) {
            heroPoster.loading(hero.poster_path, progress)
            heroName.text = hero.name
            root.setOnClickListener { listener(hero.id!!) }
        }
    }
}