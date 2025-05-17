package com.example.expensetracker.utils

import android.icu.text.NumberFormat
import com.example.expensetracker.R
import com.example.expensetracker.model.ExpenseEntity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


object Utils {

    fun formatDateToHumanReadableForm(dateInMillis: Long): String {
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormatter.format(dateInMillis)
    }
    /*
    fun formatCurrency(amount: Double, locale: Locale = Locale.CDF): String {
        val currencyFormatter = NumberFormat.getCurrencyInstance(locale)
        return currencyFormatter.format(amount)
    }*/
    fun formatToDecimalValue(d: Double): String {
        return String.format("%. 2f", d)
    }
    fun formatDayMonthYear(dateInMillis: Long): String {
        val dateFormatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        return dateFormatter.format(dateInMillis)
    }
    fun formatStringDateToMonthDayYear(date: String): String {
        val millis = getMillisFromDate(date)
        return formatDayMonthYear(millis)
    }
    fun getMillisFromDate(date: String): Long {
        return getMilliFromDate(date)
    }
    fun getMilliFromDate(dateFormat: String?): Long {
        var date = Date()
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        try {
            date = formatter.parse(dateFormat)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        println("Today is $date")
        return date.time
    }
    fun getItemIcon(item: ExpenseEntity): Int{
        if (item.category == "Paypal") {
            return R.drawable.ic_paypal
        } else if (item.category == "Netflix") {
            return R.drawable.ic_netflix
        }
        else if (item.category == "Gameroom") {
            return R.drawable.game
        }
        return R.drawable.ic_upwork
    }

}


enum class ExpenseType(val value: String) {
    INCOME("Solde"),
    EXPENSE("Dépense")
}

