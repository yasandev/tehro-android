package dev.yasan.metro.tehran.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.yasan.metro.tehran.R
import dev.yasan.metro.tehran.ui.composable.screen.MainViewModel
import dev.yasan.metro.tehran.ui.composable.screen.about.AboutScreen
import dev.yasan.metro.tehran.ui.composable.screen.about.AboutViewModel
import dev.yasan.metro.tehran.ui.composable.screen.home.HomeScreen
import dev.yasan.metro.tehran.ui.composable.screen.line.LineScreen
import dev.yasan.metro.tehran.ui.composable.screen.line.LineViewModel
import dev.yasan.metro.tehran.ui.composable.screen.map.MapScreen
import dev.yasan.metro.tehran.ui.composable.screen.map.MapViewModel
import dev.yasan.metro.tehran.ui.composable.screen.station.StationScreen
import dev.yasan.metro.tehran.ui.composable.screen.station.StationViewModel
import dev.yasan.metro.tehran.ui.theme.themePrimary

/**
 * The main navigation graph for Tehro.
 *
 * @param startDestination The destination route that the app starts with.
 *
 * @see NavRoutes
 */
@ExperimentalMaterialNavigationApi
@ExperimentalAnimationApi
@Composable
fun NavGraph(
    startDestination: String = NavRoutes.routeHome()
) {

    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberAnimatedNavController(bottomSheetNavigator)
    val systemUiController = rememberSystemUiController()
        .apply { setNavigationBarColor(color = colorResource(id = R.color.layer_background)) }

    val mainViewModel: MainViewModel = hiltViewModel()

    ModalBottomSheetLayout(bottomSheetNavigator) {
        AnimatedNavHost(
            navController = navController,
            startDestination = startDestination,
            enterTransition = { _, _ ->
                expandIn()
            },
            popEnterTransition = { _, _ ->
                expandIn()
            },
            exitTransition = { _, _ ->
                fadeOut()
            },
            popExitTransition = { _, _ ->
                fadeOut()
            },
        ) {

            composable(route = NavRoutes.routeHome()) {

                systemUiController.setStatusBarColor(color = themePrimary)

                HomeScreen(
                    mainViewModel = mainViewModel,
                    navController = navController
                )
            }

            composable(
                route = NavRoutes.routeLineBase(),
                arguments = listOf(
                    navArgument(NavRoutes.EXTRA_LINE_ID) {
                        type = NavType.IntType
                    },
                )
            ) {

                val lineViewModel: LineViewModel = hiltViewModel(it)

                val lineId =
                    it.arguments?.getInt(NavRoutes.EXTRA_LINE_ID) ?: 0

                val line = mainViewModel.getLineById(lineId = lineId)

                systemUiController.setStatusBarColor(color = line?.color ?: Color.DarkGray)

                LineScreen(
                    lineViewModel = lineViewModel,
                    navController = navController,
                    line = line
                )
            }

            composable(route = NavRoutes.routeMap()) {

                systemUiController.setStatusBarColor(color = themePrimary)

                val mapViewModel: MapViewModel = hiltViewModel(it)

                MapScreen(mapViewModel = mapViewModel)
            }

            composable(
                route = NavRoutes.routeAbout(),
            ) {

                val aboutViewModel: AboutViewModel = hiltViewModel(it)

                AboutScreen(aboutViewModel = aboutViewModel)
            }

            bottomSheet(
                route = NavRoutes.routeStationBase(),
                arguments = listOf(
                    navArgument(NavRoutes.EXTRA_STATION_ID) {
                        type = NavType.IntType
                    },
                )
            ) {

                val stationId =
                    it.arguments?.getInt(NavRoutes.EXTRA_STATION_ID) ?: 0

                val stationViewModel: StationViewModel = hiltViewModel(it)

                StationScreen(
                    stationViewModel = stationViewModel,
                    navController = navController,
                    stationId = stationId
                )
            }
        }
    }
}
