package pe.isil.esports.presentation.hero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.isil.esports.databinding.FragmentHeroesBinding
import pe.isil.esports.domain.model.Hero
import pe.isil.esports.utils.observe
import pe.isil.esports.utils.toast

@ExperimentalCoroutinesApi
class HeroesFragment : Fragment() {

    private var _binding: FragmentHeroesBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: HeroViewModel by viewModel()

    private val heroAdapter: HeroAdapter by lazy {
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
            binding.heroesRecycler.apply {
                layoutManager = GridLayoutManager(it, 2)
                adapter = heroAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        with(viewModel) {
            observe(getAll()) {
                if (it.data != null) {
                    if (it.data.isNotEmpty()) heroAdapter.update(it.data) else activity?.toast("Empty")
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