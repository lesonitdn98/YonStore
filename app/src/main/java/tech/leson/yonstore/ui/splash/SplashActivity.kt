package tech.leson.yonstore.ui.splash

import org.koin.androidx.viewmodel.ext.android.viewModel
import tech.leson.yonstore.BR
import tech.leson.yonstore.R
import tech.leson.yonstore.databinding.ActivitySplashBinding
import tech.leson.yonstore.ui.base.BaseActivity
import tech.leson.yonstore.ui.login.LoginActivity
import tech.leson.yonstore.ui.main.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashNavigator, SplashViewModel>(),
    SplashNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_splash
    override val viewModel: SplashViewModel by viewModel()

    override fun init() {
        viewModel.setNavigator(this)
        viewModel.checkLoginState()
    }

    override fun openLogin() {
        startActivity(LoginActivity.getIntent(this))
        finish()
    }

    override fun openMain() {
        startActivity(MainActivity.getIntent(this))
        finish()
    }
}
