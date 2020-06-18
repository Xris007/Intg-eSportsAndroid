package pe.isil.esports.presentation.champion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.isil.esports.databinding.FragmentChampionsBinding
import pe.isil.esports.utils.observe
import pe.isil.esports.utils.toast

@ExperimentalCoroutinesApi
class ChampionsFragment : Fragment() {

    private var _binding: FragmentChampionsBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: ChampionViewModel by viewModel()

    private val championAdapter: ChampionAdapter by lazy {
        ChampionAdapter { activity?.toast(it.name) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChampionsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecycler()
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
        with(viewModel) {
            observe(getAll()) {
                if (it.loading) {
                    //
                } else if (!it.loading && it.data != null) {
                    if (it.data.isNotEmpty()) championAdapter.update(it.data) else activity?.toast("Empty")
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