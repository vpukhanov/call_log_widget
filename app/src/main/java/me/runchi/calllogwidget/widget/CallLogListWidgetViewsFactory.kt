package me.runchi.calllogwidget.widget

import android.content.Context
import android.provider.CallLog
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import me.runchi.calllogwidget.R
import me.runchi.calllogwidget.model.Call

class CallLogListWidgetViewsFactory(
    private val context: Context
) : RemoteViewsService.RemoteViewsFactory {
    private var calls = mutableListOf<Call>()

    companion object {
        private const val REMOTE_VIEWS_COUNT = 50
    }

    override fun onCreate() {}

    override fun onDataSetChanged() {
        val cursor = context.contentResolver.query(
            CallLog.Calls.CONTENT_URI,
            Call.PROJECTION,
            null,
            null,
            "${CallLog.Calls.DATE} DESC"
        ) ?: return
        var count = 0

        calls.clear()

        while (cursor.moveToNext() && count < REMOTE_VIEWS_COUNT) {
            calls.add(Call(context, cursor))
            ++count
        }
    }

    override fun onDestroy() {}

    override fun getCount(): Int {
        return calls.count()
    }

    override fun getViewAt(position: Int): RemoteViews {
        val call = calls[position]
        val views = RemoteViews(context.packageName, R.layout.call_log_list_item)

        views.setTextViewText(R.id.phone_text, call.name ?: call.number)
        views.setTextViewText(R.id.time_text, call.displayDate)
        views.setImageViewResource(R.id.type_icon, call.type.drawable)

        return views
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(position: Int): Long {
        val call = calls[position]
        return call.date.time
    }

    override fun hasStableIds(): Boolean {
        return true
    }
}