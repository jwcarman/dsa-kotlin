package dsa.tree.binary.rbt

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.junit.jupiter.api.Test

class BlackSearchTreeNodeTest {

    @Test
    fun `black node should be black`() {
        val node = BlackNode("foo")
        node.isBlack shouldBe true
    }

    @Test
    fun `black nodes should not be red`() {
        val node = BlackNode("foo")
        node.isRed shouldBe false
    }

    @Test
    fun `black nodes should not be empty`() {
        val node = BlackNode("foo")
        node.isEmpty shouldBe false
    }

    @Test
    fun `black nodes should not return empty iterator`() {
        val node = BlackNode("foo")
        node.iterator().hasNext() shouldBe true
    }

    @Test
    fun `black nodes should return self for to black`() {
        val node = BlackNode("foo")
        node.toBlack() shouldBeSameInstanceAs node
    }

    @Test
    fun `inserting exact value should return self`() {
        val node = BlackNode("foo")
        val newNode = node.insert("foo", Comparator.naturalOrder())
        newNode shouldBeSameInstanceAs node
    }

    @Test
    fun `should insert left child with no rotations`() {
        val node = BlackNode("foo")
            .insert("bar", Comparator.naturalOrder())
        node.isRed shouldBe false
        node.value shouldBe "foo"
        node.left.isRed shouldBe true
        node.left.value shouldBe "bar"
    }

    @Test
    fun `should insert right child with no rotations`() {
        val node = BlackNode("foo")
            .insert("zoo", Comparator.naturalOrder())
        node.isRed shouldBe false
        node.value shouldBe "foo"
        node.right.isRed shouldBe true
        node.right.value shouldBe "zoo"
    }

    @Test
    fun `should perform left-left rotation correctly`() {
        val node = BlackNode("c")
            .insert("b", Comparator.naturalOrder())
            .insert("a", Comparator.naturalOrder())
        node.isBlack shouldBe true
        node.value shouldBe "b"
        node.left.isRed shouldBe true
        node.left.value shouldBe "a"
        node.right.isRed shouldBe true
        node.right.value shouldBe "c"
    }

    @Test
    fun `should perform left-right rotation correctly`() {
        val node = BlackNode("c")
            .insert("a", Comparator.naturalOrder())
            .insert("b", Comparator.naturalOrder())
        node.isBlack shouldBe true
        node.value shouldBe "b"
        node.left.isRed shouldBe true
        node.left.value shouldBe "a"
        node.right.isRed shouldBe true
        node.right.value shouldBe "c"
    }

    @Test
    fun `should perform right-right rotation correctly`() {
        val node = BlackNode("a")
            .insert("b", Comparator.naturalOrder())
            .insert("c", Comparator.naturalOrder())
        node.isBlack shouldBe true
        node.value shouldBe "b"
        node.left.isRed shouldBe true
        node.left.value shouldBe "a"
        node.right.isRed shouldBe true
        node.right.value shouldBe "c"
    }

    @Test
    fun `should perform right-left rotation correctly`() {
        val node = BlackNode("a")
            .insert("c", Comparator.naturalOrder())
            .insert("b", Comparator.naturalOrder())
        node.isBlack shouldBe true
        node.value shouldBe "b"
        node.left.isRed shouldBe true
        node.left.value shouldBe "a"
        node.right.isRed shouldBe true
        node.right.value shouldBe "c"
    }

    @Test
    fun `should perform left insert with red uncle`() {
        val node = BlackNode("c")
            .insert("d", Comparator.naturalOrder())
            .insert("b", Comparator.naturalOrder())
            .insert("a", Comparator.naturalOrder())
        node.isRed shouldBe true
        node.value shouldBe "c"
        node.left.isBlack shouldBe true
        node.left.value shouldBe "b"
        node.right.isBlack shouldBe true
        node.right.value shouldBe "d"
        node.left.left.isRed shouldBe true
        node.left.left.value shouldBe "a"
    }

    @Test
    fun `should perform right insert with red uncle`() {
        val node = BlackNode("b")
            .insert("a", Comparator.naturalOrder())
            .insert("c", Comparator.naturalOrder())
            .insert("d", Comparator.naturalOrder())
        node.isRed shouldBe true
        node.value shouldBe "b"
        node.left.isBlack shouldBe true
        node.left.value shouldBe "a"
        node.right.isBlack shouldBe true
        node.right.value shouldBe "c"
        node.right.right.isRed shouldBe true
        node.right.right.value shouldBe "d"
    }

    @Test
    fun `inserting left and then right should not change root element`() {
        val node = BlackNode("b")
            .insert("a", Comparator.naturalOrder())
            .insert("c", Comparator.naturalOrder())
        node.isBlack shouldBe true
        node.value shouldBe "b"
    }

    @Test
    fun `inserting right and then left should not change root element`() {
        val node = BlackNode("b")
            .insert("c", Comparator.naturalOrder())
            .insert("a", Comparator.naturalOrder())
        node.isBlack shouldBe true
        node.value shouldBe "b"
    }

    @Test
    fun `should not do any rotations when inserting into black parent to right`() {
        val node = BlackNode("a")
            .insert("b", Comparator.naturalOrder())
            .insert("c", Comparator.naturalOrder())
            .insert("d", Comparator.naturalOrder())
            .insert("e", Comparator.naturalOrder())

        node.isRed shouldBe true
        node.value shouldBe "b"
        node.left.isBlack shouldBe true
        node.left.value shouldBe "a"
        node.right.isBlack shouldBe true
        node.right.value shouldBe "d"
        node.right.left.isRed shouldBe true
        node.right.left.value shouldBe "c"
        node.right.right.isRed shouldBe true
        node.right.right.value shouldBe "e"
    }

    @Test
    fun `should not do any rotations when inserting into black parent to left`() {
        val node = BlackNode("c")
            .insert("b", Comparator.naturalOrder())
            .insert("d", Comparator.naturalOrder())
            .insert("e", Comparator.naturalOrder())
            .insert("a", Comparator.naturalOrder())

        node.isRed shouldBe true
        node.value shouldBe "c"
        node.right.isBlack shouldBe true
        node.right.value shouldBe "d"
        node.left.isBlack shouldBe true
        node.left.value shouldBe "b"
        node.left.left.isRed shouldBe true
        node.left.left.value shouldBe "a"


    }


    @Test
    fun `should contain exact value`() {
        val node = BlackNode("b")
        node.contains("b", Comparator.naturalOrder()) shouldBe true
    }

    @Test
    fun `should contain value to right`() {
        val node = BlackNode("b")
            .insert("c", Comparator.naturalOrder())
        node.contains("c", Comparator.naturalOrder()) shouldBe true
    }

    @Test
    fun `should contain value to left`() {
        val node = BlackNode("b")
            .insert("a", Comparator.naturalOrder())
        node.contains("a", Comparator.naturalOrder()) shouldBe true
    }
}