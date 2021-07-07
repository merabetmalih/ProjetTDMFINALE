package com.example.projettdmubunto

import android.content.Context
import android.widget.Toast
import com.opencsv.CSVReader
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.InputStreamReader


object Utils {
    fun getAllDatabase(context:Context):List<Ayah>{
        val ayas = mutableListOf<Ayah>()
        try {
            val csvfile = InputStreamReader(context.assets.open("dataa.csv"))
            val csvfilereader = BufferedReader(csvfile)
            var nextLine= csvfilereader.readLine()
            nextLine= csvfilereader.readLine()
            while (nextLine!= null) {
                val data = nextLine.split(',')
                ayas.add(Ayah(data[1].toInt(),
                data[2],data[3].toLong(), data[4], data[5].toInt(), data[6].toInt(),data[7], data[8],
                data[9]))
                nextLine= csvfilereader.readLine()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ayas
    }
}