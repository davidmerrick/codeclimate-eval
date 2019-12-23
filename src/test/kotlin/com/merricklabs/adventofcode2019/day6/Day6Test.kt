package com.merricklabs.adventofcode2019.day6

import com.merricklabs.adventofcode2019.day6.TreeDistanceTraverser.traverse
import io.kotlintest.shouldBe
import org.testng.annotations.Test

class Day6Test {

    @Test
    fun `Test example problem`() {
        val orbitList = """
            COM)B
            B)C
            C)D
            D)E
            E)F
            B)G
            G)H
            D)I
            E)J
            J)K
            K)L
        """.trimIndent()
                .lines()
                .map { it.split(")") }
                .map { Pair(it[0], it[1]) }
                .toList()

        val root = buildOrbitTree(orbitList)
        val orbitCount = traverse(root, 0)
        orbitCount shouldBe 42
    }


    @Test
    fun `Test my input`() {
        val orbitList = this::class.java.getResourceAsStream("input.txt")
                .bufferedReader()
                .readLines()
                .map { it.split(")") }
                .map { Pair(it[0], it[1]) }
                .toList()

        val root = buildOrbitTree(orbitList)
        val orbitCount = traverse(root, 0)
        println(orbitCount)
    }

    /**
     * Need to build the tree from a map because the values can be
     * out of order.
     */
    private fun buildOrbitTree(orbitList: List<Pair<String, String>>): TreeNode {
        val treeMap = mutableMapOf<String, TreeNode>()

        for (orbit in orbitList) {
            val currentRoot = getOrCreateNode(orbit.first, treeMap)
            val childNode = getOrCreateNode(orbit.second, treeMap)
            currentRoot.addChild(childNode)
            treeMap[orbit.second] = childNode
        }

        return treeMap.toTree()
    }

    private fun getOrCreateNode(value: String, treeMap: MutableMap<String, TreeNode>): TreeNode {
        return if (treeMap.containsKey(value)) {
            treeMap[value]!!
        } else {
            val newNode = TreeNode(value)
            treeMap[value] = newNode
            newNode
        }
    }
}

fun Map<String, TreeNode>.toTree() = this.values.first { it.parent == null }