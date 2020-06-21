package pe.isil.esports.presentation.god

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.isil.esports.databinding.FragmentGodBinding
import pe.isil.esports.utils.observe
import pe.isil.esports.utils.posterLoading
import pe.isil.esports.utils.toast

@ExperimentalCoroutinesApi
class GodFragment : Fragment() {

    private var _binding: FragmentGodBinding? = null
    private val binding
        get() = _binding!!

    private val args: GodFragmentArgs by navArgs()
    private val godId: Long by lazy { args.id }
    private val godName: String by lazy { args.name }

    private val viewModel: GodViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGodBinding.inflate(inflater, container, false)

        with((activity as AppCompatActivity)) {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.title = godName
        }

        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        with(viewModel) {
            observe(getGod(godId)) {
                if (it.data != null) {
                    with(binding) {
                        godBackdrop.posterLoading(it.data.background_path)
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