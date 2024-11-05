package dsa.tree.binary.rbt

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.junit.jupiter.api.Test

class NilNodeTest {

    @Test
    fun `nil nodes should be black`() {
        val node = NilNode<String>()
        node.isRed shouldBe false
    }

    @Test
    fun `nil nodes should have size 0`() {
        val node = NilNode<String>()
        node.size shouldBe 0
    }

    @Test
    fun `nil nodes should be empty`() {
        val node = NilNode<String>()
        node.isEmpty shouldBe true
    }

    @Test
    fun `nil nodes should return empty iterator`() {
        val node = NilNode<String>()
        node.iterator().hasNext() shouldBe false
    }

    @Test
    fun `nil nodes should return self for left`() {
        val node = NilNode<String>()
        node.left shouldBeSameInstanceAs node
    }

    @Test
    fun `nil nodes should return self for right`() {
        val node = NilNode<String>()
        node.right shouldBeSameInstanceAs node
    }

    @Test
    fun `nil nodes should return self when asked to convert to black`() {
        val node = NilNode<String>()
        node.toBlack() shouldBeSameInstanceAs node
    }

    @Test
    fun `nil nodes should not support accessing their value`() {
        val node = NilNode<String>()
        shouldThrow<NoSuchElementException> {
            node.value
        }
    }

    @Test
    fun `adding to nil node should return red node`() {
        val node = NilNode<String>()
            .insert("foo", Comparator.naturalOrder())
        node.isRed shouldBe true
        node.value shouldBe "foo"
    }

    @Test
    fun `black height of nil node should be zero`() {
        val node = NilNode<String>()
        node.blackHeight shouldBe 0
    }
}