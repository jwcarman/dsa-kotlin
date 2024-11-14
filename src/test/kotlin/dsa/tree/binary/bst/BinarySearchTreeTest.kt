package dsa.tree.binary.bst

import dsa.tree.PersistentSetFactory
import dsa.tree.binary.BinaryTreeTest

class BinarySearchTreeTest: BinaryTreeTest() {

    override fun factory(): PersistentSetFactory = BinarySearchTree


}