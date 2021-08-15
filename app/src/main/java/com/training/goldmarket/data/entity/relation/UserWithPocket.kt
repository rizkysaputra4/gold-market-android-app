package com.training.goldmarket.data.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.training.goldmarket.data.entity.Pocket
import com.training.goldmarket.data.entity.User

data class UserWithPocket(
    @Embedded
    val user: User,

    @Relation(
        parentColumn = "pocketId",
        entityColumn = "pocketOwnerId"
    )
    val pockets: List<Pocket>
)
