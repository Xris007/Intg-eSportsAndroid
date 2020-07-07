package pe.isil.esports.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import pe.isil.esports.databinding.FragmentLoginBinding
import pe.isil.esports.utils.observe
import pe.isil.esports.utils.toast

class LoginFragment : DialogFragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        setupView()

        return binding.root
    }

    private fun setupView() {
        with(binding) {
            signIn.setOnClickListener {
                with(viewModel) {
                    observe(login("${username.text}", "${password.text}")) {
                        if (it.data != null) {
                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
                            activity?.toast("Welcome!")
                        } else {
                            Snackbar.make(binding.root, "Invalid user", Snackbar.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }

            cancel.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val window = dialog?.window ?: return
        val params = window.attributes
        params.width = 1000
        params.height = 600
        window.attributes = params
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}