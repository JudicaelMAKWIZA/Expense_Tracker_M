// File: HomeViewModel.kt
package com.example.expensetracker.feature_ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.HomeUiState
import com.example.expensetracker.data.dao.ExpenseRepository
import com.example.expensetracker.model.ExpenseEntity
import com.example.expensetracker.utils.ExpenseType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: ExpenseRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        viewModelScope.launch {
            repository.getAllExpenses().collect { list ->
                _uiState.value = HomeUiState(
                    expenses = list,
                    totalIncome = calculateTotalByType(list, ExpenseType.INCOME),
                    totalExpense = calculateTotalByType(list, ExpenseType.EXPENSE),
                    balance = calculateBalance(list)
                )
            }
        }
    }

    fun calculateTotalByType(list: List<ExpenseEntity>, type: ExpenseType): String {
        val total = list
            .filter { it.type == type.value }
            .sumOf { it.amount }
        return "CDF $total"
    }

    fun calculateBalance(list: List<ExpenseEntity>): String {
        val total = list.sumOf {
            if (it.type == ExpenseType.INCOME.value) it.amount else -it.amount
        }
        return "CDF $total"
    }
}
