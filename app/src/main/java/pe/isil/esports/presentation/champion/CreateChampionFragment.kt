package pe.isil.esports.presentation.champion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.isil.esports.R
import pe.isil.esports.databinding.FragmentChampionBinding
import pe.isil.esports.databinding.FragmentCreateChampionBinding


@ExperimentalCoroutinesApi
class CreateChampionFragment : Fragment() {

    private var _binding: FragmentCreateChampionBinding? = null
    private val binding
        get() = _binding!!


    private val viewModel: ChampionViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateChampionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}