package com.example.syncmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.syncmaster.ui.destinations.NavManager
import com.example.syncmaster.ui.theme.SyncMasterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SyncMasterTheme {
               NavManager()
            }
        }
    }
}
