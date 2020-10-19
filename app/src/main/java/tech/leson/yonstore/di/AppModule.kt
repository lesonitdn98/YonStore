package tech.leson.yonstore.di

import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import tech.leson.yonstore.data.AppDataManager
import tech.leson.yonstore.data.DataManager
import tech.leson.yonstore.data.local.db.AppDbHelper
import tech.leson.yonstore.data.local.db.DbHelper
import tech.leson.yonstore.data.local.prefs.AppPreferencesHelper
import tech.leson.yonstore.data.local.prefs.PreferencesHelper
import tech.leson.yonstore.data.remote.ApiHelper
import tech.leson.yonstore.data.remote.AppApiHelper
import tech.leson.yonstore.ui.category.CategoryViewModel
import tech.leson.yonstore.ui.favorite.FavoriteViewModel
import tech.leson.yonstore.ui.login.LoginViewModel
import tech.leson.yonstore.ui.main.MainActivity
import tech.leson.yonstore.ui.main.MainTabAdapter
import tech.leson.yonstore.ui.main.MainViewModel
import tech.leson.yonstore.ui.main.account.AccountViewModel
import tech.leson.yonstore.ui.main.cart.CartViewModel
import tech.leson.yonstore.ui.main.explore.ExploreViewModel
import tech.leson.yonstore.ui.main.home.HomeViewModel
import tech.leson.yonstore.ui.main.home.adapter.CategoryAdapter
import tech.leson.yonstore.ui.main.home.adapter.ProductAdapter
import tech.leson.yonstore.ui.main.home.adapter.SlideShowAdapter
import tech.leson.yonstore.ui.main.offer.OfferViewModel
import tech.leson.yonstore.ui.product.ProductViewModel
import tech.leson.yonstore.ui.product.adapter.ProductImgAdapter
import tech.leson.yonstore.ui.register.RegisterViewModel
import tech.leson.yonstore.ui.splash.SplashViewModel
import tech.leson.yonstore.ui.verify.PhoneVerifyViewModel
import tech.leson.yonstore.utils.rx.AppSchedulerProvider
import tech.leson.yonstore.utils.rx.SchedulerProvider

val appModule = module {
    single<DbHelper> { AppDbHelper() }
    single<PreferencesHelper> { AppPreferencesHelper() }
    single<ApiHelper> { AppApiHelper() }
    single<DataManager> { AppDataManager() }
    single<SchedulerProvider> { AppSchedulerProvider() }
    single {
        val auth = FirebaseAuth.getInstance()
        auth.setLanguageCode("vn")
        return@single auth
    }

    viewModel { SplashViewModel(get(), get(), get()) }
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { RegisterViewModel(get(), get(), get()) }
    viewModel { MainViewModel(get(), get()) }
    viewModel { PhoneVerifyViewModel(get(), get(), get()) }
}

val mainModule = module {
    single { SlideShowAdapter(ArrayList()) }
    single(named("home")) {
        CategoryAdapter(ArrayList(),
            CategoryAdapter.LAYOUT_VIEW_TYPE_HOME)
    }
    single(named("manFashion")) {
        CategoryAdapter(ArrayList(),
            CategoryAdapter.LAYOUT_VIEW_TYPE_EXPLORE)
    }
    single(named("womanFashion")) {
        CategoryAdapter(ArrayList(),
            CategoryAdapter.LAYOUT_VIEW_TYPE_EXPLORE)
    }
    single(named("vertical")) {
        ProductAdapter(ArrayList(),
            ProductAdapter.LAYOUT_VIEW_TYPE_VERTICAL)
    }
    single(named("flashSale")) {
        ProductAdapter(ArrayList(),
            ProductAdapter.LAYOUT_VIEW_TYPE_HORIZONTAL)
    }
    single(named("megaSale")) {
        ProductAdapter(ArrayList(),
            ProductAdapter.LAYOUT_VIEW_TYPE_HORIZONTAL)
    }
    single(named("favorite")) {
        ProductAdapter(ArrayList(),
            ProductAdapter.LAYOUT_VIEW_TYPE_VERTICAL)
    }
    single { ProductImgAdapter(ArrayList()) }
    single { (activity: MainActivity) -> MainTabAdapter(activity) }

    viewModel { AccountViewModel(get(), get()) }
    viewModel { CartViewModel(get(), get()) }
    viewModel { ExploreViewModel(get(), get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { OfferViewModel(get(), get()) }

    viewModel { FavoriteViewModel(get(), get()) }
    viewModel { CategoryViewModel(get(), get()) }
    viewModel { ProductViewModel(get(), get()) }
}
