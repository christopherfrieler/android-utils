package rocks.frieler.android.utils

/**
 * Utility object that provides functions to check function-arguments.
 */
object Preconditions {

    /**
     * Returns the given argument if it passes the check. Otherwise a [IllegalArgumentException] with the given message
     * is thrown.
     *
     * @param argument the argument to check
     * @param check the check to perform on the argument
     * @param message the error-message, if the argument fails the check
     * @return the argument, if it passes the check
     * @throws IllegalArgumentException if the argument fails the check
     */
    @JvmStatic fun <T> checkArgument(argument: T, check: (T) -> Boolean, message: String): T {
        if (check.invoke(argument)) {
            return argument
        } else {
            throw IllegalArgumentException(message)
        }
    }

    /**
     * Checks the given precondition (which should not depend on any arguments) and throws an [IllegalStateException]
     * if it is not fulfilled.
     *
     * @param precondition the precondition that must be `true`
     * @param message the error-message, if the precondition is not fulfilled
     * @throws IllegalStateException if the precondition is not fulfilled
     */
    @JvmStatic fun checkState(precondition: Boolean, message: String) {
        if (!precondition) {
            throw IllegalStateException(message)
        }
    }
}