package me.runchi.calllogwidget.receiver

import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import me.runchi.calllogwidget.widget.CallLogListWidgetProvider

class PhoneStateChangedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            val newState = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
            if (newState == TelephonyManager.EXTRA_STATE_IDLE) {
                updateWidgets(context)
            }
        }
    }

    private fun updateWidgets(context: Context) {
        val manager = AppWidgetManager.getInstance(context)
        val ids = manager.getAppWidgetIds(ComponentName(context.applicationContext, CallLogListWidgetProvider::class.java))
        manager.notifyAppWidgetViewDataChanged(ids, CallLogListWidgetProvider.LIST_VIEW_ID)
    }
}