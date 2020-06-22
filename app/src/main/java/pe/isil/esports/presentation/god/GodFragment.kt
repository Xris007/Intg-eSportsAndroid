package pe.isil.esports.presentation.god

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.isil.esports.R
import pe.isil.esports.databinding.FragmentGodBinding
import pe.isil.esports.utils.icon
import pe.isil.esports.utils.loading
import pe.isil.esports.utils.observe
import pe.isil.esports.utils.toast

@ExperimentalCoroutinesApi
class GodFragment : Fragment() {

    private var _binding: FragmentGodBinding? = null
    private val binding
        get() = _binding!!

    private val args: GodFragmentArgs by navArgs()
    private val god: Long by lazy { args.id }

    private val viewModel: GodViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGodBinding.inflate(inflater, container, false)

        binding.back.setOnClickListener {
            activity?.onBackPressed()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        with(viewModel) {
            observe(getGod(god)) {
                if (it.data != null) {
                    with(binding) {
                        godBackdrop.loading(it.data.backdrop_path)
                        godType.icon(getGodTypeIcon(it.data.type))
                        godName.text = it.data.name
                        godTitle.text = it.data.title
                        godTypeName.text = it.data.type
                        godAttributes.text = getGodAttributes(it.data.attributes)
                        godDescription.text = it.data.description
                        godAttackDamage.text = it.data.attack_damage
                        godAttackSpeed.text = it.data.attack_speed
                        godAttackRange.text = it.data.attack_range
                        godMoveSpeed.text = it.data.move_speed
                        godArmor.text = it.data.armor
                        godMagicResistance.text = it.data.magic_resistance
                        godHealthRegeneration.text = it.data.hp_regeneration
                        godManaRegeneration.text = it.data.mp_regeneration
                        godHealth.text = it.data.health
                        godMana.text = it.data.mana
                    }
                } else {
                    activity?.toast("${it.error}")
                }
            }
        }
    }

    private fun getGodTypeIcon(type: String?): Int {
        return when(type?.toLowerCase()){
            "Guardian" -> R.drawable.ic_class_guardian
            "Warrior" -> R.drawable.ic_class_warrior
            "Hunter" -> R.drawable.ic_class_hunter
            "Mage" -> R.drawable.ic_class_mage
            "Assassin" -> R.drawable.ic_class_assassin
            else -> R.drawable.ic_empty
        }
    }
    private fun getGodAttributes(attributes: String?): String{
        return attributes!!.split(" - ").joinToString("  ")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}