package pe.isil.esports.presentation.hero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.isil.esports.databinding.FragmentCreateHeroBinding
import pe.isil.esports.domain.model.Hero
import pe.isil.esports.utils.observe
import pe.isil.esports.utils.toast
import pe.isil.esports.utils.validate

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

        with((activity as AppCompatActivity)) {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(CreateHeroFragmentDirections.actionCreateHeroFragmentToHeroesFragment())
        }

        setupView()

        return binding.root
    }

    private fun setupView() {

        with(binding) {
            cancel.setOnClickListener {
                findNavController().navigate(CreateHeroFragmentDirections.actionCreateHeroFragmentToHeroesFragment())
            }

            save.setOnClickListener {
                if (validateForm()) {
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
                } else {
                    activity?.toast("You must fill in all the fields.")
                }
            }
        }
    }

    private fun validateForm(): Boolean {
        return with(binding) {
            name.validate()
            attribute.validate()
            type.validate()
            roles.validate()
            poster.validate()
            backdrop.validate()
            description.validate()
            strength.validate()
            agility.validate()
            intelligence.validate()
            attackDamage.validate()
            armor.validate()
            moveSpeed.validate()
            health.validate()
            healthRegeneration.validate()
            mana.validate()
            manaRegeneration.validate()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}