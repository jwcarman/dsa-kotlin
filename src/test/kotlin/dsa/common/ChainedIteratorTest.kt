package dsa.common

import dsa.common.iterator.ChainedIterator
import dsa.common.iterator.SingletonIterator
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ChainedIteratorTest {
    @Test
    fun hasNextShouldBeFalseWithNoIterators() {
        val iterator = ChainedIterator<Int>()
        iterator.hasNext() shouldBe false
    }

    @Test
    fun nextShouldThrowNoSuchElementExceptionWithNoIterators() {
        val iterator = ChainedIterator<Int>()
        shouldThrow<NoSuchElementException> { iterator.next() }
    }

    @Test
    fun nextShouldReturnAllValues() {
        val iterator = ChainedIterator(SingletonIterator(1), SingletonIterator(2))
        iterator.hasNext() shouldBe true
        iterator.next() shouldBe 1
        iterator.hasNext() shouldBe true
        iterator.next() shouldBe 2
        iterator.hasNext() shouldBe false
    }
}