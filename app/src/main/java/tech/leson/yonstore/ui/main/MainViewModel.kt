package tech.leson.yonstore.ui.main

import android.view.View
import tech.leson.yonstore.data.DataManager
import tech.leson.yonstore.ui.base.BaseViewModel
import tech.leson.yonstore.utils.rx.SchedulerProvider

class MainViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<MainNavigator>(dataManager, schedulerProvider) {
    fun onClick(view: View) {
    }
}
