package com.example.taskflow_tf.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Event(
    val id: Long = System.currentTimeMillis(),
    val title: String,
    val startTime: String,
    val endTime: String,
    val date: String,
    val location: String? = null,
    val email: String? = null,
    val note: String? = null,
    val isCompleted: Boolean = false
) : Parcelable

