package dsa.common

import dsa.common.iterator.EmptyIterator
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class EmptyIteratorTest {
    @Test
    fun shouldNotHaveNext() {
        val iterator = EmptyIterator<Int>()
        iterator.hasNext() shouldBe false
    }

    @Test
    fun nextShouldThrowNoSuchElementException() {
        val iterator = EmptyIterator<Int>()
        shouldThrow<NoSuchElementException> { iterator.next() }
    }
}