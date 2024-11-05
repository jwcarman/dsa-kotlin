package dsa.tree.binary.rbt

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class RedBlackTreeTest {

    @Test
    fun `should create empty tree with no comparator for Comparable types`() {
        val tree = RedBlackTree.create<String>()
        tree.isEmpty() shouldBe true
    }

    @Test
    fun `should create empty tree with custom comparator`() {
        val tree = RedBlackTree.create<String>(Comparator.naturalOrder<String>().reversed())
        tree.isEmpty() shouldBe true
    }

    @Test
    fun `empty tree should have size 0`() {
        val tree = RedBlackTree.create<String>()
        tree.size shouldBe 0
    }

    @Test
    fun `empty tree should have black root`() {
        val tree = RedBlackTree.create<String>()
        tree.root.isRed shouldBe false
    }

    @Test
    fun `empty tree should return empty iterator`() {
        val tree = RedBlackTree.create<String>()
        tree.iterator().hasNext() shouldBe false
    }

    @Test
    fun `empty tree should not contain element`() {
        val tree = RedBlackTree.create<String>()
        tree.contains("foo") shouldBe false
    }

    @Test
    fun `should be able to add to an empty tree`() {
        val tree = RedBlackTree.create<String>()
            .add("foo")
        tree.size shouldBe 1
        tree.root.isRed shouldBe false
    }

    @Test
    fun `clear should return an empty tree`() {
        val tree = RedBlackTree.create<String>()
            .add("foo")
            .clear()
        tree.isEmpty() shouldBe true
    }

    @Test
    fun `should be able to add multiple elements`() {
        val tree = RedBlackTree.create<String>()
            .add("foo")
            .add("bar")
            .add("zoo")
        tree.size shouldBe 3
        tree.iterator().asSequence().toList() shouldBe listOf("bar", "foo", "zoo")
    }

    @Test
    fun `should be able to add multiple elements with custom comparator`() {
        val tree = RedBlackTree.create<String>(Comparator.naturalOrder<String>().reversed())
            .add("foo")
            .add("bar")
            .add("zoo")
        tree.size shouldBe 3
        tree.iterator().asSequence().toList() shouldBe listOf("zoo", "foo", "bar")
    }

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