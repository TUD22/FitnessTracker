package com.example.fitnesstracker

import android.content.Context
import com.google.gson.GsonBuilder
import java.io.File

object JsonManager {
    private const val FILE_NAME = "trening_data.json"

    fun savetreningListToJson(context: Context, treningList: List<Trening>) {
        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonString = gson.toJson(treningList)
        val file = File(context.filesDir, FILE_NAME)


        file.writeText(jsonString)
    }
}