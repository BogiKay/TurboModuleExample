package com.nativelocalstorage

import android.content.Context
import android.content.SharedPreferences
import com.nativelocalstorage.NativeLocalStorageSpec
import com.facebook.react.bridge.ReactApplicationContext


class NativeLocalStorageModule(reactContext: ReactApplicationContext) : NativeLocalStorageSpec(reactContext) {
    override fun getName(): String {
        return NAME
    }

    override fun setItem(value: String, key: String) {
        val sharedPref = getReactApplicationContext().getSharedPreferences("my_perfs", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    override fun getItem(key: String): String? {
        val sharedPref = reactApplicationContext.getSharedPreferences("my_perfs", Context.MODE_PRIVATE)
        val value = sharedPref.getString(key, null)
        return value.toString()
    }

    override fun removeItem(key: String?) {
        val sharedPerf = reactApplicationContext.getSharedPreferences("my_perfs", Context.MODE_PRIVATE)
        val editor = sharedPerf.edit()
        editor.remove(key)
        editor.apply()
    }

    override fun clear() {
        val sharedPerf = reactApplicationContext.getSharedPreferences("my_perfs", Context.MODE_PRIVATE)
        val editor = sharedPerf.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        const val NAME = "NativeLocalStorage"
    }
}