package com.fexapk.aoelookup.model

import com.google.gson.annotations.SerializedName

data class Leaderboards(
    @SerializedName("rm_solo") val rmSolo: MatchData? = null,
    @SerializedName("rm_team") val rmTeam: MatchData? = null,
    @SerializedName("rm_2v2_elo") val rm2v2Elo: MatchData? = null,
    @SerializedName("rm_3v3_elo") val rm3v3Elo: MatchData? = null,
    @SerializedName("rm_4v4_elo") val rm4v4Elo: MatchData? = null,
    @SerializedName("qm_1v1") val qm1v1: MatchData? = null,
    @SerializedName("qm_2v2") val qm2v2: MatchData? = null,
    @SerializedName("qm_3v3") val qm3v3: MatchData? = null,
    @SerializedName("qm_4v4") val qm4v4: MatchData? = null,
    )