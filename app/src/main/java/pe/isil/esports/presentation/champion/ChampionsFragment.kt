package pe.isil.esports.presentation.champion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.isil.esports.databinding.FragmentChampionsBinding
import pe.isil.esports.domain.model.Champion
import pe.isil.esports.utils.observe
import pe.isil.esports.utils.toast

class ChampionsFragment : Fragment() {

    private var _binding: FragmentChampionsBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: ChampionViewModel by viewModel()

    private val championAdapter: ChampionAdapter by lazy {
        ChampionAdapter(emptyList()) { activity?.toast(it.name) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChampionsBinding.inflate(inflater, container, false)

        setupViewModel()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecycler()
    }

    private fun setupViewModel() {
        with(viewModel) {
            observe(isViewLoading) { }
            observe(onMessageError) { activity?.toast("$it") }
            observe(isEmptyList) { activity?.toast("$it") }
        }
    }

    private val champions = Observer<List<Champion>> {
        championAdapter.update(it)
    }

    private fun setupRecycler() {
        activity?.let {
            binding.championsRecycler.apply {
                layoutManager = GridLayoutManager(it, 2)
                adapter = championAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll().observe(this, champions)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}