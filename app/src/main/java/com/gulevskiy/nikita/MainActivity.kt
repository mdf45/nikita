package com.gulevskiy.nikita

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import kotlin.system.exitProcess


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { check(input.text.toString()) }
        exit_button.setOnClickListener { exitProcess(0) }
    }

    private fun check(path: String) {
        var text = ""

        val directory = File(path)
        if (directory.listFiles() == null) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Ошибка")
            builder.setMessage("Некорректный путь...")
            builder.setPositiveButton("ОК") { dialog, _ -> dialog.dismiss() }
            builder.create()
            builder.show()
            return
        }
        val files: Array<File> = directory.listFiles()

        text += "Путь: $path\n"

        text += "Количество: ${files.size}\n"

        text += "Содержимое:\n"

        for (i in files.indices) {
            text += "${i+1}: ${files[i].name} - ${files[i].totalSpace / 8 / 1024 / 1024} Мб\n"
        }

        resultTV.text = text
    }
}