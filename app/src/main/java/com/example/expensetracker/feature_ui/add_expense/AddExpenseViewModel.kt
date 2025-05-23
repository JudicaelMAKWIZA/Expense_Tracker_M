package com.example.expensetracker.feature_ui.add_expense

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expensetracker.data.ExpenseDatabase
import com.example.expensetracker.data.dao.ExpenseDao
import com.example.expensetracker.feature_ui.home.HomeViewModel
import com.example.expensetracker.model.ExpenseEntity

class AddExpenseViewModel(val dao: ExpenseDao): ViewModel() {
    suspend fun  addExpense(expenseEntity: ExpenseEntity) : Boolean{
        return try {
            dao.insertExpense(expenseEntity)
            return true
        }
        catch(ex: Throwable){
            return false

        }
    }
}

class AddExpenseViewModelFactory(private val context: Context) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddExpenseViewModel::class.java)) {
            val dao = ExpenseDatabase.getDatabase(context).expenseDao()
            @Suppress("UNCHECKED_CAST")
            return AddExpenseViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}