@Serializable
data class OrbConfig(
    val sizeDp: Int = 48,
    val opacity: Float = 0.85f,
    val is3D: Boolean = true,
    val primaryColor: Long = 0xFF2196F3,
    val secondaryColor: Long = 0xFF00BCD4,
    val animationQuality: AnimationQuality = AnimationQuality.BALANCED
)

enum class AnimationQuality { LOW, BALANCED, HIGH }

@Serializable
data class GestureSlot(
    val id: Int,
    val gestureType: GestureType,
    val action: Action
)

enum class GestureType {
    SINGLE_TAP, DOUBLE_TAP, LONG_PRESS,
    SWIPE_UP, SWIPE_DOWN, SWIPE_LEFT, SWIPE_RIGHT,
    TWO_FINGER_TAP
}

@Serializable
sealed class Action {
    data class LaunchApp(val packageName: String, val activityName: String? = null, val deepLink: String? = null) : Action()
    data class SystemCommand(val command: SystemCommandType) : Action()
    data class ExecuteMacro(val macroId: String) : Action()
    data class PasteClipboard(val itemId: String? = null) : Action()
    data class QuickSetting(val tileName: String) : Action()
}

enum class SystemCommandType {
    BACK, HOME, RECENTS, NOTIFICATIONS, QUICK_SETTINGS,
    POWER_DIALOG, SCREENSHOT, LOCK_SCREEN, VOLUME_UP, VOLUME_DOWN,
    BRIGHTNESS_UP, BRIGHTNESS_DOWN
}

@Serializable
data class ClipboardItem(
    val id: String,
    val text: String,
    val timestamp: Long,
    val sourcePackage: String?,
    val label: String?
)

@Serializable
data class UserPreferences(
    val orbConfig: OrbConfig,
    val gestureSlots: List<GestureSlot>,
    val clipboardEnabled: Boolean = true,
    val clipboardTimeoutSeconds: Int = 60,
    val maxClipboardItems: Int = 50,
    val showNotification: Boolean = true,
    val highQualityMode: Boolean = false,
    val flyoutWidthDp: Int = 320
)

@Serializable
data class Macro(
    val id: String,
    val name: String,
    val steps: List<MacroStep>
)

@Serializable
sealed class MacroStep {
    data class ActionStep(val action: Action) : MacroStep()
    data class Delay(val millis: Int) : MacroStep()
    data class Condition(val conditionType: ConditionType, val value: String, val thenSteps: List<MacroStep>, val elseSteps: List<MacroStep> = emptyList()) : MacroStep()
}

enum class ConditionType {
    CURRENT_APP_IS, CLIPBOARD_CONTAINS, BATTERY_ABOVE, TIME_BETWEEN, WIFI_CONNECTED
}