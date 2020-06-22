package pe.isil.esports.presentation.champion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.isil.esports.R
import pe.isil.esports.databinding.FragmentChampionBinding
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChampionBinding.inflate(inflater, container, false)

        binding.back.setOnClickListener {
            activity?.onBackPressed()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        with(viewModel) {
            observe(getChampion(champion)) {
                if (it.data != null) {
                    with(binding) {
                        championBackdrop.loading(it.data.backdrop_path)
                        championRol.icon(getChampionIcon(it.data.rol))
                        championName.text = it.data.name
                        championTitle.text = it.data.title
                        championPrimary.text = it.data.rol
                        championAttributes.text = getChampionAttributes(it.data.attributes)
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
                        championManaIcon.setBackgroundResource(getChampionResourceColor(it.data.attributes))
                        containerMana.setBackgroundResource(getChampionResourceColor(it.data.attributes))
                        championMana.text = it.data.mana
                    }
                } else {
                    activity?.toast("${it.error}")
                }
            }
        }
    }

    private fun getChampionIcon(rol: String?): Int {
        return when (rol?.toLowerCase()) {
            "juggernaut" -> R.drawable.ic_fighters
            "burst" -> R.drawable.ic_mages
            "assassin" -> R.drawable.ic_assasins
            "vanguard" -> R.drawable.ic_tanks
            "battlemage" -> R.drawable.ic_mages
            "marksman" -> R.drawable.ic_marksmen
            "specialist" -> R.drawable.ic_mages
            "catcher" -> R.drawable.ic_supports
            "warden" -> R.drawable.ic_tanks
            "diver" -> R.drawable.ic_fighters
            "skirmisher" -> R.drawable.ic_assasins
            "enchanter" -> R.drawable.ic_supports
            "artillery" -> R.drawable.ic_mages
            else -> R.drawable.ic_empty
        }
    }

    private fun getChampionAttributes(attributes: String?): String {
        return attributes!!.split(" - ").joinToString("  ")
    }

    private fun getChampionResourceRegenerationIcon(resource: String?): Int {
        return when (resource!!.split(" - ")[0].toLowerCase()) {
            "mana" -> R.drawable.ic_mana_regeneration
            "energy" -> R.drawable.ic_energy_regeneration
            "ferocity" -> R.drawable.ic_fury
            "fury" -> R.drawable.ic_fury
            "bloodthirst" -> R.drawable.ic_fury
            "courage" -> R.drawable.ic_fury
            "grit" -> R.drawable.ic_fury
            "rage" -> R.drawable.ic_fury
            "heat" -> R.drawable.ic_heat
            "health costs" -> R.drawable.ic_health_cost
            "flow" -> R.drawable.ic_flow
            "shield" -> R.drawable.ic_shield
            "manaless" -> R.drawable.ic_empty
            else -> R.drawable.ic_empty
        }
    }

    private fun getChampionResourceIcon(resource: String?): Int {
        return when (resource!!.split(" - ")[0].toLowerCase()) {
            "mana" -> R.drawable.ic_mana
            "energy" -> R.drawable.ic_energy
            else -> R.drawable.ic_empty
        }
    }

    private fun getChampionResourceColor(resource: String?): Int {
        return when (resource!!.split(" - ")[0].toLowerCase()) {
            "mana" -> R.color.manaColor
            "energy" -> R.color.energyColor
            else -> R.color.cardColor
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}