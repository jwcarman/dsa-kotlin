package dsa.tree.binary

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.junit.jupiter.api.Test

abstract class AbstractBinaryTreeTest {

    abstract fun <T : Comparable<T>> emptyTree(): BinarySearchTree<T>

    abstract fun <T> emptyTree(comparator: Comparator<T>): BinarySearchTree<T>

    abstract fun <T:Comparable<T>> treeOf(vararg values: T): BinarySearchTree<T>

    abstract fun <T> treeOf(comparator: Comparator<T>, vararg values: T): BinarySearchTree<T>

    abstract fun <T:Comparable<T>> treeFrom(values:Collection<T>): BinarySearchTree<T>

    abstract fun <T> treeFrom(comparator: Comparator<T>, values:Collection<T>): BinarySearchTree<T>

    @Test
    fun `empty tree should be empty`() {
        val tree = emptyTree<Int>()
        tree.isEmpty() shouldBe true
    }

    @Test
    fun `right of empty tree should be empty`() {
        val tree = emptyTree<Int>()
        tree.right.isEmpty() shouldBe true
    }

    @Test
    fun `left of empty tree should be empty`() {
        val tree = emptyTree<Int>()
        tree.left.isEmpty() shouldBe true
    }

    @Test
    fun `empty tree should not be right weighted`() {
        val tree = emptyTree<Int>()
        tree.isRightWeighted() shouldBe false
    }

    @Test
    fun `empty tree should not be left weighted`() {
        val tree = emptyTree<Int>()
        tree.isLeftWeighted() shouldBe false
    }

    @Test
    fun `adding value less than should return left weighted tree`() {
        val tree = treeOf(2, 1)
        tree.isLeftWeighted() shouldBe true
    }

    @Test
    fun `adding value greater than should return right weighted tree`() {
        val tree = treeOf(1, 2)
        tree.isRightWeighted() shouldBe true
    }

    @Test
    fun `empty tree should be empty with comparator`() {
        val tree = emptyTree<String>(Comparator.comparing { it.uppercase() })
        tree.isEmpty() shouldBe true
    }

    @Test
    fun `adding to empty tree should yield leaf`() {
        val tree = emptyTree<Int>()
        val newTree = tree.add(1)
        newTree.isEmpty() shouldBe false
        newTree.value shouldBe 1
        newTree.left.isEmpty() shouldBe true
        newTree.right.isEmpty() shouldBe true
    }

    @Test
    fun `empty tree should not contain values`() {
        val tree = emptyTree<Int>()
        tree.contains(123) shouldBe false
    }

    @Test
    fun `empty tree should have zero height`() {
        val tree = emptyTree<Int>()
        tree.height shouldBe 0
    }

    @Test
    fun `empty tree should have zero size`() {
        val tree = emptyTree<Int>()
        tree.size shouldBe 0
    }

    @Test
    fun `empty tree should not support accessing value`() {
        val tree = emptyTree<Int>()
        shouldThrow<UnsupportedOperationException> { tree.value }
    }

    @Test
    fun `clearing empty tree should be noop`() {
        val tree = emptyTree<Int>()
        val newTree = tree.clear()
        newTree shouldBeSameInstanceAs tree
    }

    @Test
    fun `retain all on empty tree should be noop`() {
        val tree = emptyTree<Int>()
        val newTree = tree.retainAll(listOf(1, 2, 3))
        newTree shouldBeSameInstanceAs tree
    }

    @Test
    fun `empty tree should contain all elements of empty list`() {
        val tree = emptyTree<Int>()
        tree.containsAll(emptyList()) shouldBe true
    }

    @Test
    fun `empty tree should not contain all of non-empty list`() {
        val tree = emptyTree<Int>()
        tree.containsAll(listOf(1, 2, 3)) shouldBe false
    }

    @Test
    fun `remove from empty tree should be noop`() {
        val tree = emptyTree<Int>()
        val newTree = tree.remove(1)
        newTree shouldBeSameInstanceAs tree
    }

    @Test
    fun `remove all from empty tree should be noop`() {
        val tree = emptyTree<Int>()
        val newTree = tree.removeAll(listOf(1, 2, 3))
        newTree shouldBeSameInstanceAs tree
    }

    @Test
    fun `remove all by predicate from empty tree should be noop`() {
        val tree = emptyTree<Int>()
        val newTree = tree.removeAll { it > 4 }
        newTree shouldBeSameInstanceAs tree
    }

    @Test
    fun `tree should contain value added to it`() {
        val tree = emptyTree<Int>()
        val newTree = tree.add(1)
        newTree.contains(1) shouldBe true
    }

    @Test
    fun `tree should not be empty after adding value`() {
        val tree = emptyTree<Int>()
        val newTree = tree.add(1)
        newTree.isEmpty() shouldBe false
    }

    @Test
    fun `adding duplicates should not be allowed`() {
        val tree = treeOf(1)
        shouldThrow<IllegalArgumentException> { tree.add(1) }
    }

    @Test
    fun `should not contain value greater than added value`() {
        val tree = treeOf(1)
        tree.contains(2) shouldBe false
    }

    @Test
    fun `should not contain value less than added value`() {
        val tree = treeOf(1)
        tree.contains(0) shouldBe false
    }

    @Test
    fun `clear should remove all values`() {
        val tree = treeOf(1, 2, 3)
        tree.clear().isEmpty() shouldBe true
    }

    @Test
    fun `removing value less than should retain other values`() {
        val tree = treeOf(2, 1, 3).remove(1)
        tree.shouldContainExactly(2, 3)
    }

    @Test
    fun `removing value greater than should retain other values`() {
        val tree = treeOf(2, 1, 3).remove(3)
        tree.shouldContainExactly(1, 2)
    }

    @Test
    fun `removing from tree with empty left should yield right`() {
        val tree = treeOf(2, 3).remove(2)
        tree.shouldContainExactly(3)
    }

    @Test
    fun `removing from tree with empty right should yield left`() {
        val tree = treeOf(2, 1).remove(2)
        tree.shouldContainExactly(1)
    }

    @Test
    fun `removing from leaf should yield empty tree`() {
        val tree = treeOf(2).remove(2)
        tree.isEmpty() shouldBe true
    }

    @Test
    fun `retain all should only retain supplied elements`() {
        val tree = treeOf(1, 2, 3, 4).retainAll(listOf(2, 3, 5))
        tree.shouldContainExactly(2, 3)
    }

    @Test
    fun `remove all by predicate should only remove matching elements`() {
        val tree = treeOf(1, 2, 3, 4).removeAll { it > 2 }
        tree.shouldContainExactly(1, 2)
    }

    @Test
    fun `non-empty tree should contain all of empty list`() {
        val tree = treeOf(1, 2, 3)
        tree.containsAll(emptyList()) shouldBe true
    }

    @Test
    fun `non-empty tree should not contain all of non-empty list with missing elements`() {
        val tree = treeOf(1, 2, 3)
        tree.containsAll(listOf(1, 2, 3, 4)) shouldBe false
    }

    @Test
    fun `builder should not be supported`() {
        val tree = treeOf(1, 2, 3)
        shouldThrow<UnsupportedOperationException> { tree.builder() }
    }

    @Test
    fun `creating tree from list of elements should contain exactly those elements`() {
        val tree = treeFrom(listOf(1, 2, 3))
        tree.shouldContainExactly(1, 2, 3)
    }

    @Test
    fun `creating tree from list of elements with comparator should contain exactly those elements`() {
        val tree = treeFrom(Comparator.comparing { it.uppercase() }, listOf("one", "two", "three"))
        tree.shouldContainExactly("one", "three", "two")
    }

    @Test
    fun `creating tree from vararg of elements should contain exactly those elements`() {
        val tree = treeOf(1, 2, 3)
        tree.shouldContainExactly(1, 2, 3)
    }

    @Test
    fun `creating tree from vararg of elements with comparator should contain exactly those elements`() {
        val tree = treeOf(Comparator.comparing { it.uppercase() }, "one", "two", "three")
        tree.shouldContainExactly("one", "three", "two")
    }
}