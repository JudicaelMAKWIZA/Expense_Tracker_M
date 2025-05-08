package com.example.expensetracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.expensetracker.data.dao.ExpenseDao
import com.example.expensetracker.model.ExpenseEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [ExpenseEntity::class], version = 1)

abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    companion object {
        const val DATABASE_NAME = "expense_database"

        @JvmStatic
        fun getDatabase(context: Context): ExpenseDatabase {
            return Room.databaseBuilder(
                context,
                ExpenseDatabase::class.java,
                DATABASE_NAME
            ).addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    InitBasicData(context)
                }
                fun InitBasicData(context: Context){
                    CoroutineScope(Dispatchers.IO).launch {
                        val dao = getDatabase(context).expenseDao()
                        dao.insertExpense(ExpenseEntity(1, "Salaire", 500000.40, System.currentTimeMillis(), "Salaire", "Solde"))
                        dao.insertExpense(ExpenseEntity(2, "Paypal", 20000.50, System.currentTimeMillis(), "Paypal", "Solde"))
                        dao.insertExpense(ExpenseEntity(3, "Netflix", 1000.43, System.currentTimeMillis(), "Netflix", "Dépense"))
                        dao.insertExpense(ExpenseEntity(4, "Gameroom", 4000.56, System.currentTimeMillis(), "Gameroom", "Dépense"))

                    }
                }
            })
                .build()
        }
    }
}
