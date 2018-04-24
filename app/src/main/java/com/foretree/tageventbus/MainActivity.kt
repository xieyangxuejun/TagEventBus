package com.foretree.tageventbus

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.foretree.tageventbus.bus.Subscribe

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @Subscribe(["1", "2"])
    fun test(string: String, int: Int) {

    }
}
