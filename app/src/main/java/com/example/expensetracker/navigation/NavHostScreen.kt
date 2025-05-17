package com.example.expensetracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.feature_ui.add_expense.AddExpense
import com.example.expensetracker.feature_ui.home.HomeScreen

@Composable
fun NavHostScreen(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "/home"){
        composable(route = "/home"){
            HomeScreen(navController)
        }
        composable(route = "/add"){
            AddExpense(navController)
        }
    }
}