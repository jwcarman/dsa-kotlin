package dsa.tree.bst

import dsa.tree.Tree
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.junit.jupiter.api.Test

class EmptyTreeTest {

    @Test
    fun newTreeShouldBeEmpty() {
        val bst: Tree<Int> = BinaryTree.empty()
        bst.isEmpty() shouldBe true
    }

    @Test
    fun shouldNotContainAnyValue() {
        val bst: Tree<Int> = BinaryTree.empty()
        bst.contains(123) shouldBe false
    }

    @Test
    fun clearShouldBeNoop() {
        val bst: Tree<Int> = BinaryTree.empty<Int>().clear()
        bst.isEmpty() shouldBe true
    }

    @Test
    fun shouldContainAllEmptyElements() {
        val bst = BinaryTree.empty<Int>()
        bst.containsAll(emptyList()) shouldBe true
    }

    @Test
    fun shouldNotContainAllNonEmptyCollection() {
        val bst = BinaryTree.empty<Int>()
        bst.containsAll(listOf(1, 2, 3)) shouldBe false
    }

    @Test
    fun removeShouldBeNoop() {
        val bst: Tree<Int> = BinaryTree.empty()
        val removed = bst.remove(1)
        removed shouldBeSameInstanceAs bst
    }

    @Test
    fun removeAllShouldBeNoop() {
        val bst = BinaryTree.empty<Int>()
        bst.removeAll(listOf(1, 2, 3)) shouldBeSameInstanceAs bst
    }

    @Test
    fun removeAllByPredicateShouldBeNoop() {
        val bst = BinaryTree.empty<Int>()
        bst.removeAll { it > 4 } shouldBeSameInstanceAs bst
    }

    @Test
    fun builderShouldNotBeSupported() {
        val bst = BinaryTree.empty<Int>()
        shouldThrow<UnsupportedOperationException> { bst.builder() }
    }
}