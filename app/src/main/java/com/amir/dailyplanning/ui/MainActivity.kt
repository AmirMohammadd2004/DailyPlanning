package com.amir.dailyplanning.ui

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amir.dailyplanning.di.myModules
import com.amir.dailyplanning.them.BaseMvvmTheme
import com.amir.dailyplanning.ui.features.home.HomeScreen
import com.amir.dailyplanning.util.MyScreen
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.navigation.KoinNavHost
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR

        setContent {

            Koin(appDeclaration = {

                androidContext(this@MainActivity)
                modules(myModules)
            }) {

                BaseMvvmTheme {

                    UiApp()

                }


            }
        }
    }


    @Composable
    private fun UiApp() {

        val navigation = rememberNavController()

        KoinNavHost(
            navController =  navigation ,
            startDestination = MyScreen.Home.rote
        ){


            composable(
                route = MyScreen.Home.rote
            ) {

                HomeScreen()
            }



        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BaseMvvmTheme {
    }
}