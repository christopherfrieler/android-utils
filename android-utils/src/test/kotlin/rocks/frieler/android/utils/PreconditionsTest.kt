package rocks.frieler.android.utils

import assertk.assert
import assertk.assertions.hasMessage
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import assertk.assertions.isSameAs
import org.junit.Test

class PreconditionsTest {
    @Test
    internal fun testCheckArgumentReturnsValidArgument() {
        val checkedArgument = Preconditions.checkArgument(this, { arg -> arg == this }, "invalid")

        assert(checkedArgument).isSameAs(this)
    }

    @Test
    internal fun testCheckArgumentThrowsIllegalArgumentExceptionForInvalidArgument() {
        val exception = assertk.catch {
            Preconditions.checkArgument(this, { arg -> arg != this }, "invalid")
        }

        assert(exception).isNotNull { theException ->
            theException.isInstanceOf(IllegalArgumentException::class)
            theException.hasMessage("invalid")
        }
    }

    @Test
    internal fun testCheckStatePassesFulfilledPrecondition() {
        Preconditions.checkState(true, "invalid")
    }

    @Test
    internal fun testCheckStateThrowsIllegalStateExceptionIfThePreconditionIsNotFulfilled() {
        val exception = assertk.catch {
            Preconditions.checkState(false, "invalid")
        }

        assert(exception).isNotNull { theException ->
            theException.isInstanceOf(IllegalStateException::class)
            theException.hasMessage("invalid")
        }
    }
}