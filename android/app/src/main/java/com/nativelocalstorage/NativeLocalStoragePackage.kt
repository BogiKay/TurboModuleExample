package com.nativelocalstorage

import android.view.View
import com.facebook.react.TurboReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.model.ReactModuleInfo
import com.facebook.react.module.model.ReactModuleInfoProvider
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager

class NativeLocalStoragePackage : TurboReactPackage() {
    override fun getModule(name: String, reactContext: ReactApplicationContext): NativeModule? {
        if (name == NativeLocalStorageModule.NAME) {
            return NativeLocalStorageModule(reactContext)
        } else if (name == ReactWebViewManager.REACT_CLASS) {
            return ReactWebViewManager(reactContext)
        } else {
            return null
        }
    }

    override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> {
        return listOf(ReactWebViewManager(reactContext))
    }


    override fun getReactModuleInfoProvider(): ReactModuleInfoProvider {
        return ReactModuleInfoProvider {
            mapOf(
                NativeLocalStorageModule.NAME to ReactModuleInfo(
                    _name = NativeLocalStorageModule.NAME,
                    _className = NativeLocalStorageModule.NAME,
                    _canOverrideExistingModule = false,
                    _needsEagerInit = false,
                    isCxxModule = false,
                    isTurboModule = true
                ),
                ReactWebViewManager.REACT_CLASS to ReactModuleInfo(
                    _name = ReactWebViewManager.REACT_CLASS,
                    _className = ReactWebViewManager.REACT_CLASS,
                    _canOverrideExistingModule = false,
                    _needsEagerInit = false,
                    isCxxModule = false,
                    isTurboModule = true
                )
            )
        }
    }
}