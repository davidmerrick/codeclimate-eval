package com.merricklabs.adventofcode2019.day6

data class TreeNode(
        val name: String,
        val parent: TreeNode? = null
) {
    private val _children: MutableList<TreeNode> = mutableListOf()

    // Hide mutability
    val children: List<TreeNode>
        get() = _children.toList()

    fun addChild(node: TreeNode): TreeNode {
        val toAdd = node.copy(parent = this)
        _children.add(toAdd)
        return toAdd
    }
}