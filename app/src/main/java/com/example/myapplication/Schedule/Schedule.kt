package com.example.myapplication.Schedule


import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Schedule(xlWb: XSSFWorkbook) {
    companion object {
        var listAllPred = ArrayList<Predmet>() //Массив со всеми предметами
    }

    var hashPredmet = HashMap<String, ArrayList<Predmet>>()


    init {
        val n = xlWb.numberOfSheets - 1
        for (i in 0..n) {
            val xlSh = xlWb.getSheetAt(i)
            hashPredmet.put(xlSh.sheetName, predToList(xlSh))
        }



    }
    fun getShedlIsDate(group: String, date: Date):ArrayList<Predmet> {
        val newlist=ArrayList<Predmet>()
        val format="dd.MM"
        val dateFormat= SimpleDateFormat(format)
        val stringDate:String=dateFormat.format(date)
        getShedlIsGroup(group)?.forEach {
            if (it.date == stringDate)
                newlist.add(it)
        }
        return newlist
    }
    fun getShedlIsDateToTecher(nameTch:String, date:Date):ArrayList<Predmet> {
        val format = "dd.MM"
        val dateFormat = SimpleDateFormat(format)
        val stringDate: String = dateFormat.format(date)
        val newlist= arrayListOf<Predmet>()
        listAllPred.forEach {
            if (it.date == stringDate)
                if (it.nameTch == nameTch){
                    newlist.add(it)
                }
        }
        if(newlist.size!=0){
            val newlist2 = arrayListOf(
                Predmet("",stringDate,"","","9:00-10:30","",""),
                Predmet("",stringDate,"","","10:40-12:10","",""),
                Predmet("",stringDate,"","","12:20-13:50","",""),
                Predmet("",stringDate,"","","14:20-15:50","",""),
                Predmet("",stringDate,"","","16:00-17:30","",""),
                Predmet("",stringDate,"","","17:40-19:10","",""),
                Predmet("",stringDate,"","","19:20-20:50","","")

            )
            newlist.forEach{
                when(it.time){
                    "9:00-10:30"->newlist2[0]=it
                    "10:40-12:10"->newlist2[1]=it
                    "12:20-13:50"->newlist2[2]=it
                    "14:20-15:50"->newlist2[3]=it
                    "16:00-17:30"->newlist2[4]=it
                    "17:40-19:10"->newlist2[5]=it
                    "19:20-20:50"->newlist2[6]=it
                }
            }
            return newlist2
        }
        return newlist
    }
    fun getShedlIsTecher(nameTch:String):ArrayList<Predmet> {
        val newlist=ArrayList<Predmet>()
        listAllPred.forEach {
            if(it.nameTch==nameTch)
                newlist.add(it)
        }
        return newlist
    }
    fun getShedlIsGroup(group: String) = hashPredmet[group]
    private fun predToList(xlSh: Sheet?): ArrayList<Predmet> {
        val listPredmet = ArrayList<Predmet>()
        var i = 2
        var j = 2
        var iDate = 1
        var jDate = 2
        val group = xlSh?.sheetName

        var m = 1
        while (true) {
            val date: String? = xlSh?.getRow(iDate)?.getCell(jDate).toString()
            val name: String? = xlSh?.getRow(i)?.getCell(j).toString()              //название
            val prep: String? = xlSh?.getRow(i + 1)?.getCell(j).toString()  //Имя перподователя
            val day: String? = xlSh?.getRow(iDate)?.getCell(1).toString()   //день недели
            val time: String? = xlSh?.getRow(i)?.getCell(0).toString()      //время
            val cab:String?=xlSh?.getRow(i)?.getCell(j+1).toString()
            val predmet = Predmet(name ?: " ", date ?: " ", prep ?: " ", day ?: " ", time
                ?: " ", group ?: " ",cab ?: " ")
            listPredmet.add(predmet)
            if (m >= 7) {
                i += 1
                iDate += 15

                m = 0
                if (xlSh?.getRow(iDate)?.getCell(jDate) == null) {
                    i = 0
                    iDate = 1
                    jDate += 2
                    j += 2
                    if (xlSh?.getRow(iDate)?.getCell(jDate) == null) {
                        break
                    }
                }
            }
            i += 2
            m++
        }
        return listPredmet
    }

}