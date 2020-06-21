package pe.isil.esports.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import pe.isil.esports.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(binding) {
            buttonChampion.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToChampionsFragment())
            }

            buttonGod.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToGodsFragment())
            }

            buttonHero.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToHeroesFragment())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}