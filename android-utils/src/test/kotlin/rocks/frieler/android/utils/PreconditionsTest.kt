package rocks.frieler.android.utils

import assertk.assertThat
import assertk.assertions.*
import org.junit.Test

class PreconditionsTest {
    @Test
    internal fun testCheckArgumentReturnsValidArgument() {
        val checkedArgument = Preconditions.checkArgument(this, { arg -> arg == this }, "invalid")

        assertThat(checkedArgument).isSameAs(this)
    }

    @Test
    internal fun testCheckArgumentThrowsIllegalArgumentExceptionForInvalidArgument() {
        assertThat {
            Preconditions.checkArgument(this, { arg -> arg != this }, "invalid")
        }

        .isFailure().let { exception ->
            exception.hasClass(IllegalArgumentException::class)
            exception.hasMessage("invalid")
        }
    }

    @Test
    internal fun testCheckStatePassesFulfilledPrecondition() {
        Preconditions.checkState(true, "invalid")
    }

    @Test
    internal fun testCheckStateThrowsIllegalStateExceptionIfThePreconditionIsNotFulfilled() {
        assertThat {
            Preconditions.checkState(false, "invalid")
        }

        .isFailure().let { exception ->
            exception.isInstanceOf(IllegalStateException::class)
            exception.hasMessage("invalid")
        }
    }
}