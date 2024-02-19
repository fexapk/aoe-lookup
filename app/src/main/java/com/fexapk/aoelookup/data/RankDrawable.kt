package com.fexapk.aoelookup.data

import androidx.annotation.DrawableRes
import com.fexapk.aoelookup.R

object RankDrawable {
    private val rankDrawableMap: Map<String, Int> = mapOf(
        "conqueror_3" to R.drawable.conqueror_three,
        "conqueror_2" to R.drawable.conqueror_two,
        "conqueror_1" to R.drawable.conqueror_one,
        "diamond_3" to R.drawable.diamond_three,
        "diamond_2" to R.drawable.diamond_two,
        "diamond_1" to R.drawable.diamond_one,
        "platinum_3" to R.drawable.platinum_three,
        "platinum_2" to R.drawable.platinum_two,
        "platinum_1" to R.drawable.platinum_one,
        "gold_3" to R.drawable.gold_three,
        "gold_2" to R.drawable.gold_two,
        "gold_1" to R.drawable.gold_one,
        "silver_3" to R.drawable.silver_three,
        "silver_2" to R.drawable.silver_two,
        "silver_1" to R.drawable.silver_one,
        "bronze_3" to R.drawable.bronze_three,
        "bronze_2" to R.drawable.bronze_two,
        "bronze_1" to R.drawable.bronze_one,
    )

    @DrawableRes
    fun getRankDrawable(rank: String) : Int {
        val drawable = rankDrawableMap.get(rank)
        return if (drawable == null) R.drawable.no_rank else drawable
    }
}