package com.amir.dailyplanning.model.repository

import com.amir.dailyplanning.model.data.Data_Planing
import com.amir.dailyplanning.model.net.ApiService

class PlaningRepositoryIMP(

    private val apiService: ApiService

): PlaningRepository {
    override suspend fun getPlan(plan: String): Data_Planing {

        val result = apiService.dailyPlaning(plan)

        if (result.status == 200) {

            return result

        } else {

            return Data_Planing(0, "", "", "", "")
        }
    }
}