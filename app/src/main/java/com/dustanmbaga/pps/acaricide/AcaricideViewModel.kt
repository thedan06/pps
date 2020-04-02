package com.dustanmbaga.pps.acaricide

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dustanmbaga.pps.util.singleArgViewModelFactory


class AcaricideViewModel(private val repository: AcaricideRepository): ViewModel() {

    companion object {
        val FACTORY = singleArgViewModelFactory(::AcaricideViewModel)
    }

    val showProgress: LiveData<Boolean>
    val acaricideList: LiveData<List<Acaricide>>

    private var _searchStringLiveData = MutableLiveData<String>()

    init {
        this.showProgress = repository.showProgress
        this.acaricideList = repository.getAcaricideFromDB()

        _searchStringLiveData.value = ""
    }

    /*fun changeState() {
        repository.changeState()
    }*/

    suspend fun refreshAcaricides() {
        repository.refreshAcaricides()
    }

    fun searchAcaricides(searchText: String) {
        _searchStringLiveData.value = searchText
    }

    val searchedAcaricideLists = Transformations.switchMap(_searchStringLiveData){ searchText ->
        repository.searchAcaricideFromDB(searchText)
    }

}
