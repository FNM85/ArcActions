package com.example.arcactions.service

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class ArcAccessibilityService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // Handle accessibility events
    }

    override fun onInterrupt() {
        // Handle service interruption
    }
}
