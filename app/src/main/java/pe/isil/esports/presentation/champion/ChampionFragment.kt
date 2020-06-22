package pe.isil.esports.presentation.champion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.isil.esports.databinding.FragmentChampionBinding
import pe.isil.esports.utils.loading
import pe.isil.esports.utils.observe
import pe.isil.esports.utils.toast

@ExperimentalCoroutinesApi
class ChampionFragment : Fragment() {

    private var _binding: FragmentChampionBinding? = null
    private val binding
        get() = _binding!!

    private val args: ChampionFragmentArgs by navArgs()
    private val champion: Long by lazy { args.id }

    private val viewModel: ChampionViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChampionBinding.inflate(inflater, container, false)

        binding.back.setOnClickListener {
            activity?.onBackPressed()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        with(viewModel) {
            observe(getChampion(champion)) {
                if (it.data != null) {
                    with(binding) {
                        championBackdrop.loading(it.data.background_path)
                        championName.text = it.data.name
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