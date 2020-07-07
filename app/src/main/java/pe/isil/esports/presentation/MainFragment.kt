package pe.isil.esports.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.isil.esports.databinding.FragmentMainBinding
import pe.isil.esports.presentation.login.LoginViewModel
import pe.isil.esports.utils.observe

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: LoginViewModel by viewModel()

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

            login.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToLoginFragment())
            }

            logout.setOnClickListener {
                with(viewModel) {
                    observe(clear()) {
                        if (it.data != null) {
                            login.visibility = View.VISIBLE
                            logout.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        with(viewModel) {
            observe(active()) {
                if (it.data != null) {
                    binding.login.visibility = View.GONE
                    binding.logout.visibility = View.VISIBLE
                } else {
                    binding.login.visibility = View.VISIBLE
                    binding.logout.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}