package com.example.bisnisproperti.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bisnisproperti.ui.view.jenis.*
import com.example.bisnisproperti.ui.view.manajer.*
import com.example.bisnisproperti.ui.view.pemilik.*
import com.example.bisnisproperti.ui.view.properti.*

@Composable
fun PengelolaHalaman(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHomeProperti.route,
        modifier = Modifier,
    ) {
        // Halaman Utama Properti
        composable(DestinasiHomeProperti.route) {
            HomePropertiView(
                navigateToInsertProperti = { navController.navigate(DestinasiInsertProperti.route) },
                onDetailClick = { idProperti ->
                    navController.navigate("${DestinasiDetailProperti.route}/$idProperti")
                }
            )
        }

        // Tambah Properti
        composable(DestinasiInsertProperti.route) {
            InsertPropertiView(navigateBack = {
                navController.navigate(DestinasiHomeProperti.route) {
                    popUpTo(DestinasiHomeProperti.route) {
                        inclusive = true
                    }
                }
            })
        }

        // Detail Properti
        composable(DestinasiDetailProperti.routeWithArg) { backStackEntry ->
            val idProperti = backStackEntry.arguments?.getString(DestinasiDetailProperti.idProperti) ?: ""
            DetailPropertiView(
                navigateBack = { navController.popBackStack() },
                onEditClick = { navController.navigate("${DestinasiUpdateProperti.route}/$idProperti") },
                navigateToJenis = { navController.navigate(DestinasiHomeJenis.route) },
                navigateToPemilik = { navController.navigate(DestinasiHomePemilik.route) },
                navigateToManajer = { navController.navigate(DestinasiHomeManajer.route) }
            )
        }

        // Edit Properti
        composable(DestinasiUpdateProperti.routeWithArg) { backStackEntry ->
            val idProperti = backStackEntry.arguments?.getString(DestinasiUpdateProperti.idProperti) ?: ""
            UpdatePropertiView(
                navigateBack = { navController.popBackStack() }
            )
        }

        // Halaman Jenis Properti
        composable(DestinasiHomeJenis.route) {
            HomeJenisView(
                navigateToInsertJenis = { navController.navigate(DestinasiInsertJenis.route) },
                onDetailClick = { idJenis ->
                    navController.navigate("${DestinasiDetailJenis.route}/$idJenis")
                }
            )
        }
        composable(DestinasiInsertJenis.route) {
            InsertJenisView(navigateBack = { navController.popBackStack() })
        }
        composable(DestinasiDetailJenis.routeWithArg) { backStackEntry ->
            val idJenis = backStackEntry.arguments?.getString(DestinasiDetailJenis.idJenis) ?: ""
            DetailJenisView(
                navigateBack = { navController.popBackStack() },
                onEditClick = { navController.navigate("${DestinasiUpdateJenis.route}/$idJenis") }
            )
        }
        composable(DestinasiUpdateJenis.routeWithArg) { backStackEntry ->
            val idJenis = backStackEntry.arguments?.getString(DestinasiUpdateJenis.idJenis) ?: ""
            UpdateJenisView(navigateBack = { navController.popBackStack() })
        }

        // Halaman Pemilik
        composable(DestinasiHomePemilik.route) {
            HomePemilikView(
                navigateToInsertPemilik = { navController.navigate(DestinasiInsertPemilik.route) },
                onDetailClick = { idPemilik ->
                    navController.navigate("${DestinasiDetailPemilik.route}/$idPemilik")
                }
            )
        }
        composable(DestinasiInsertPemilik.route) {
            InsertPemilikView(navigateBack = { navController.popBackStack() })
        }
        composable(DestinasiDetailPemilik.routeWithArg) { backStackEntry ->
            val idPemilik = backStackEntry.arguments?.getString(DestinasiDetailPemilik.idPemilik) ?: ""
            DetailPemilikView(
                navigateBack = { navController.popBackStack() },
                onEditClick = { navController.navigate("${DestinasiUpdatePemilik.route}/$idPemilik") }
            )
        }
        composable(DestinasiUpdatePemilik.routeWithArg) { backStackEntry ->
            val idPemilik = backStackEntry.arguments?.getString(DestinasiUpdatePemilik.idPemilik) ?: ""
            UpdatePemilikView(navigateBack = { navController.popBackStack() })
        }

        // Halaman Manajer
        composable(DestinasiHomeManajer.route) {
            HomeManajerView(
                navigateToInsertManajer = { navController.navigate(DestinasiInsertManajer.route) },
                onDetailClick = { idManajer ->
                    navController.navigate("${DestinasiDetailManajer.route}/$idManajer")
                }
            )
        }
        composable(DestinasiInsertManajer.route) {
            InsertManajerView(navigateBack = { navController.popBackStack() })
        }
        composable(DestinasiDetailManajer.routeWithArg) { backStackEntry ->
            val idManajer = backStackEntry.arguments?.getString(DestinasiDetailManajer.idManajer) ?: ""
            DetailManajerView(
                navigateBack = { navController.popBackStack() },
                onEditClick = { navController.navigate("${DestinasiUpdateManajer.route}/$idManajer") }
            )
        }
        composable(DestinasiUpdateManajer.routeWithArg) { backStackEntry ->
            val idManajer = backStackEntry.arguments?.getString(DestinasiUpdateManajer.idManajer) ?: ""
            UpdateManajerView(navigateBack = { navController.popBackStack() })
        }
    }
}