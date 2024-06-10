package ru.mirea.udalov.employeedb

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mirea.udalov.employeedb.EmployeeDao
import ru.mirea.udalov.employeedb.Employee

@Database(entities = [Employee::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao?
}