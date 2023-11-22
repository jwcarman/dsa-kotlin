package dsa.tree.bst

import dsa.tree.Tree
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test


class BinaryTreeTest {


    @Test
    fun shouldContainValueAdded() {
        val bst: Tree<Int> = BinaryTree.empty<Int>().add(1)
        bst.contains(1) shouldBe true
    }

    @Test
    fun duplicatesShouldNotBeAllowed() {
        shouldThrow<IllegalArgumentException> {
            BinaryTree.empty<Int>().add(1).add(1)
        }
    }

    @Test
    fun shouldNotContainValueGreaterThan() {
        val bst: Tree<Int> = BinaryTree.of(1)
        bst.contains(2) shouldBe false
    }

    @Test
    fun shouldNotContainValueLessThan() {
        val bst: Tree<Int> = BinaryTree.of(1)
        bst.contains(0) shouldBe false
    }

    @Test
    fun treeShouldNotBeEmptyAfterAdd() {
        val bst: Tree<Int> = BinaryTree.of(1)
        bst.isEmpty() shouldBe false
    }

    @Test
    fun treeShouldAllowMultipleAdds() {
        val bst: Tree<Int> = BinaryTree.of(2, 1, 3)
        bst.isEmpty() shouldBe false
    }

    @Test
    fun clearShouldRemoveAllValues() {
        val bst: Tree<Int> = BinaryTree.of(2, 1, 3).clear()
        bst.isEmpty() shouldBe true
    }

    @Test
    fun removeAfterAddShouldYieldEmptyTree() {
        val bst: Tree<Int> = BinaryTree.empty<Int>().add(1).remove(1)
        bst.isEmpty() shouldBe true
    }

    @Test
    fun removingLeftValueShouldRetainOtherValues() {
        val bst: Tree<Int> = BinaryTree.of(2, 1, 3).remove(1)
        bst.shouldContainExactly(2, 3)
    }

    @Test
    fun removingWithEmptyLeftShouldYieldRight() {
        val bst: Tree<Int> = BinaryTree.of(2, 3).remove(2)
        bst.shouldContainExactly(3)
    }

    @Test
    fun removingRightValueShouldRetainOtherValues() {
        val bst: Tree<Int> = BinaryTree.of(2, 1, 3).remove(3)
        bst.shouldContainExactly(1, 2)
    }

    @Test
    fun removingWithEmptyRightShouldYieldLeft() {
        val bst: Tree<Int> = BinaryTree.of(2, 1).remove(2)
        bst.shouldContainExactly(1)
    }

    @Test
    fun removeLeafShouldYieldEmptyTree() {
        val bst = BinaryTree.of(2).remove(2)
        bst.isEmpty() shouldBe true
    }

    @Test
    fun removeWithLeftAndRightChildrenShouldReplaceWithMinimumSuccessor() {
        val bst = BinaryTree.of(2, 1, -1, 6, 3).remove(2)
        bst.shouldContainExactly(-1, 1, 3, 6)
    }

    @Test
    fun retainAllOnEmptyListShouldYieldEmptyList() {
        val bst = BinaryTree.empty<Int>().retainAll(listOf(1, 2, 3))
        bst.isEmpty() shouldBe true
    }

    @Test
    fun retainAllShouldYieldOnlyValuesFromSuppliedElements() {
        val bst = BinaryTree.of(1, 2, 3, 4).retainAll(listOf(2, 3, 5))
        bst.shouldContainExactly(2, 3)
    }

    @Test
    fun removeAllByPredicateShouldRemoveAppropriateElements() {
        val bst = BinaryTree.of(2, 1, 3, 5, 6, 7).removeAll { it > 5 }
        bst.shouldContainExactly(1, 2, 3, 5)
    }

    @Test
    fun containsAllShouldBeTrueForEmptyCollection() {
        val bst = BinaryTree.of(1, 2, 3)
        bst.containsAll(emptyList()) shouldBe true
    }

    @Test
    fun containsAllShouldBeFalseWhenAllValuesNotContained() {
        val bst = BinaryTree.of(1, 2, 3)
        bst.containsAll(listOf(1, 2, 3, 4)) shouldBe false
    }

    @Test
    fun emptyShouldReturnEmptyTree() {
        val bst = BinaryTree.empty<Int>()
        bst.isEmpty() shouldBe true
    }

    @Test
    fun emptyWithComparatorShouldReturnEmptyTree() {
        val bst = BinaryTree.empty<String>(Comparator.comparing { it.uppercase() })
        bst.isEmpty() shouldBe true
    }

    @Test
    fun ofShouldContainExactlyElements() {
        val bst = BinaryTree.of(1, 2, 3)
        bst.shouldContainExactly(1, 2, 3)
    }

    @Test
    fun fromShouldContainExactlyElements() {
        val bst = BinaryTree.from(listOf(1, 2, 3))
        bst.shouldContainExactly(1, 2, 3)
    }

    @Test
    fun ofWithComparatorShouldContainExactlyElements() {
        val bst = BinaryTree.of(Comparator.comparing { it.uppercase() }, "one", "two", "three")
        bst.shouldContainExactly("one", "three", "two")
    }

    @Test
    fun fromWithComparatorShouldContainExactlyElements() {
        val bst = BinaryTree.from(Comparator.comparing { it.uppercase() }, listOf("one", "two", "three"))
        bst.shouldContainExactly("one", "three", "two")
    }

    @Test
    fun builderShouldNotBeSupported() {
        val bst = BinaryTree.of(1,2,3)
        shouldThrow<UnsupportedOperationException> { bst.builder() }
    }
}