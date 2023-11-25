package dsa.tree.binary.unbalanced

import dsa.tree.binary.BinarySearchTree
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.junit.jupiter.api.Test

class UnbalancedTreeTest {
    @Test
    fun emptyShouldBeEmpty() {
        val tree = BinarySearchTree.unbalanced<Int>()
        tree.isEmpty() shouldBe true
    }

    @Test
    fun newTreeShouldBeEmpty() {
        val bst: BinarySearchTree<Int> = BinarySearchTree.unbalanced()
        bst.isEmpty() shouldBe true
    }

    @Test
    fun shouldNotContainAnyValue() {
        val bst: BinarySearchTree<Int> = BinarySearchTree.unbalanced()
        bst.contains(123) shouldBe false
    }

    @Test
    fun clearEmptyTreeShouldBeNoop() {
        val bst: BinarySearchTree<Int> = BinarySearchTree.unbalanced<Int>().clear()
        bst.isEmpty() shouldBe true
    }


    @Test
    fun shouldContainAllEmptyElements() {
        val bst = BinarySearchTree.unbalanced<Int>()
        bst.containsAll(emptyList()) shouldBe true
    }

    @Test
    fun shouldNotContainAllNonEmptyCollection() {
        val bst = BinarySearchTree.unbalanced<Int>()
        bst.containsAll(listOf(1, 2, 3)) shouldBe false
    }

    @Test
    fun removeShouldBeNoop() {
        val bst: BinarySearchTree<Int> = BinarySearchTree.unbalanced()
        val removed = bst.remove(1)
        removed shouldBeSameInstanceAs bst
    }

    @Test
    fun removeAllShouldBeNoop() {
        val bst = BinarySearchTree.unbalanced<Int>()
        bst.removeAll(listOf(1, 2, 3)) shouldBeSameInstanceAs bst
    }

    @Test
    fun removeAllByPredicateShouldBeNoop() {
        val bst = BinarySearchTree.unbalanced<Int>()
        bst.removeAll { it > 4 } shouldBeSameInstanceAs bst
    }
    
    @Test
    fun shouldContainValueAdded() {
        val bst: BinarySearchTree<Int> = BinarySearchTree.unbalanced<Int>().add(1)
        bst.contains(1) shouldBe true
    }

    @Test
    fun duplicatesShouldNotBeAllowed() {
        shouldThrow<IllegalArgumentException> {
            BinarySearchTree.unbalanced<Int>().add(1).add(1)
        }
    }

    @Test
    fun shouldNotContainValueGreaterThan() {
        val bst: BinarySearchTree<Int> = BinarySearchTree.unbalancedOf(1)
        bst.contains(2) shouldBe false
    }

    @Test
    fun shouldNotContainValueLessThan() {
        val bst: BinarySearchTree<Int> = BinarySearchTree.unbalancedOf(1)
        bst.contains(0) shouldBe false
    }

    @Test
    fun treeShouldNotBeEmptyAfterAdd() {
        val bst: BinarySearchTree<Int> = BinarySearchTree.unbalancedOf(1)
        bst.isEmpty() shouldBe false
    }

    @Test
    fun treeShouldAllowMultipleAdds() {
        val bst: BinarySearchTree<Int> = BinarySearchTree.unbalancedOf(2, 1, 3)
        bst.isEmpty() shouldBe false
    }

    @Test
    fun clearShouldRemoveAllValues() {
        val bst: BinarySearchTree<Int> = BinarySearchTree.unbalancedOf(2, 1, 3).clear()
        bst.isEmpty() shouldBe true
    }

    @Test
    fun removeAfterAddShouldYieldEmptyTree() {
        val bst: BinarySearchTree<Int> = BinarySearchTree.unbalanced<Int>().add(1).remove(1)
        bst.isEmpty() shouldBe true
    }

    @Test
    fun removingLeftValueShouldRetainOtherValues() {
        val bst: BinarySearchTree<Int> = BinarySearchTree.unbalancedOf(2, 1, 3).remove(1)
        bst.shouldContainExactly(2, 3)
    }

    @Test
    fun removingWithEmptyLeftShouldYieldRight() {
        val bst: BinarySearchTree<Int> = BinarySearchTree.unbalancedOf(2, 3).remove(2)
        bst.shouldContainExactly(3)
    }

    @Test
    fun removingRightValueShouldRetainOtherValues() {
        val bst: BinarySearchTree<Int> = BinarySearchTree.unbalancedOf(2, 1, 3).remove(3)
        bst.shouldContainExactly(1, 2)
    }

    @Test
    fun removingWithEmptyRightShouldYieldLeft() {
        val bst: BinarySearchTree<Int> = BinarySearchTree.unbalancedOf(2, 1).remove(2)
        bst.shouldContainExactly(1)
    }

    @Test
    fun removeLeafShouldYieldEmptyTree() {
        val bst = BinarySearchTree.unbalancedOf(2).remove(2)
        bst.isEmpty() shouldBe true
    }

    @Test
    fun removeWithLeftAndRightChildrenShouldReplaceWithMinimumSuccessor() {
        val bst = BinarySearchTree.unbalancedOf(2, 1, -1, 6, 3).remove(2)
        bst.shouldContainExactly(-1, 1, 3, 6)
    }

    @Test
    fun retainAllOnEmptyListShouldYieldEmptyList() {
        val bst = BinarySearchTree.unbalanced<Int>().retainAll(listOf(1, 2, 3))
        bst.isEmpty() shouldBe true
    }

    @Test
    fun retainAllShouldYieldOnlyValuesFromSuppliedElements() {
        val bst = BinarySearchTree.unbalancedOf(1, 2, 3, 4).retainAll(listOf(2, 3, 5))
        bst.shouldContainExactly(2, 3)
    }

    @Test
    fun removeAllByPredicateShouldRemoveAppropriateElements() {
        val bst = BinarySearchTree.unbalancedOf(2, 1, 3, 5, 6, 7).removeAll { it > 5 }
        bst.shouldContainExactly(1, 2, 3, 5)
    }

    @Test
    fun containsAllShouldBeTrueForEmptyCollection() {
        val bst = BinarySearchTree.unbalancedOf(1, 2, 3)
        bst.containsAll(emptyList()) shouldBe true
    }

    @Test
    fun containsAllShouldBeFalseWhenAllValuesNotContained() {
        val bst = BinarySearchTree.unbalancedOf(1, 2, 3)
        bst.containsAll(listOf(1, 2, 3, 4)) shouldBe false
    }

    @Test
    fun emptyShouldReturnEmptyTree() {
        val bst = BinarySearchTree.unbalanced<Int>()
        bst.isEmpty() shouldBe true
    }

    @Test
    fun emptyWithComparatorShouldReturnEmptyTree() {
        val bst = BinarySearchTree.unbalanced<String>(Comparator.comparing { it.uppercase() })
        bst.isEmpty() shouldBe true
    }

    @Test
    fun ofShouldContainExactlyElements() {
        val bst = BinarySearchTree.unbalancedOf(1, 2, 3)
        bst.shouldContainExactly(1, 2, 3)
    }

    @Test
    fun fromShouldContainExactlyElements() {
        val bst = BinarySearchTree.unbalancedFrom(listOf(1, 2, 3))
        bst.shouldContainExactly(1, 2, 3)
    }

    @Test
    fun ofWithComparatorShouldContainExactlyElements() {
        val bst = BinarySearchTree.unbalancedOf(Comparator.comparing { it.uppercase() }, "one", "two", "three")
        bst.shouldContainExactly("one", "three", "two")
    }

    @Test
    fun fromWithComparatorShouldContainExactlyElements() {
        val bst =
            BinarySearchTree.unbalancedFrom(Comparator.comparing { it.uppercase() }, listOf("one", "two", "three"))
        bst.shouldContainExactly("one", "three", "two")
    }

    @Test
    fun builderShouldNotBeSupported() {
        val bst = BinarySearchTree.unbalancedOf(1, 2, 3)
        shouldThrow<UnsupportedOperationException> { bst.builder() }
    }
}