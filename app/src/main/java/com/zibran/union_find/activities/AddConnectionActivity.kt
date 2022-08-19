package com.zibran.union_find.activities

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.zibran.union_find.R
import com.zibran.union_find.activities.FindUnion.connected
import com.zibran.union_find.activities.FindUnion.setText
import com.zibran.union_find.activities.FindUnion.showToast
import com.zibran.union_find.activities.FindUnion.sizeOfArray
import com.zibran.union_find.activities.FindUnion.union
import kotlinx.android.synthetic.main.activity_add_connection.*


class AddConnectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_connection)
        connectionShowText.visibility = View.GONE

        add_more_btn.setOnClickListener {
            firstNodeEdt.text.clear()
            secondNodeEdt.text.clear()
            connectionShowText.visibility = View.GONE
            add_more_btn.visibility = View.GONE
        }
        add_connection_btn.setOnClickListener {
            createConnections()

        }
        backBtn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun createConnections() {
        // get values from user
        val firstNode = firstNodeEdt.text.toString().toInt()
        val secondNode = secondNodeEdt.text.toString().toInt()


        if (TextUtils.isEmpty(firstNode.toString())) {
            showError(firstNodeEdt)
        } else if (TextUtils.isEmpty(secondNode.toString())) {
            showError(secondNodeEdt)

        } else {
            if (firstNode < sizeOfArray && secondNode < sizeOfArray) {
                if (connected(firstNode, secondNode)) {
                    setText("(${firstNode}, ${secondNode}) are already connected!",
                        connectionShowText)
                } else {
                    union(firstNode, secondNode)
                    setText("(${firstNode}, ${secondNode}) are now connected nodes",
                        connectionShowText)
                    add_more_btn.visibility = View.VISIBLE
                }
            } else {
                showToast(this)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


    var showError = { edtText: EditText ->
        edtText.error = "Please enter a number"

    }
}