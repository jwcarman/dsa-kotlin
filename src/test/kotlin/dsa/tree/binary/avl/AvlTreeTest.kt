package dsa.tree.binary.avl

import dsa.tree.binary.BinarySearchTree
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.junit.jupiter.api.Test

class AvlTreeTest {
    @Test
    fun emptyShouldBeEmpty() {
        val tree = BinarySearchTree.avl<Int>()
        tree.isEmpty() shouldBe true
    }

    @Test
    fun emptyRightShouldBeEmpty() {
        val tree = BinarySearchTree.avl<Int>()
        tree.right.isEmpty() shouldBe true
    }

    @Test
    fun emptyLeftShouldBeEmpty() {
        val tree = BinarySearchTree.avl<Int>()
        tree.left.isEmpty() shouldBe true
    }

    @Test
    fun emptyShouldNotBeRightWeighted() {
        val tree = BinarySearchTree.avl<Int>()
        tree.isRightWeighted() shouldBe false
    }

    @Test
    fun emptyShouldNotBeLeftWeighted() {
        val tree = BinarySearchTree.avl<Int>()
        tree.isLeftWeighted() shouldBe false
    }

    @Test
    fun clearEmptyTreeShouldBeNoop() {
        val bst: BinarySearchTree<Int> = BinarySearchTree.avl<Int>().clear()
        bst.isEmpty() shouldBe true
    }

    @Test
    fun clearShouldRemoveAllValues() {
        val bst = BinarySearchTree.avlOf(1, 2, 3)
        bst.clear().isEmpty() shouldBe true
    }

    @Test
    fun emptyShouldHaveZeroHeight() {
        val tree = BinarySearchTree.avl<Int>()
        tree.height shouldBe 0
    }

    @Test
    fun emptyShouldNotSupportAccessingValue() {
        val tree = BinarySearchTree.avl<Int>()
        shouldThrow<UnsupportedOperationException> { tree.value }
    }

    @Test
    fun addingToEmptyShouldReturnALeaf() {
        val tree = BinarySearchTree.avl<Int>()
        val newTree = tree.add(1)
        newTree.isEmpty() shouldBe false
        newTree.value shouldBe 1
        newTree.left.isEmpty() shouldBe true
        newTree.right.isEmpty() shouldBe true
    }

    @Test
    fun removeFromEmptyShouldYieldSameTree() {
        val tree = BinarySearchTree.avl<Int>()
        tree.remove(1) shouldBeSameInstanceAs tree
    }

    @Test
    fun addValueLessThanShouldReturnLeftWeightedTree() {
        val tree = BinarySearchTree.avlOf(2, 1)
        tree.isLeftWeighted() shouldBe true
    }

    @Test
    fun addValueGreaterThanShouldReturnRightWeightedTree() {
        val tree = BinarySearchTree.avlOf(1, 2)
        tree.isRightWeighted() shouldBe true
    }

    @Test
    fun shouldNotAllowAddingDuplicates() {
        val tree = BinarySearchTree.avlOf(1)
        shouldThrow<IllegalArgumentException> { tree.add(1) }
    }

    @Test
    fun shouldRebalanceUsingLeftLeftRotation() {
        val tree = BinarySearchTree.avlOf(1, 2, 3)
        tree.isLeftWeighted() shouldBe false
        tree.isRightWeighted() shouldBe false
        tree.height shouldBe 2
        tree.value shouldBe 2
        tree.left.value shouldBe 1
        tree.right.value shouldBe 3
    }

    @Test
    fun shouldRebalanceUsingLeftRightRotation() {
        val tree = BinarySearchTree.avlOf(1, 3, 2)
        tree.isLeftWeighted() shouldBe false
        tree.isRightWeighted() shouldBe false
        tree.height shouldBe 2
        tree.value shouldBe 2
        tree.left.value shouldBe 1
        tree.right.value shouldBe 3
    }

    @Test
    fun shouldRebalanceUsingRightRightRotation() {
        val tree = BinarySearchTree.avlOf(3, 2, 1)
        tree.isLeftWeighted() shouldBe false
        tree.isRightWeighted() shouldBe false
        tree.height shouldBe 2
        tree.value shouldBe 2
        tree.left.value shouldBe 1
        tree.right.value shouldBe 3
    }

    @Test
    fun shouldRebalanceUsingRightLeftRotation() {
        val tree = BinarySearchTree.avlOf(3, 1, 2)
        tree.isLeftWeighted() shouldBe false
        tree.isRightWeighted() shouldBe false
        tree.height shouldBe 2
        tree.value shouldBe 2
        tree.left.value shouldBe 1
        tree.right.value shouldBe 3
    }

    @Test
    fun creatingFromListShouldContainAllValues() {
        val tree = BinarySearchTree.avlFrom(listOf(1,2,3,4,5))
        tree shouldContainExactly listOf(1,2,3,4,5)
    }

    @Test
    fun fromWithComparatorShouldContainExactlyElements() {
        val bst = BinarySearchTree.avlFrom(Comparator.comparing { it.uppercase() }, listOf("one", "two", "three"))
        bst.shouldContainExactly("one", "three", "two")
    }

    @Test
    fun ofWithComparatorShouldContainExactlyElements() {
        val bst = BinarySearchTree.avlOf(Comparator.comparing { it.uppercase() }, "one", "two", "three")
        bst.shouldContainExactly("one", "three", "two")
    }
}