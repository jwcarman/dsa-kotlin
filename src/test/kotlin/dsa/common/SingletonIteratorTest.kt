package dsa.common

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class SingletonIteratorTest {
    @Test
    fun hasNextShouldBeTrueUponCreation() {
        val iterator = SingletonIterator(1)
        iterator.hasNext() shouldBe true
    }

    @Test
    fun nextShouldReturnSingletonValue() {
        val iterator = SingletonIterator(1)
        iterator.next() shouldBe 1
    }

    @Test
    fun hasNextShouldBeFalseAfterNext() {
        val iterator = SingletonIterator(1)
        iterator.next()
        iterator.hasNext() shouldBe false
    }
}