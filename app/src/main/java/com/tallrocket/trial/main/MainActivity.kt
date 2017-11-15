package com.tallrocket.trial.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.Toast
import com.tallrocket.trial.R
import java.util.ArrayList

class MainActivity : AppCompatActivity(), MainView, ItemClick {


    var mOnItemClickListener: ItemClick? = null
    var mMainViewPresenter: MainViewPresenter? = null
    var mMainView: MainView? = null
    var mRecylerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUp()
        initializeRecylerView();
    }

    private fun initializeRecylerView() {
        mRecylerView = findViewById<RecyclerView>(R.id.recyler_view)
        mRecylerView?.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        mMainViewPresenter?.getDataFromServer()
    }

    private fun setUp() {
        mOnItemClickListener = this
        mMainView = this
        mMainViewPresenter = MainPresenter(mMainView)
    }

    override fun onItemClick(pos: Int, employee: Employee?) {
        Toast.makeText(this, "Selected Employee " + employee?.name, Toast.LENGTH_SHORT).show()
    }

    override fun loadData(employees: ArrayList<Employee>) {
        mRecylerView?.adapter = MyRecylerViewAdapter(this, employees, mOnItemClickListener)

    }
}
