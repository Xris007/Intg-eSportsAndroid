package pe.isil.esports.presentation.god

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.isil.esports.databinding.FragmentCreateGodBinding
import pe.isil.esports.domain.model.God
import pe.isil.esports.utils.observe
import pe.isil.esports.utils.toast

@ExperimentalCoroutinesApi
class CreateGodFragment : Fragment() {

    private var _binding: FragmentCreateGodBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: GodViewModel by viewModel()

    private lateinit var god: God

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateGodBinding.inflate(layoutInflater)

        with((activity as AppCompatActivity)) {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(CreateGodFragmentDirections.actionCreateGodFragmentToGodsFragment())
        }

        setupView()

        return binding.root
    }

    private fun setupView() {

        with(binding) {

            cancel.setOnClickListener {
                findNavController().navigate(CreateGodFragmentDirections.actionCreateGodFragmentToGodsFragment())
            }

            save.setOnClickListener {

                god = God(
                    id = null,
                    name = "${name.text}",
                    title = "${title.text}",
                    type = "${type.text}",
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
                    observe(createGod(god)) {
                        if (it.data != null) {
                            activity?.toast("God added")
                            findNavController().navigate(CreateGodFragmentDirections.actionCreateGodFragmentToGodsFragment())
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