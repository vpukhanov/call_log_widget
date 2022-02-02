package me.runchi.calllogwidget.model

import android.provider.CallLog
import androidx.annotation.DrawableRes
import me.runchi.calllogwidget.R

enum class CallType {
    OUTGOING,
    RECEIVED,
    MISSED,
    REJECTED,
    BLOCKED,
    VOICEMAIL,
    UNKNOWN;

    val drawable: Int
        @DrawableRes get() {
            return when (this) {
                OUTGOING -> R.drawable.ic_baseline_call_made_24
                RECEIVED -> R.drawable.ic_baseline_call_received_24
                MISSED -> R.drawable.ic_baseline_call_missed_24
                REJECTED -> R.drawable.ic_baseline_phone_missed_24
                BLOCKED -> R.drawable.ic_baseline_block_24
                VOICEMAIL -> R.drawable.ic_baseline_voicemail_24
                else -> R.drawable.ic_baseline_device_unknown_24
            }
        }

    companion object {
        fun parseInt(code: Int): CallType {
            return when (code) {
                CallLog.Calls.OUTGOING_TYPE -> OUTGOING
                CallLog.Calls.INCOMING_TYPE -> RECEIVED
                CallLog.Calls.MISSED_TYPE -> MISSED
                CallLog.Calls.REJECTED_TYPE -> REJECTED
                CallLog.Calls.BLOCKED_TYPE -> BLOCKED
                CallLog.Calls.VOICEMAIL_TYPE -> VOICEMAIL
                else -> UNKNOWN
            }
        }
    }
}