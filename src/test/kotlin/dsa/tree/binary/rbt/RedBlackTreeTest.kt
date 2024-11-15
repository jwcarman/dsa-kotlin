package dsa.tree.binary.rbt

import dsa.tree.PersistentSetFactory
import dsa.tree.binary.BinaryTreeTest
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class RedBlackTreeTest: BinaryTreeTest() {
    override fun factory(): PersistentSetFactory = RedBlackTree



    @Test
    fun `should handle left-left rotation`() {
        val tree = RedBlackTree.create<String>()
            .add("foo")
            .add("baz")
            .add("bar")
        tree.size shouldBe 3
        tree.iterator().asSequence().toList() shouldBe listOf("bar", "baz", "foo")
    }

    @Test
    fun `should handle right-right rotation`() {
        val tree = RedBlackTree.create<String>()
            .add("foo")
            .add("too")
            .add("zoo")
        tree.size shouldBe 3
        tree.iterator().asSequence().toList() shouldBe listOf("foo", "too", "zoo")
    }

    @Test
    fun `should handle left-right rotation`() {
        val tree = RedBlackTree.create<String>()
            .add("foo")
            .add("bar")
            .add("baz")
        tree.size shouldBe 3
        tree.iterator().asSequence().toList() shouldBe listOf("bar", "baz", "foo")
    }

    @Test
    fun `should handle right-left rotation`() {
        val tree = RedBlackTree.create<String>()
            .add("foo")
            .add("zoo")
            .add("too")
        tree.size shouldBe 3
        tree.iterator().asSequence().toList() shouldBe listOf("foo", "too", "zoo")
    }

    @Test
    fun `should handle left, red uncle case`() {
        val tree = RedBlackTree.create<Int>()
            .add(10)
            .add(5)
            .add(15)
            .add(1)
        tree.size shouldBe 4
        tree.iterator().asSequence().toList() shouldBe listOf(1, 5, 10, 15)
    }

    @Test
    fun `should handle right, red uncle case`() {
        val tree = RedBlackTree.create<Int>()
            .add(10)
            .add(5)
            .add(15)
            .add(20)
        tree.size shouldBe 4
        tree.iterator().asSequence().toList() shouldBe listOf(5, 10, 15, 20)
    }

    @Test
    fun `should handle inserting into a black parent to right`() {
        val tree = RedBlackTree.create<Int>()
            .add(10)
            .add(5)
            .add(15)
            .add(20)
            .add(12)
        tree.size shouldBe 5
        tree.iterator().asSequence().toList() shouldBe listOf(5, 10, 12, 15, 20)
    }

    @Test
    fun `should handle inserting into a black parent to left`() {
        val tree = RedBlackTree.create<Int>()
            .add(10)
            .add(5)
            .add(15)
            .add(1)
            .add(3)
        tree.size shouldBe 5
        tree.iterator().asSequence().toList() shouldBe listOf(1, 3, 5, 10, 15)
    }





}