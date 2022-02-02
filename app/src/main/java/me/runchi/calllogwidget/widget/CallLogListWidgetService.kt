package me.runchi.calllogwidget.widget

import android.content.Intent
import android.widget.RemoteViewsService

class CallLogListWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return CallLogListWidgetViewsFactory(this.applicationContext)
    }
}

