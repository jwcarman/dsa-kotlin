package dsa.tree.binary.avl

import dsa.tree.binary.AbstractBinaryTreeTest
import dsa.tree.binary.BinarySearchTree
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class AvlTreeTest: AbstractBinaryTreeTest() {
    override fun <T : Comparable<T>> emptyTree(): BinarySearchTree<T> = BinarySearchTree.avl()

    override fun <T> emptyTree(comparator: Comparator<T>): BinarySearchTree<T> = BinarySearchTree.avl(comparator)

    override fun <T : Comparable<T>> treeOf(vararg values: T): BinarySearchTree<T> = BinarySearchTree.avlOf(*values)

    override fun <T> treeOf(comparator: Comparator<T>, vararg values: T): BinarySearchTree<T> =
        BinarySearchTree.avlOf(comparator, *values)

    override fun <T : Comparable<T>> treeFrom(values: Collection<T>): BinarySearchTree<T> =
        BinarySearchTree.avlFrom(values)

    override fun <T> treeFrom(comparator: Comparator<T>, values: Collection<T>): BinarySearchTree<T> =
        BinarySearchTree.avlFrom(comparator, values)

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
}