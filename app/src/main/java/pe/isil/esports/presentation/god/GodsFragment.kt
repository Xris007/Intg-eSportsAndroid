package pe.isil.esports.presentation.god

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.isil.esports.databinding.FragmentGodsBinding
import pe.isil.esports.domain.model.God
import pe.isil.esports.utils.observe
import pe.isil.esports.utils.toast

class GodsFragment : Fragment() {

    private var _binding: FragmentGodsBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: GodViewModel by viewModel()

    private val godAdapter: GodAdapter by lazy {
        GodAdapter(emptyList()) { activity?.toast(it.name) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGodsBinding.inflate(inflater, container, false)

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

    private val gods = Observer<List<God>> {
        godAdapter.update(it)
    }

    private fun setupRecycler() {
        activity?.let {
            binding.godsRecycler.apply {
                layoutManager = GridLayoutManager(it, 2)
                adapter = godAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll().observe(this, gods)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}