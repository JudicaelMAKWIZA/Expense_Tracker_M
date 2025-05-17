package com.example.expensetracker.data

import com.example.expensetracker.data.dao.ExpenseDao
import com.example.expensetracker.data.dao.ExpenseRepository
import com.example.expensetracker.model.ExpenseEntity
import kotlinx.coroutines.flow.Flow

class ExpenseRepositoryImpl(private val dao: ExpenseDao) : ExpenseRepository {
    override fun getAllExpenses(): Flow<List<ExpenseEntity>> = dao.getAllExpenses()
}
