package pe.isil.esports.presentation.champion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.isil.esports.R
import pe.isil.esports.databinding.FragmentChampionBinding
import pe.isil.esports.databinding.FragmentCreateChampionBinding
import pe.isil.esports.domain.model.Champion
import pe.isil.esports.utils.observe
import pe.isil.esports.utils.toast


@ExperimentalCoroutinesApi
class CreateChampionFragment : Fragment() {

    private var _binding: FragmentCreateChampionBinding? = null
    private val binding
        get() = _binding!!


    private val viewModel: ChampionViewModel by viewModel()

    private lateinit var champion: Champion

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateChampionBinding.inflate(layoutInflater)

        with((activity as AppCompatActivity)){
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(true)
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(CreateChampionFragmentDirections.actionCreateChampionFragmentToChampionsFragment())
        }

        setupView()

        return binding.root
    }

    private fun setupView(){
        with(binding){
            champion = Champion(
                id = null,
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

            cancel.setOnClickListener {
                findNavController().navigate(CreateChampionFragmentDirections.actionCreateChampionFragmentToChampionsFragment())
            }

            save.setOnClickListener {
                with(viewModel){
                    observe(createChampion(champion)){
                        if (it.data != null) {
                            activity?.toast("Champion added")
                            findNavController().navigate(CreateChampionFragmentDirections.actionCreateChampionFragmentToChampionsFragment())
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