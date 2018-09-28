package rocks.frieler.android.utils

import assertk.assert
import assertk.assertions.isEqualTo
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

    @Test
    internal fun testJoinCreatesJoinedStringWithDefaultParameters() {
        val parts = ArrayList<String>()
        parts.add("foo")
        parts.add("bar")

        val joined = Strings.join(parts)

        assert(joined).isEqualTo("foobar")
    }

    @Test
    internal fun testJoinCreatesJoinedStringWithSeparatorPrefixPostfixAndUsingTransformer() {
        val parts = ArrayList<NamedThing>()
        parts.add(NamedThing("This"))
        parts.add(NamedThing("That"))

        val joined = Strings.join(parts, ", ", "[", "]", NamedThing::name)

        assert(joined).isEqualTo("[This, That]")
    }

    class NamedThing(val name: String) {
    }
}
