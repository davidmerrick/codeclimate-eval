package com.merricklabs.adventofcode2019.day6

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
        val orbitCount = TreeDistanceTraverser.traverse(root, 0)
        orbitCount shouldBe 42
    }


    // Todo: These orbits aren't all part of the same tree
    // May need to create a map of all the root nodes
    // Could do this by filtering the tree map and only returning the ones
    // with a null parent.
    @Test
    fun `Test my input`() {
        val orbitList = this::class.java.getResourceAsStream("input.txt")
                .bufferedReader()
                .readLines()
                .map { it.split(")") }
                .map { Pair(it[0], it[1]) }
                .toList()

        val root = buildOrbitTree(orbitList)
        val orbitCount = TreeDistanceTraverser.traverse(root, 0)
        println(orbitCount)
    }

    private fun buildOrbitTree(orbitList: List<Pair<String, String>>): TreeNode {
        val treeMap = mutableMapOf<String, TreeNode>()

        for (orbit in orbitList) {
            val currentRoot: TreeNode = if (treeMap.containsKey(orbit.first)) {
                treeMap[orbit.first]!!
            } else {
                val newNode = TreeNode(orbit.first)
                treeMap[orbit.first] = newNode
                newNode
            }

            val newChild = currentRoot.addChild(TreeNode(orbit.second))
            treeMap[orbit.second] = newChild
        }

        return treeMap[orbitList[0].first]!!
    }

}