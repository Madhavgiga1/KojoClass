package com.example.reater.utils

import android.content.Context
import com.example.reater.utils.Constants.Companion.PREFS_TOKEN_FILE
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager@Inject constructor(@ApplicationContext context:Context) {
    private var prefs=context.getSharedPreferences(PREFS_TOKEN_FILE,Context.MODE_PRIVATE)
}