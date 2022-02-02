package me.runchi.calllogwidget.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import me.runchi.calllogwidget.R

class CallLogListWidgetProvider : AppWidgetProvider() {
    companion object {
        const val LIST_VIEW_ID = R.id.list_view
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val intent = Intent(context, CallLogListWidgetService::class.java).apply {
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            data = Uri.parse(toUri(Intent.URI_INTENT_SCHEME))
        }

        val views = RemoteViews(context.packageName, R.layout.call_log_list_widget).apply {
            setRemoteAdapter(LIST_VIEW_ID, intent)
            setEmptyView(LIST_VIEW_ID, R.id.empty_view)
        }

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}