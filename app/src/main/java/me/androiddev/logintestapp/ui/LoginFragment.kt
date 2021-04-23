package me.androiddev.logintestapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import me.androiddev.logintestapp.App
import me.androiddev.logintestapp.MainActivity
import me.androiddev.logintestapp.R
import me.androiddev.logintestapp.common.closeSoftKeyboard
import me.androiddev.logintestapp.common.parentError
import me.androiddev.logintestapp.common.showAlertDialog
import me.androiddev.logintestapp.databinding.WindowLoginBinding
import me.androiddev.logintestapp.ui.mv.LoginFragmentVM
import javax.inject.Inject

class LoginFragment(val layoutId: Int = R.layout.window_login) : Fragment() {


    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: LoginFragmentVM

    lateinit var binding: WindowLoginBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.coreComponent(requireContext()).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, modelFactory).get(LoginFragmentVM::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WindowLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initClickers()
        initVM()
    }

    fun initClickers() {

        binding.submitButton.setOnClickListener {
            requireActivity().closeSoftKeyboard()
            viewModel.validateData()
        }

        binding.edPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                requireActivity().closeSoftKeyboard()
                viewModel.validateData()
            }
            true
        }


    }

    fun initVM() {

        viewModel.loginErrorEvent.observe(this) {
            binding.edLogin.parentError = getString(R.string.warning_enter_login)
        }

        viewModel.passwordErrorEvent.observe(this) {
            binding.edPassword.parentError = getString(R.string.warning_enter_password)
        }

        viewModel.loginEvent.observe(this) {
            if (it)
                findNavController().navigate(R.id.action_loginFragment_to_successFragment)
            else {
                requireContext().showAlertDialog(getString(R.string.error_login))
            }
        }

        viewModel.apiBlockWindow.removeObservers(this)
        viewModel.apiBlockWindow.observe(viewLifecycleOwner) {
            if (it)
                (requireActivity() as MainActivity).disableUserInteraction()
            else
                (requireActivity() as MainActivity).enableUserInteraction()
        }

        viewModel.apiNoConnection.observe(this) {
            requireContext().showAlertDialog(getString(R.string.no_connection))
        }

    }

}