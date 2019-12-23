package com.merricklabs.adventofcode2019.day6

object TreeDistanceTraverser {
    fun traverse(node: TreeNode, level: Int): Int {
        // base case
        if (node.children.isEmpty()) {
            return level
        }

        var distance = level

        for (child in node.children) {
            distance += traverse(child, level + 1)
        }

        return distance
    }
}