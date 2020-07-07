package pe.isil.esports.presentation.god

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.isil.esports.R
import pe.isil.esports.databinding.FragmentGodBinding
import pe.isil.esports.presentation.login.LoginViewModel
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
    private val userViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGodBinding.inflate(inflater, container, false)

        with((activity as AppCompatActivity)) {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(GodFragmentDirections.actionGodFragmentToGodsFragment())
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        with(viewModel) {
            observe(getGod(god)) {
                if (it.data != null) {
                    with(binding) {
                        godBackdrop.loading(it.data.backdrop_path, progress)
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

        with(userViewModel) {
            observe(active()) {
                if (it.data != null) {
                    setHasOptionsMenu(true)
                }
            }
        }
    }

    private fun getGodTypeIcon(type: String?): Int {
        return when (type) {
            "Guardian" -> R.drawable.ic_class_guardian
            "Warrior" -> R.drawable.ic_class_warrior
            "Hunter" -> R.drawable.ic_class_hunter
            "Mage" -> R.drawable.ic_class_mage
            "Assassin" -> R.drawable.ic_class_assassin
            else -> R.drawable.ic_empty
        }
    }

    private fun getGodAttributes(attributes: String?): String {
        return attributes!!.split(" - ").joinToString("  ")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit -> findNavController().navigate(
                GodFragmentDirections.actionGodFragmentToUpdateGodFragment(
                    god
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