package me.runchi.calllogwidget.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import me.runchi.calllogwidget.function.updateWidgets

class PhoneStateChangedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            val newState = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
            if (newState == TelephonyManager.EXTRA_STATE_IDLE) {
                updateWidgets(context)
            }
        }
    }

}

