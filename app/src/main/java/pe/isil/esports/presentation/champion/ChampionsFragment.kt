package pe.isil.esports.presentation.champion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.isil.esports.databinding.FragmentChampionsBinding
import pe.isil.esports.utils.loading
import pe.isil.esports.utils.observe
import pe.isil.esports.utils.toast

@ExperimentalCoroutinesApi
class ChampionsFragment : Fragment() {

    private var _binding: FragmentChampionsBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: ChampionViewModel by viewModel()

    private val championAssassinsAdapter: ChampionAdapter by lazy {
        ChampionAdapter {
            findNavController().navigate(
                ChampionsFragmentDirections.actionChampionsFragmentToChampionFragment(
                    it
                )
            )
        }
    }

    private val championFightersAdapter: ChampionAdapter by lazy {
        ChampionAdapter {
            findNavController().navigate(
                ChampionsFragmentDirections.actionChampionsFragmentToChampionFragment(
                    it
                )
            )
        }
    }

    private val championMagesAdapter: ChampionAdapter by lazy {
        ChampionAdapter {
            findNavController().navigate(
                ChampionsFragmentDirections.actionChampionsFragmentToChampionFragment(
                    it
                )
            )
        }
    }

    private val championMarksmenAdapter: ChampionAdapter by lazy {
        ChampionAdapter {
            findNavController().navigate(
                ChampionsFragmentDirections.actionChampionsFragmentToChampionFragment(
                    it
                )
            )
        }
    }

    private val championSupportsAdapter: ChampionAdapter by lazy {
        ChampionAdapter {
            findNavController().navigate(
                ChampionsFragmentDirections.actionChampionsFragmentToChampionFragment(
                    it
                )
            )
        }
    }

    private val championTanksAdapter: ChampionAdapter by lazy {
        ChampionAdapter {
            findNavController().navigate(
                ChampionsFragmentDirections.actionChampionsFragmentToChampionFragment(
                    it
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChampionsBinding.inflate(inflater, container, false)

        with((activity as AppCompatActivity)) {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(ChampionsFragmentDirections.actionChampionsFragmentToMainFragment())
        }

        binding.championGuide.loading("https://i.ibb.co/MM1YHLs/ic-guide-champion.png")

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecycler()
    }

    private fun setupRecycler() {
        activity?.let {
            binding.championAssassinsRecycler.apply {
                layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
                adapter = championAssassinsAdapter
            }

            binding.championFightersRecycler.apply {
                layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
                adapter = championFightersAdapter
            }

            binding.championMagesRecycler.apply {
                layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
                adapter = championMagesAdapter
            }

            binding.championMarksmenRecycler.apply {
                layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
                adapter = championMarksmenAdapter
            }

            binding.championSupportsRecycler.apply {
                layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
                adapter = championSupportsAdapter
            }

            binding.championTanksRecycler.apply {
                layoutManager = LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, false)
                adapter = championTanksAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        with(viewModel) {
            observe(getAssassins()) {
                if (it.data != null) {
                    if (it.data.isNotEmpty()) {
                        championAssassinsAdapter.update(it.data)
                    } else {
                        activity?.toast("Empty")
                    }
                } else {
                    activity?.toast("${it.error}")
                }
            }

            observe(getFighters()) {
                if (it.data != null) {
                    if (it.data.isNotEmpty()) {
                        championFightersAdapter.update(it.data)
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
                        championMagesAdapter.update(it.data)
                    } else {
                        activity?.toast("Empty")
                    }
                } else {
                    activity?.toast("${it.error}")
                }
            }

            observe(getMarksmen()) {
                if (it.data != null) {
                    if (it.data.isNotEmpty()) {
                        championMarksmenAdapter.update(it.data)
                    } else {
                        activity?.toast("Empty")
                    }
                } else {
                    activity?.toast("${it.error}")
                }
            }

            observe(getSupports()) {
                if (it.data != null) {
                    if (it.data.isNotEmpty()) {
                        championSupportsAdapter.update(it.data)
                    } else {
                        activity?.toast("Empty")
                    }
                } else {
                    activity?.toast("${it.error}")
                }
            }

            observe(getTanks()) {
                if (it.data != null) {
                    if (it.data.isNotEmpty()) {
                        championTanksAdapter.update(it.data)
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