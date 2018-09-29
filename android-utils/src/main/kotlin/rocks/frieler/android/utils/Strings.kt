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

    /**
     * Returns the given [String] or `""` if it is `null`.
     *
     * @param s a [String] or `null`
     * @return the [String] or `""`
     */
    @JvmStatic fun nullToEmpty(s: String?): String {
        return s ?: ""
    }

    /**
     * Counts the occurrences of the `characters` in the given [String].
     *
     * @param s the [String] to search in
     * @param characters the [Char]s to search for
     * @return the number of occurrences of the `characters`
     */
    @JvmStatic fun count(s: String, vararg characters: Char): Int {
        return s.count { c -> c in characters }
    }

    /**
     * Returns a [String] containing the given [String] repeated `n` times.
     *
     * @param s the [String] to repeat
     * @param n the number of times to repeat `s`
     * @return a [String] containing the given [String] repeated `n` times
     * @see String.repeat
     */
    @JvmStatic fun repeat(s: String, n: Int): String {
        return s.repeat(n)
    }

    /**
     * Joins the given parts to one [String].
     *
     * You can optionally specify a prefix, a postfix and a separator.
     *
     * Additionally, if the parts are not [Strings](String) themselves, you can specify a transformer or their
     * [Any.toString]-method will be used.
     *
     * @param parts the parts to join
     * @param separator an optional separator to put between the parts
     * @param prefix an optional prefix to prepend to the [String]
     * @param postfix an optional postfix to append to the [String]
     * @param transformer an optional transformation-function to apply to the parts
     * @return a joined [String] of the given parts.
     */
    @JvmStatic @JvmOverloads fun <T> join(parts: Iterable<T>, separator: String = "", prefix: String = "", postfix: String = "", transformer: ((T) -> CharSequence)? = null): String {
        return parts.joinToString(separator, prefix, postfix, transform = transformer)
    }
}
