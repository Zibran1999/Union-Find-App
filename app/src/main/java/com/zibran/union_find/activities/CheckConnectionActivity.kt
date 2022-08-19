package com.zibran.union_find.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zibran.union_find.R
import com.zibran.union_find.activities.FindUnion.connected
import com.zibran.union_find.activities.FindUnion.initArrays
import com.zibran.union_find.activities.FindUnion.setText
import com.zibran.union_find.activities.FindUnion.showToast
import com.zibran.union_find.activities.FindUnion.sizeOfArray
import kotlinx.android.synthetic.main.activity_check_connection.*


class CheckConnectionActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_connection)

        addNodesBtn.setOnClickListener {
            startActivity(Intent(this, AddConnectionActivity::class.java))
        }

        initArrays(2001)

        checkConnectionBtn.setOnClickListener {
            checkConnections()
        }

    }

    private fun checkConnections() {

        val firstNode = firstNodeEdt.text.toString()
        val secondNode = secondNodeEdt.text.toString()

        if (TextUtils.isEmpty(firstNode)) {
            firstNodeEdt.error = "Please enter any value"
        } else if (TextUtils.isEmpty(secondNode)) {
            secondNodeEdt.error = "Please enter any value"

        } else {
            if (firstNode.toInt() < sizeOfArray && secondNode.toInt() < sizeOfArray) {
                if (connected(firstNode.toInt(), secondNode.toInt())) {
                    setText("(${firstNode}, ${secondNode}) are connected nodes",
                        connectionShowTV)
                } else {
                    setText("(${firstNode}, ${secondNode}) are not connected nodes",
                        connectionShowTV)
                }
            } else {
                showToast(this)
            }
        }

    }

    override fun onStart() {
        super.onStart()
        firstNodeEdt.text.clear()
        secondNodeEdt.text.clear()
        connectionShowTV.visibility = View.GONE
    }
}