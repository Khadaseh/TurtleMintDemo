package com.turtlemint.turtlemintproject.view.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtil{

    companion object{
        // date conversion
        fun changeDateFormat(strDate: String?): String {
            if(strDate.isNullOrEmpty()){
               return ""
            }
            return try{
                val sourceSdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                val requiredSdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
                requiredSdf.format(sourceSdf.parse(strDate)!!)
            }catch (ex: Exception){
                ""
            }
        }
    }
}

