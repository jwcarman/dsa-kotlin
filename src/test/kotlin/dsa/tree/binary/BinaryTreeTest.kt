package dsa.tree.binary

import dsa.tree.PersistentSetFactory
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

abstract class BinaryTreeTest {
    abstract fun factory(): PersistentSetFactory

    @Test
    fun `should create empty tree with no comparator for Comparable types`() {
        val tree = factory().create<String>()
        tree.isEmpty() shouldBe true
    }

    @Test
    fun `should create empty tree with custom comparator`() {
        val tree = factory().create<String>(Comparator.naturalOrder<String>().reversed())
        tree.isEmpty() shouldBe true
    }

    @Test
    fun `empty tree should have size 0`() {
        val tree = factory().create<String>()
        tree.size shouldBe 0
    }

    @Test
    fun `empty tree should return empty iterator`() {
        val tree = factory().create<String>()
        tree.iterator().hasNext() shouldBe false
    }

    @Test
    fun `empty tree should not contain element`() {
        val tree = factory().create<String>()
        tree.contains("foo") shouldBe false
    }

    @Test
    fun `removing from an empty tree should return an empty tree`() {
        val tree = factory().create<String>()
            .remove("foo")
        tree.isEmpty() shouldBe true
    }

    @Test
    fun `should be able to add to an empty tree`() {
        val tree = factory().create<String>()
            .add("foo")
        tree.size shouldBe 1
        tree.toList() shouldBe listOf("foo")
    }

    @Test
    fun `clear should return an empty tree`() {
        val tree = factory().create<String>()
            .add("foo")
            .clear()
        tree.isEmpty() shouldBe true
    }

    @Test
    fun `should be able to add multiple elements`() {
        val tree = factory().create<String>()
            .add("foo")
            .add("bar")
            .add("zoo")
        tree.size shouldBe 3
        tree.toList() shouldBe listOf("bar", "foo", "zoo")
    }

    @Test
    fun `adding same element should not change tree`() {
        val tree = factory().create<String>()
            .add("foo")
            .add("foo")
        tree.size shouldBe 1
        tree.toList() shouldBe listOf("foo")
    }

    @Test
    fun `should be able to remove element`() {
        val tree = factory().create<String>()
            .add("foo")
            .add("bar")
            .add("zoo")
            .remove("bar")
        tree.size shouldBe 2
        tree.contains("bar") shouldBe false
    }

    @Test
    fun `should contain root after adding`() {
        val tree = factory().create<String>()
            .add("foo")
        tree.contains("foo") shouldBe true
    }

    @Test
    fun `should contain element to left of root`() {
        val tree = factory().create<String>()
            .add("foo")
            .add("bar")
            .add("zoo")
        tree.contains("bar") shouldBe true
    }

    @Test
    fun `should contain element to right of root`() {
        val tree = factory().create<String>()
            .add("foo")
            .add("bar")
            .add("zoo")
        tree.contains("zoo") shouldBe true
    }

    @Test
    fun `should be able to remove root with two children`() {
        val tree = factory().create<String>()
            .add("foo")
            .add("bar")
            .add("zoo")
            .remove("foo")
        tree.size shouldBe 2
        tree.toList() shouldBe listOf("bar", "zoo")
    }

    @Test
    fun `should be able to remove root with left child only`() {
        val tree = factory().create<String>()
            .add("foo")
            .add("bar")
            .remove("foo")
        tree.size shouldBe 1
        tree.toList() shouldBe listOf("bar")
    }

    @Test
    fun `should be able to remove root with right child only`() {
        val tree = factory().create<String>()
            .add("foo")
            .add("zoo")
            .remove("foo")
        tree.size shouldBe 1
        tree.toList() shouldBe listOf("zoo")
    }

    @Test
    fun `should be able to remove root with no children`() {
        val tree = factory().create<String>()
            .add("foo")
            .remove("foo")
        tree.isEmpty() shouldBe true
    }

    @Test
    fun `should be able to remove element to the right of root`() {
        val tree = factory().create<String>()
            .add("foo")
            .add("bar")
            .add("zoo")
            .remove("zoo")
        tree.size shouldBe 2
        tree.toList() shouldBe listOf("bar", "foo")
    }

    @Test
    fun `should be able to remove element to the left of root`() {
        val tree = factory().create<String>()
            .add("foo")
            .add("bar")
            .remove("bar")
        tree.size shouldBe 1
        tree.toList() shouldBe listOf("foo")
    }
}