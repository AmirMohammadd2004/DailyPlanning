package com.amir.dailyplanning.model.repository

import com.amir.dailyplanning.model.data.Data_Planing

interface PlaningRepository {


    suspend fun getPlan(plan: String): Data_Planing

}