package com.example.cleanarchitecture.domin.model

import com.example.cleanarchitecture.data.remote.dto.TeamMember

data class CoinDetail(
    val coinId: String,
    val isActive: Boolean,
    val name: String,
    val description:String,
    val rank: Int,
    val symbol: String,
    val tags:List<String>,
    val team:List<TeamMember>
)
