package com.yasiraliraj.ricediseasedetection.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.yasiraliraj.ricediseasedetection.util.LocaleHelper

open class BaseActivity : AppCompatActivity() {
    override fun attachBaseContext(newBase: Context) {
        val lang = LocaleHelper.getSavedLanguage(newBase)
        super.attachBaseContext(LocaleHelper.setLocale(newBase, lang))
    }
}
