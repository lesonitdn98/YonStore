package tech.leson.yonstore.ui.event

import android.view.View
import androidx.lifecycle.MutableLiveData
import tech.leson.yonstore.R
import tech.leson.yonstore.data.DataManager
import tech.leson.yonstore.data.model.Category
import tech.leson.yonstore.data.model.Event
import tech.leson.yonstore.ui.base.BaseViewModel
import tech.leson.yonstore.utils.rx.SchedulerProvider

class EventViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<EventNavigator>(dataManager, schedulerProvider) {

    fun getEvents() {
        setIsLoading(true)
        dataManager.getAllEvent()
            .addOnSuccessListener {
                setIsLoading(false)
                val data: MutableList<Event> = ArrayList()
                for (doc in it) {
                    val event = doc.toObject(Event::class.java)
                    data.add(event)
                }
                navigator?.setEvent(data)
            }
            .addOnFailureListener {
                setIsLoading(false)
                navigator?.onError(it.message.toString())
            }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnAddEvent -> navigator?.onCreateEvent()
            R.id.btnBack -> navigator?.onBack()
        }
    }
}
