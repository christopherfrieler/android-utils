package rocks.frieler.android.utils

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StringsTest {
    @Test
    internal fun testIsNullOrEmptyReturnsTrueForNull() {
        assertTrue(Strings.isNullOrEmpty(null))
    }

    @Test
    internal fun testIsNullOrEmptyReturnsTrueForEmptyString() {
        assertTrue(Strings.isNullOrEmpty(""))
    }

    @Test
    internal fun testIsNullOrEmptyReturnsFalseForNonEmptyString() {
        assertFalse(Strings.isNullOrEmpty("abc"))
    }
}
