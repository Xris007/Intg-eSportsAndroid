package pe.isil.esports.presentation.hero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.isil.esports.databinding.FragmentUpdateHeroBinding
import pe.isil.esports.domain.model.Hero
import pe.isil.esports.utils.observe
import pe.isil.esports.utils.toast
import pe.isil.esports.utils.validate

@ExperimentalCoroutinesApi
class UpdateHeroFragment : Fragment() {

    private var _binding: FragmentUpdateHeroBinding? = null
    private val binding
        get() = _binding!!

    private val args: UpdateHeroFragmentArgs by navArgs()
    private val hero: Long by lazy { args.id }

    private val viewModel: HeroViewModel by viewModel()

    private lateinit var updated: Hero

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateHeroBinding.inflate(inflater, container, false)

        with((activity as AppCompatActivity)) {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(
                UpdateHeroFragmentDirections.actionUpdateHeroFragmentToHeroFragment(
                    hero
                )
            )
        }

        setupView()

        return binding.root
    }

    private fun setupView() {

        with(binding) {
            cancel.setOnClickListener {
                findNavController().navigate(
                    UpdateHeroFragmentDirections.actionUpdateHeroFragmentToHeroFragment(
                        hero
                    )
                )
            }

            update.setOnClickListener {
                if (validateForm()) {
                    updated = Hero(
                        id = hero,
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
                        observe(updateHero(hero, updated)) {
                            if (it.data != null) {
                                activity?.toast("Hero updated")
                                findNavController().navigate(
                                    UpdateHeroFragmentDirections.actionUpdateHeroFragmentToHeroFragment(
                                        hero
                                    )
                                )
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

    override fun onResume() {
        super.onResume()
        with(viewModel) {
            observe(getHero(hero)) {
                if (it.data != null) {
                    with(binding) {
                        name.setText(it.data.name)
                        attribute.setText(it.data.attribute)
                        type.setText(it.data.type)
                        roles.setText(it.data.roles)
                        poster.setText(it.data.poster_path)
                        backdrop.setText(it.data.backdrop_path)
                        description.setText(it.data.description)
                        strength.setText(it.data.strength)
                        agility.setText(it.data.agility)
                        intelligence.setText(it.data.intelligence)
                        attackDamage.setText(it.data.attack_damage)
                        armor.setText(it.data.armor)
                        moveSpeed.setText(it.data.move_speed)
                        health.setText(it.data.health)
                        healthRegeneration.setText(it.data.hp_regeneration)
                        mana.setText(it.data.mana)
                        manaRegeneration.setText(it.data.mp_regeneration)
                    }
                } else {
                    activity?.toast("${it.error}")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}