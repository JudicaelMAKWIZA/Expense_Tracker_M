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

@Database(entities = [ExpenseEntity::class], version = 2, exportSchema = false)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao

    companion object {
        const val DATABASE_NAME = "expense_database"

        @Volatile // Important pour la visibilité entre les threads
        private var INSTANCE: ExpenseDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): ExpenseDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, // Utilisez applicationContext pour éviter les fuites de mémoire
                    ExpenseDatabase::class.java,
                    DATABASE_NAME
                )
                    // Solution pour la migration : Migration Destructive
                    // Ceci supprimera la base de données si une migration n'est pas trouvée.
                    // Utile en développement, mais attention en production car cela efface les données.
                    .fallbackToDestructiveMigration()
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Remplir la base de données avec des données initiales UNIQUEMENT lors de la création.
                            // Nous lançons cela dans une coroutine pour ne pas bloquer le thread principal.
                            // Il est crucial d'utiliser l'instance de la base de données qui vient d'être créée.
                            // Nous pouvons accéder à `INSTANCE` ici car il sera assigné juste après le .build()
                            // avant que la méthode `getDatabase` ne retourne.
                            // Ou, de manière plus sûre, passer l'instance au moment de l'appel à init.
                            INSTANCE?.let { database -> // Assurez-vous que INSTANCE est non null
                                CoroutineScope(Dispatchers.IO).launch {
                                    initBasicData(database.expenseDao())
                                }
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance // retourne l'instance nouvellement créée et assignée à INSTANCE
            }
        }

        // Méthode d'initialisation des données de base
        // Prend le Dao en paramètre pour éviter de rappeler getDatabase() de manière incorrecte
        private suspend fun initBasicData(dao: ExpenseDao) {
            dao.insertExpense(ExpenseEntity(0, "Salaire", 500000.40, System.currentTimeMillis().toString(), "Salaire", "Solde"))
            dao.insertExpense(ExpenseEntity(0, "Paypal", 20000.50, System.currentTimeMillis().toString(), "Paypal", "Solde"))
            dao.insertExpense(ExpenseEntity(0, "Netflix", 1000.43, System.currentTimeMillis().toString(), "Netflix", "Dépense"))
            dao.insertExpense(ExpenseEntity(0, "Gameroom", 4000.56, System.currentTimeMillis().toString(), "Gameroom", "Dépense"))
            // Note : J'ai changé les IDs à 0 pour que Room les auto-génère si votre `id` dans ExpenseEntity est `@PrimaryKey(autoGenerate = true)`.
            // Si vous gérez les IDs manuellement et qu'ils doivent être uniques, assurez-vous de votre logique d'ID.
        }
    }
}