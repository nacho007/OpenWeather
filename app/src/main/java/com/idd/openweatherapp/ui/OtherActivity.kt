package com.idd.openweatherapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.idd.openweatherapp.R
import com.idd.openweatherapp.ui.weatherdetail.FragmentCityWeatherDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.other_activity.*
import javax.inject.Inject

/**
 * Created by ignaciodeandreisdenis on 8/10/20.
 */
@AndroidEntryPoint
class OtherActivity : AppCompatActivity() {

    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.other_activity)

        other_activity_button?.setOnClickListener {
            viewModel.invokeService("sdf")
            Log.e("", "")
        }
    }

    class MyViewModel @ViewModelInject constructor(
        private val repository: Repository,
        @Assisted private val savedStateHandle: SavedStateHandle
    ) : ViewModel(), LifecycleObserver {

        companion object {
            const val KEY = "KEY"
        }

        private val showTextLiveData = savedStateHandle.getLiveData<String>(KEY)

        val showTextDataNotifier: LiveData<String>
            get() = showTextLiveData

        fun fetchValue() {
            showTextLiveData.value = repository.getMessage()
        }

        fun invokeService(cityName: String) {
            Log.e("sdf", "")
        }
    }

    class Repository @Inject constructor() {
        fun getMessage() = "From Repository"
    }
}
