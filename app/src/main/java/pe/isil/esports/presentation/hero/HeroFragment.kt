package pe.isil.esports.presentation.hero

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.isil.esports.R
import pe.isil.esports.databinding.FragmentHeroBinding
import pe.isil.esports.utils.icon
import pe.isil.esports.utils.loading
import pe.isil.esports.utils.observe
import pe.isil.esports.utils.toast

@ExperimentalCoroutinesApi
class HeroFragment : Fragment() {

    private var _binding: FragmentHeroBinding? = null
    private val binding
        get() = _binding!!

    private val args: HeroFragmentArgs by navArgs()
    private val hero: Long by lazy { args.id }

    private val viewModel: HeroViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHeroBinding.inflate(inflater, container, false)

        with((activity as AppCompatActivity)) {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(HeroFragmentDirections.actionHeroFragmentToHeroesFragment())
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        with(viewModel) {
            observe(getHero(hero)) {
                if (it.data != null) {
                    with(binding) {
                        heroBackdrop.loading(it.data.backdrop_path, progress)
                        heroAttribute.icon(getHeroAttributeIcon(it.data.attribute))
                        heroName.text = it.data.name
                        heroType.text = it.data.type
                        heroRoles.text = getHeroType(it.data.roles)
                        heroDescription.text = it.data.description
                        heroStrength.text = it.data.strength
                        heroAgility.text = it.data.agility
                        heroIntelligence.text = it.data.intelligence
                        heroAttackDamage.text = it.data.attack_damage
                        heroArmor.text = it.data.armor
                        heroMoveSpeed.text = it.data.move_speed
                        heroHealth.text = it.data.health
                        heroHp.text = it.data.hp_regeneration
                        heroMana.text = it.data.mana
                        heroMp.text = it.data.mp_regeneration
                    }
                } else {
                    activity?.toast("${it.error}")
                }
            }
        }
    }

    private fun getHeroAttributeIcon(attribute: String?): Int {
        return when (attribute) {
            "Strength" -> R.drawable.ic_strength
            "Agility" -> R.drawable.ic_agility
            "Intelligence" -> R.drawable.ic_intelligence
            else -> R.drawable.ic_empty
        }
    }

    private fun getHeroType(type: String?): String {
        return type!!.split(" - ").joinToString("  ")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit -> findNavController().navigate(HeroFragmentDirections.actionHeroFragmentToUpdateHeroFragment(hero))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}