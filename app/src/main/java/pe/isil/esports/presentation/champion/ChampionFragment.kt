package pe.isil.esports.presentation.champion

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.isil.esports.R
import pe.isil.esports.databinding.FragmentChampionBinding
import pe.isil.esports.presentation.login.LoginViewModel
import pe.isil.esports.utils.icon
import pe.isil.esports.utils.loading
import pe.isil.esports.utils.observe
import pe.isil.esports.utils.toast

@ExperimentalCoroutinesApi
class ChampionFragment : Fragment() {

    private var _binding: FragmentChampionBinding? = null
    private val binding
        get() = _binding!!

    private val args: ChampionFragmentArgs by navArgs()
    private val champion: Long by lazy { args.id }

    private val viewModel: ChampionViewModel by viewModel()
    private val userViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChampionBinding.inflate(inflater, container, false)

        with((activity as AppCompatActivity)) {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(ChampionFragmentDirections.actionChampionFragmentToChampionsFragment())
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        with(viewModel) {
            observe(getChampion(champion)) {
                if (it.data != null) {
                    with(binding) {
                        championBackdrop.loading(it.data.backdrop_path, progress)
                        championRol.icon(getChampionIcon(it.data.rol))
                        championName.text = it.data.name
                        championTitle.text = it.data.title
                        championPrimary.text = it.data.rol
                        championAttributes.text = getChampionAttributes(it.data.attributes)
                        championDescription.text = it.data.description
                        championAttackDamage.text = it.data.attack_damage
                        championAttackSpeed.text = it.data.attack_speed
                        championAttackRange.text = it.data.attack_range
                        championMoveSpeed.text = it.data.move_speed
                        championArmor.text = it.data.armor
                        championMagicResistance.text = it.data.magic_resistance
                        championHealthRegeneration.text = it.data.hp_regeneration
                        championManaRegenerationIcon.icon(getChampionResourceRegenerationIcon(it.data.attributes))
                        championManaRegeneration.text = it.data.mp_regeneration
                        championHealth.text = it.data.health
                        championManaIcon.icon(getChampionResourceIcon(it.data.attributes))
                        val tint = ContextCompat.getColor(
                            requireContext(),
                            getChampionResourceColor(it.data.attributes)
                        )
                        ImageViewCompat.setImageTintList(
                            championManaIcon,
                            ColorStateList.valueOf(tint)
                        )
                        containerMana.setBackgroundResource(getChampionResourceColor(it.data.attributes))
                        championMana.text = it.data.mana
                    }
                } else {
                    activity?.toast("${it.error}")
                }
            }
        }

        with(userViewModel) {
            observe(active()) {
                if (it.data != null) {
                    setHasOptionsMenu(true)
                }
            }
        }
    }

    private fun getChampionIcon(rol: String?): Int {
        return when (rol) {
            "Juggernaut" -> R.drawable.ic_fighters
            "Burst" -> R.drawable.ic_mages
            "Assassin" -> R.drawable.ic_assasins
            "Vanguard" -> R.drawable.ic_tanks
            "Battlemage" -> R.drawable.ic_mages
            "Marksman" -> R.drawable.ic_marksmen
            "Specialist" -> R.drawable.ic_mages
            "Catcher" -> R.drawable.ic_supports
            "Warden" -> R.drawable.ic_tanks
            "Diver" -> R.drawable.ic_fighters
            "Skirmisher" -> R.drawable.ic_assasins
            "Enchanter" -> R.drawable.ic_supports
            "Artillery" -> R.drawable.ic_mages
            else -> R.drawable.ic_empty
        }
    }

    private fun getChampionAttributes(attributes: String?): String {
        return attributes!!.split(" - ").joinToString("  ")
    }

    private fun getChampionResourceRegenerationIcon(resource: String?): Int {
        return when (resource!!.split(" - ")[0]) {
            "Mana" -> R.drawable.ic_mana_regeneration
            "Energy" -> R.drawable.ic_energy_regeneration
            "Ferocity" -> R.drawable.ic_fury
            "Fury" -> R.drawable.ic_fury
            "Bloodthirst" -> R.drawable.ic_fury
            "Courage" -> R.drawable.ic_fury
            "Grit" -> R.drawable.ic_fury
            "Rage" -> R.drawable.ic_fury
            "Heat" -> R.drawable.ic_heat
            "Flow" -> R.drawable.ic_flow
            "Manaless" -> R.drawable.ic_empty
            else -> R.drawable.ic_empty
        }
    }

    private fun getChampionResourceIcon(resource: String?): Int {
        return when (resource!!.split(" - ")[0]) {
            "Mana" -> R.drawable.ic_mana
            "Energy" -> R.drawable.ic_energy
            else -> R.drawable.ic_empty
        }
    }

    private fun getChampionResourceColor(resource: String?): Int {
        return when (resource!!.split(" - ")[0]) {
            "Mana" -> R.color.manaColor
            "Energy" -> R.color.energyColor
            else -> R.color.cardColor
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit -> findNavController().navigate(
                ChampionFragmentDirections.actionChampionFragmentToUpdateChampionFragment(
                    champion
                )
            )
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}