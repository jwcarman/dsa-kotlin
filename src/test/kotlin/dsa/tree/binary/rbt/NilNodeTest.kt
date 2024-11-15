package dsa.tree.binary.rbt

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class NilNodeTest {
    @Test
    fun `should not be red`() {
        val node = NilNode<Int>()
        node.isRed() shouldBe false
    }

    @Test
    fun `should not contain any element`() {
        val node = NilNode<Int>()
        node.contains(1) shouldBe false
    }

    @Test
    fun `should have size 0`() {
        val node = NilNode<Int>()
        node.size shouldBe 0
    }

    @Test
    fun `left should return self`() {
        val node = NilNode<Int>()
        node.left shouldBe node
    }

    @Test
    fun `right should return self`() {
        val node = NilNode<Int>()
        node.right shouldBe node
    }

    @Test
    fun `should be nil`() {
        val node = NilNode<Int>()
        node.isNil() shouldBe true
    }

    @Test
    fun `should return black height 0`() {
        val node = NilNode<Int>()
        node.blackHeight shouldBe 0
    }

    @Test
    fun `should return black color`() {
        val node = NilNode<Int>()
        node.color shouldBe RedBlackNode.Color.BLACK
    }

    @Test
    fun `should throw exception when value is accessed`() {
        val node = NilNode<Int>()
        shouldThrow<NoSuchElementException> { node.value }
    }
}