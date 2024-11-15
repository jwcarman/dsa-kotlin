package dsa.tree.binary.avl

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class AvlEmptyNodeTest {

    @Test
    fun `left should return self`() {
        val node = AvlEmptyNode<Int>()
        node.left shouldBe node
    }

    @Test
    fun `right should return self`() {
        val node = AvlEmptyNode<Int>()
        node.right shouldBe node
    }

    @Test
    fun `should have size 0`() {
        val node = AvlEmptyNode<Int>()
        node.size shouldBe 0
    }

    @Test
    fun `should have height 0`() {
        val node = AvlEmptyNode<Int>()
        node.height shouldBe 0
    }

    @Test
    fun `should not contain any element`() {
        val node = AvlEmptyNode<Int>()
        node.contains(1, Comparator.naturalOrder()) shouldBe false
    }

    @Test
    fun `accessing value should throw exception`() {
        val node = AvlEmptyNode<Int>()
        shouldThrow<NoSuchElementException> { node.value }
    }

}