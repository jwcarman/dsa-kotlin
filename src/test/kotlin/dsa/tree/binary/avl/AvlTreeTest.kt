package dsa.tree.binary.avl

import dsa.tree.PersistentSetFactory
import dsa.tree.binary.BinaryTreeTest
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class AvlTreeTest: BinaryTreeTest() {

    override fun factory(): PersistentSetFactory = AvlTree

    @Test
    fun `should rebalance using left-left rotation`() {
        val tree = factory().create<Int>()
            .add(1)
            .add(2)
            .add(3)

        tree.toList() shouldBe listOf(1, 2, 3)
    }

    @Test
    fun `should rebalance using left-right rotation`() {
        val tree = factory().create<Int>()
            .add(3)
            .add(1)
            .add(2)

        tree.toList() shouldBe listOf(1, 2, 3)
    }

    @Test
    fun `should rebalance using right-right rotation`() {
        val tree = factory().create<Int>()
            .add(3)
            .add(2)
            .add(1)

        tree.toList() shouldBe listOf(1, 2, 3)
    }

    @Test
    fun `should rebalance using right-left rotation`() {
        val tree = factory().create<Int>()
            .add(1)
            .add(3)
            .add(2)

        tree.toList() shouldBe listOf(1, 2, 3)
    }


}