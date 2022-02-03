package me.runchi.calllogwidget.model

import android.content.Context
import android.database.Cursor
import android.provider.CallLog
import androidx.core.database.getStringOrNull
import androidx.preference.PreferenceManager
import net.danlew.android.joda.DateUtils
import org.joda.time.DateTime
import java.util.*

class Call(private val context: Context, cursor: Cursor) {
    companion object {
        val PROJECTION = arrayOf(
            CallLog.Calls.CACHED_NAME,
            CallLog.Calls.NUMBER,
            CallLog.Calls.TYPE,
            CallLog.Calls.DATE,
        )
    }

    val name: String? = cursor.getStringOrNull(0)

    val number: String = cursor.getString(1)
    val type: CallType = CallType.parseInt(cursor.getInt(2))
    val date: Date = Date(cursor.getLong(3))

    val displayDate: String
        get() {
            return DateUtils.getRelativeTimeSpanString(context, DateTime(date.time)).toString()
        }

    fun satisfiesPreferences(): Boolean {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return when (type) {
            CallType.OUTGOING -> preferences.getBoolean("outgoing", true)
            CallType.RECEIVED -> preferences.getBoolean("received", true)
            CallType.MISSED -> preferences.getBoolean("missed", true)
            CallType.REJECTED -> preferences.getBoolean("rejected", false)
            CallType.BLOCKED -> preferences.getBoolean("blocked", false)
            CallType.VOICEMAIL -> preferences.getBoolean("voicemail", false)
            else -> false
        }
    }
}