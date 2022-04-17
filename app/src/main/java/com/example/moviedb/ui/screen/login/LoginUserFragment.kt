package com.example.moviedb.ui.screen.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.moviedb.R
import com.example.moviedb.databinding.FragmentLoginUserBinding
import com.example.moviedb.ui.base.BaseFragment
import com.example.moviedb.ui.base.getNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginUserFragment : BaseFragment<FragmentLoginUserBinding, LoginViewModel>() {


    override val layoutId: Int = R.layout.fragment_login_user

    override val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.btnLogin.setOnClickListener {
            if (isLoginValid()) {
                viewModel.loginUser()
                toMain()
            } else {
                showToast("User Name and Password minimum 3 Character")
            }

        }
    }

    private fun isLoginValid(): Boolean {
        val edtUserName = viewBinding.edtUsername.text.toString()
        val edtPassword = viewBinding.edtPassword.text.toString()
        viewModel.setDataForm(edtUserName, edtPassword)
        viewModel.loginRequest.userName = edtUserName
        viewModel.loginRequest.password = edtPassword

        return (edtUserName.isNotBlank() && edtUserName.length > 3) && (edtPassword.isNotBlank() && edtPassword.length > 3)
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun toMain() {
        getNavController()?.navigate(
            LoginUserFragmentDirections.toMain()
        )
    }

}