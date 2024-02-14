package com.fexapk.aoelookup.model

import com.google.gson.annotations.SerializedName

data class Leaderboards(
    @SerializedName("rm_solo") val rmSolo: RmSolo?
)