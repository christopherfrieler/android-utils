package rocks.frieler.android.utils

import assertk.assert
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.Test

class StringsTest {
    @Test
    internal fun testIsNullOrEmptyReturnsTrueForNull() {
        assert(Strings.isNullOrEmpty(null)).isTrue()
    }

    @Test
    internal fun testIsNullOrEmptyReturnsTrueForEmptyString() {
        assert(Strings.isNullOrEmpty("")).isTrue()
    }

    @Test
    internal fun testIsNullOrEmptyReturnsFalseForNonEmptyString() {
        assert(Strings.isNullOrEmpty("abc")).isFalse()
    }

    @Test
    internal fun testRepeatProducesRepetitionOfString() {
        val repeated = Strings.repeat("hello", 3)

        assert(repeated).isEqualTo("hello".repeat(3))
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
