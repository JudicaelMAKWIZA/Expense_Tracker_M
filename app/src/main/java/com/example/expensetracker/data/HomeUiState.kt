package com.example.expensetracker.data

import com.example.expensetracker.model.ExpenseEntity

data class HomeUiState(
    val expenses: List<ExpenseEntity> = emptyList(),
    val totalIncome: String = "CDF 0.0",
    val totalExpense: String = "CDF 0.0",
    val balance: String = "CDF 0.0"
)
