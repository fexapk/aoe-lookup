package com.fexapk.aoelookup.model

import com.google.gson.annotations.SerializedName

data class Leaderboards(
    @SerializedName("rm_solo") val rmSolo: MatchInfo? = null,
    @SerializedName("rm_team") val rmTeam: MatchInfo? = null,
    @SerializedName("rm_1v1_elo") val rm1v1Elo: MatchInfo? = null,
    @SerializedName("rm_2v2_elo") val rm2v2Elo: MatchInfo? = null,
    @SerializedName("rm_3v3_elo") val rm3v3Elo: MatchInfo? = null,
    @SerializedName("rm_4v4_elo") val rm4v4Elo: MatchInfo? = null,
    @SerializedName("qm_1v1") val qm1v1: MatchInfo? = null,
    @SerializedName("qm_2v2") val qm2v2: MatchInfo? = null,
    @SerializedName("qm_3v3") val qm3v3: MatchInfo? = null,
    @SerializedName("qm_4v4") val qm4v4: MatchInfo? = null,

)