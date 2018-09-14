package rocks.frieler.android.utils

/**
 * Utility object that provides useful [String]-operations.
 */
object Strings {

    /**
     * Checks if the given [String] is `null` or [empty][CharSequence.isEmpty].
     *
     * @param s the [String] to check
     * @return `true` if the given [String] is `null` or [empty][CharSequence.isEmpty], otherwise `false`
     */
    @JvmStatic fun isNullOrEmpty(s: String?): Boolean {
        return s == null || s.isEmpty()
    }
}
