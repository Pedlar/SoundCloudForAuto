package org.notlocalhost.soundcloud.auto.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.notlocalhost.soundcloud.auto.R
import org.notlocalhost.soundcloud.auto.boilerplates.BaseFragment
import org.notlocalhost.soundcloud.auto.di.scopes.ActivityScope
import javax.inject.Inject

@ActivityScope
class HomeFragment @Inject constructor() : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}