package pe.isil.esports.presentation.hero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.isil.esports.databinding.FragmentHeroesBinding
import pe.isil.esports.utils.observe
import pe.isil.esports.utils.toast

@ExperimentalCoroutinesApi
class HeroesFragment : Fragment() {

    private var _binding: FragmentHeroesBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: HeroViewModel by viewModel()

    private val heroStrengthAdapter: HeroAdapter by lazy {
        HeroAdapter { activity?.toast(it.name) }
    }

    private val heroAgilityAdapter: HeroAdapter by lazy {
        HeroAdapter { activity?.toast(it.name) }
    }

    private val heroIntelligenceAdapter: HeroAdapter by lazy {
        HeroAdapter { activity?.toast(it.name) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHeroesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecycler()
    }


    private fun setupRecycler() {
        activity?.let {
            binding.strengthRecycler.apply {
                layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
                adapter = heroStrengthAdapter
            }

            binding.agilityRecycler.apply {
                layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
                adapter = heroAgilityAdapter
            }

            binding.intelligenceRecycler.apply {
                layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
                adapter = heroIntelligenceAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        with(viewModel) {
            observe(getStrength()) {
                if (it.data != null) {
                    if (it.data.isNotEmpty()) {
                        heroStrengthAdapter.update(it.data)
                    } else {
                        activity?.toast("Empty")
                    }
                } else {
                    activity?.toast("${it.error}")
                }
            }

            observe(getAgility()) {
                if (it.data != null) {
                    if (it.data.isNotEmpty()) {
                        heroAgilityAdapter.update(it.data)
                    } else {
                        activity?.toast("Empty")
                    }
                } else {
                    activity?.toast("${it.error}")
                }
            }

            observe(getIntelligence()) {
                if (it.data != null) {
                    if (it.data.isNotEmpty()) {
                        heroIntelligenceAdapter.update(it.data)
                    } else {
                        activity?.toast("Empty")
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