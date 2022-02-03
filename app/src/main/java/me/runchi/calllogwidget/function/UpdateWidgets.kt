package me.runchi.calllogwidget.function

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import me.runchi.calllogwidget.widget.CallLogListWidgetProvider

fun updateWidgets(context: Context) {
    val manager = AppWidgetManager.getInstance(context)
    val ids = manager.getAppWidgetIds(
        ComponentName(
            context.applicationContext,
            CallLogListWidgetProvider::class.java
        )
    )
    manager.notifyAppWidgetViewDataChanged(ids, CallLogListWidgetProvider.LIST_VIEW_ID)
}