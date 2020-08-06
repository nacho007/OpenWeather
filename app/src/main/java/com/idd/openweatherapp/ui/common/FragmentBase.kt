package com.idd.openweatherapp.ui.common

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Created by ignaciodeandreisdenis on 8/6/20.
 */

open class FragmentBase : Fragment() {

    private val myTag = "Tag: " + this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(myTag, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.v(myTag, "onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
        Log.v(myTag, "onPause")
    }

    override fun onDetach() {
        super.onDetach()
        Log.v(myTag, "onDetach")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(myTag, "onDestroy")
    }
}
