package pe.isil.esports.presentation.god

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.isil.esports.R
import pe.isil.esports.databinding.FragmentGodsBinding
import pe.isil.esports.utils.loading
import pe.isil.esports.utils.observe
import pe.isil.esports.utils.toast

@ExperimentalCoroutinesApi
class GodsFragment : Fragment() {

    private var _binding: FragmentGodsBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: GodViewModel by viewModel()

    private val godGuardianAdapter: GodAdapter by lazy {
        GodAdapter {
            findNavController().navigate(
                GodsFragmentDirections.actionGodsFragmentToGodFragment(
                    it
                )
            )
        }
    }

    private val godWarriorAdapter: GodAdapter by lazy {
        GodAdapter {
            findNavController().navigate(
                GodsFragmentDirections.actionGodsFragmentToGodFragment(
                    it
                )
            )
        }
    }

    private val godHunterAdapter: GodAdapter by lazy {
        GodAdapter {
            findNavController().navigate(
                GodsFragmentDirections.actionGodsFragmentToGodFragment(
                    it
                )
            )
        }
    }

    private val godMageAdapter: GodAdapter by lazy {
        GodAdapter {
            findNavController().navigate(
                GodsFragmentDirections.actionGodsFragmentToGodFragment(
                    it
                )
            )
        }
    }

    private val godAssassinAdapter: GodAdapter by lazy {
        GodAdapter {
            findNavController().navigate(
                GodsFragmentDirections.actionGodsFragmentToGodFragment(
                    it
                )
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGodsBinding.inflate(inflater, container, false)

        with((activity as AppCompatActivity)) {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(GodsFragmentDirections.actionGodsFragmentToMainFragment())
        }

        setHasOptionsMenu(true)

        binding.godGuide.loading("https://i.ibb.co/5jDszss/ic-guide-god.png", binding.progress)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecycler()
    }

    private fun setupRecycler() {
        activity?.let {
            binding.godGuardianRecycler.apply {
                layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
                adapter = godGuardianAdapter
            }

            binding.godWarriorRecycler.apply {
                layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
                adapter = godWarriorAdapter
            }

            binding.godHunterRecycler.apply {
                layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
                adapter = godHunterAdapter
            }

            binding.godMageRecycler.apply {
                layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
                adapter = godMageAdapter
            }

            binding.godAssassinRecycler.apply {
                layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
                adapter = godAssassinAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        with(viewModel) {
            observe(getGuardians()) {
                if (it.data != null) {
                    if (it.data.isNotEmpty()) {
                        godGuardianAdapter.update(it.data)
                    } else {
                        activity?.toast("Empty")
                    }
                } else {
                    activity?.toast("${it.error}")
                }
            }

            observe(getWarriors()) {
                if (it.data != null) {
                    if (it.data.isNotEmpty()) {
                        godWarriorAdapter.update(it.data)
                    } else {
                        activity?.toast("Empty")
                    }
                } else {
                    activity?.toast("${it.error}")
                }
            }

            observe(getHunters()) {
                if (it.data != null) {
                    if (it.data.isNotEmpty()) {
                        godHunterAdapter.update(it.data)
                    } else {
                        activity?.toast("Empty")
                    }
                } else {
                    activity?.toast("${it.error}")
                }
            }

            observe(getMages()) {
                if (it.data != null) {
                    if (it.data.isNotEmpty()) {
                        godMageAdapter.update(it.data)
                    } else {
                        activity?.toast("Empty")
                    }
                } else {
                    activity?.toast("${it.error}")
                }
            }

            observe(getAssassins()) {
                if (it.data != null) {
                    if (it.data.isNotEmpty()) {
                        godAssassinAdapter.update(it.data)
                    } else {
                        activity?.toast("Empty")
                    }
                } else {
                    activity?.toast("${it.error}")
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.create, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.create -> findNavController().navigate(GodsFragmentDirections.actionGodsFragmentToCreateGodFragment())
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}