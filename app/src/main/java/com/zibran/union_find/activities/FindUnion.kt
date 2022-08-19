package com.zibran.union_find.activities

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast

object FindUnion {
    @JvmStatic
    lateinit var nodeArray: IntArray
    lateinit var rankArray: IntArray

    private var k: Int = 0
    var sizeOfArray: Int = 0

    fun initArrays(n: Int) {
        nodeArray = IntArray(n)
        rankArray = IntArray(n)
        sizeOfArray = n

        for (i in 0 until n) {
            nodeArray[i] = i
        }
    }

    fun union(p: Int, q: Int) {
        val firstNode: Int = root(p)
        val secondNode: Int = root(q)

        if (firstNode == secondNode) {
            return
        }
        if (rankArray[firstNode] < rankArray[secondNode]) {
            nodeArray[firstNode] = secondNode

        } else if (rankArray[firstNode] > rankArray[secondNode]) {
            nodeArray[secondNode] = firstNode

        } else {
            nodeArray[secondNode] = firstNode
            rankArray[firstNode] = rankArray[firstNode] + 1
        }

    }

    fun connected(firstNode: Int, secondNode: Int): Boolean {
        return root(firstNode) == root(secondNode)
    }

    private fun root(nodeValue: Int): Int {
        k = nodeValue
        if (nodeArray[k] != k) {
            nodeArray[k] = root(nodeArray[k])
        }
        return nodeArray[k]
    }

    fun setText(text: String, connectionShowTV: TextView) {
        connectionShowTV.visibility = View.VISIBLE
        connectionShowTV.text = text
    }

    val showToast = { context: Context ->
        Toast.makeText(context,
            "Please enter the nodes between 0 to ${sizeOfArray - 1}",
            Toast.LENGTH_LONG).show()

    }
}