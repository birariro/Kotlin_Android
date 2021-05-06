package com.example.clipboardsample

import android.content.ClipDescription.MIMETYPE_TEXT_PLAIN
import android.content.ClipboardManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val button:Button by lazy { findViewById(R.id.button) }
    private val textView :TextView by lazy { findViewById(R.id.textView) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
                val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

                clipboard?.let {
                    if(clipboard?.primaryClipDescription?.hasMimeType(MIMETYPE_TEXT_PLAIN) == true){
                        val item = clipboard.primaryClip!!.getItemAt(0)
                        val yourText = item.text.toString()
                        textView.text = yourText
                    }else{
                        Toast.makeText(this,"클립보드에 텍스트가없다.",Toast.LENGTH_SHORT).show()
                    }
                }

            }

        }
}
