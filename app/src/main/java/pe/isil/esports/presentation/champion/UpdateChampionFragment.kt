package pe.isil.esports.presentation.champion

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
import pe.isil.esports.databinding.FragmentUpdateChampionBinding
import pe.isil.esports.domain.model.Champion
import pe.isil.esports.utils.observe
import pe.isil.esports.utils.toast
import pe.isil.esports.utils.validate


@ExperimentalCoroutinesApi
class UpdateChampionFragment : Fragment() {

    private var _binding: FragmentUpdateChampionBinding? = null
    private val binding
        get() = _binding!!

    private val args: UpdateChampionFragmentArgs by navArgs()
    private val champion: Long by lazy { args.id }

    private val viewModel: ChampionViewModel by viewModel()

    private lateinit var updated: Champion

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateChampionBinding.inflate(inflater, container, false)

        with((activity as AppCompatActivity)) {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(
                UpdateChampionFragmentDirections.actionUpdateChampionFragmentToChampionFragment(
                    champion
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
                    UpdateChampionFragmentDirections.actionUpdateChampionFragmentToChampionFragment(
                        champion
                    )
                )
            }

            update.setOnClickListener {
                if (validateForm()) {
                    updated = Champion(
                        id = champion,
                        name = "${name.text}",
                        title = "${title.text}",
                        rol = "${rol.text}",
                        attributes = "${attributes.text}",
                        poster_path = "${poster.text}",
                        backdrop_path = "${backdrop.text}",
                        description = "${description.text}",
                        attack_damage = "${attackDamage.text}",
                        attack_speed = "${attackSpeed.text}",
                        attack_range = "${attackRange.text}",
                        move_speed = "${moveSpeed.text}",
                        armor = "${armor.text}",
                        magic_resistance = "${magicResistance.text}",
                        hp_regeneration = "${healthRegeneration.text}",
                        mp_regeneration = "${manaRegeneration.text}",
                        health = "${health.text}",
                        mana = "${mana.text}"
                    )

                    with(viewModel) {
                        observe(updateChampion(champion, updated)) {
                            if (it.data != null) {
                                activity?.toast("Champion updated")
                                findNavController().navigate(
                                    UpdateChampionFragmentDirections.actionUpdateChampionFragmentToChampionFragment(
                                        champion
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
            title.validate()
            rol.validate()
            attributes.validate()
            poster.validate()
            backdrop.validate()
            description.validate()
            attackDamage.validate()
            attackSpeed.validate()
            attackRange.validate()
            moveSpeed.validate()
            armor.validate()
            magicResistance.validate()
            healthRegeneration.validate()
            manaRegeneration.validate()
            health.validate()
            mana.validate()
        }
    }

    override fun onResume() {
        super.onResume()
        with(viewModel) {
            observe(getChampion(champion)) {
                if (it.data != null) {
                    with(binding) {
                        name.setText(it.data.name)
                        title.setText(it.data.title)
                        rol.setText(it.data.rol)
                        attributes.setText(it.data.attributes)
                        poster.setText(it.data.poster_path)
                        backdrop.setText(it.data.backdrop_path)
                        description.setText(it.data.description)
                        attackDamage.setText(it.data.attack_damage)
                        attackSpeed.setText(it.data.attack_speed)
                        attackRange.setText(it.data.attack_range)
                        moveSpeed.setText(it.data.move_speed)
                        armor.setText(it.data.armor)
                        magicResistance.setText(it.data.magic_resistance)
                        healthRegeneration.setText(it.data.hp_regeneration)
                        manaRegeneration.setText(it.data.mp_regeneration)
                        health.setText(it.data.health)
                        mana.setText(it.data.mana)
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