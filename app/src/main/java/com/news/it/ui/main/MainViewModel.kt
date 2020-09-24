package com.news.it.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.it.domain.DataRepository
import com.news.it.domain.DataRepositoryIml
import com.news.it.domain.Event
import com.news.it.domain.model.ChannelNews
import com.news.it.domain.model.NewsData
import com.news.it.ui.news.CommonWebviewActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(appCtx: Context) : ViewModel() {

    private var dataRepo: DataRepository = DataRepositoryIml(appCtx)

    private var isRssLoaded: Boolean = false

    private val _rssLoading = MutableLiveData<Boolean>()
    val rssLoading: LiveData<Boolean> = _rssLoading

    private val _loadingError = MutableLiveData<Event<Boolean>>()
    val loadingError: LiveData<Event<Boolean>> = _loadingError

    private val _rssData = MutableLiveData<Event<List<ChannelNews>>>()
    val rssData: LiveData<Event<List<ChannelNews>>> = _rssData

    fun resume() {
        if (!isRssLoaded) {
            _rssLoading.value = true
            getRss()
        }
    }

    fun showNewsItem(baseContext: Context?, item: ChannelNews?) {
        if (item == null) {
            _loadingError.value = Event(true)
        } else {
            baseContext?.let {
                CommonWebviewActivity.start(
                    baseContext,
                    item.title ?: "",
                    item.link ?: ""
                )
            }
        }
    }

    private fun getRss() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response = dataRepo.getNews()
                if (response.isSuccess()) {
                    _rssData.value = Event((response.payload as NewsData).channelNews)
                    isRssLoaded = true
                } else {
                    _loadingError.value = Event(true)
                }
            } catch (ex: Exception) {
                _loadingError.value = Event(true)

            } finally {
                _rssLoading.value = false
            }
        }
    }

}