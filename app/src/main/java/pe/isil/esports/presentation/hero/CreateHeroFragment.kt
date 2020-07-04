package pe.isil.esports.presentation.hero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.isil.esports.databinding.FragmentCreateHeroBinding
import pe.isil.esports.domain.model.Hero
import pe.isil.esports.utils.observe
import pe.isil.esports.utils.toast

@ExperimentalCoroutinesApi
class CreateHeroFragment : Fragment() {

    private var _binding: FragmentCreateHeroBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: HeroViewModel by viewModel()

    private lateinit var hero: Hero

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateHeroBinding.inflate(layoutInflater)

        setupView()

        return binding.root
    }

    private fun setupView() {

        with(binding) {
            hero = Hero(
                id = null,
                name = "${name.text}",
                attribute = "${attribute.text}",
                type = "${type.text}",
                roles = "${roles.text}",
                poster_path = "${poster.text}",
                backdrop_path = "${backdrop.text}",
                description = "${description.text}",
                strength = "${strength.text}",
                agility = "${agility.text}",
                intelligence = "${intelligence.text}",
                attack_damage = "${attackDamage.text}",
                armor = "${armor.text}",
                move_speed = "${moveSpeed.text}",
                health = "${health.text}",
                hp_regeneration = "${healthRegeneration.text}",
                mana = "${mana.text}",
                mp_regeneration = "${manaRegeneration.text}"
            )

            cancel.setOnClickListener {
                findNavController().navigate(CreateHeroFragmentDirections.actionCreateHeroFragmentToHeroesFragment())
            }

            save.setOnClickListener {
                with(viewModel) {
                    observe(createHero(hero)) {
                        if (it.data != null) {
                            activity?.toast("Hero added")
                            findNavController().navigate(CreateHeroFragmentDirections.actionCreateHeroFragmentToHeroesFragment())
                        } else {
                            activity?.toast("${it.error}")
                        }
                    }
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}