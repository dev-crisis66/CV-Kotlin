package lidem.cfa.cv

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.io.File
import java.io.IOException
import java.io.InputStream

val itemList: ArrayList<Item> = arrayListOf(
    Item("PHP", "php", "Language"),
    Item("Swift", "swift", "Language"),
    Item("Laravel", "laravel", "Language"),
    Item("Kotlin", "kotlin", "Language"),
    Item("Symfony", "symfony", "Language"),
    Item("Python", "python", "Language"),
)
val itemList2: ArrayList<Item> = arrayListOf(
    Item("Dr PC", "drpc", "Stage d'observation"),
    Item("Comelse", "comelse", "Debut le 18/06/2022")
)

class MainActivity : AppCompatActivity() {


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_refresh -> {
                itemList.shuffle()
                itemList2.shuffle()
                val listView = findViewById<ListView>(R.id.listView)
                val listView2 = findViewById<ListView>(R.id.listView2)

                val adapter = CustomAdapter(this, itemList)
                val adapter2 = CustomAdapter(this, itemList2)
                listView.adapter = adapter
                listView2.adapter = adapter2
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val item = intent.getSerializableExtra("item") as? Item
        val position = intent.getSerializableExtra("position").toString()
        val listName = intent.getSerializableExtra("listName").toString()
        if (item != null) {
            println(listName)
            when (listName) {
                "itemList" -> {
                    itemList[position.toInt()] = item
                }
                "itemList2" -> {
                    itemList2[position.toInt()] = item
                }
            }
        }
        val listView = findViewById<ListView>(R.id.listView)
        val listView2 = findViewById<ListView>(R.id.listView2)

        listView.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(this@MainActivity, itemList[p2].name, Toast.LENGTH_SHORT).show()
                val item = itemList[p2]
                val intent = Intent(this@MainActivity, ShowItem::class.java)
                intent.putExtra("item", item)
                intent.putExtra("position", p2)
                intent.putExtra("listName", "itemList")
                startActivity(intent)
            }
        }

        listView2.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val item = itemList2[p2]
                Toast.makeText(this@MainActivity, itemList2[p2].name, Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, ShowItem::class.java)
                intent.putExtra("item", item)
                intent.putExtra("position", p2)
                intent.putExtra("listName", "itemList2")
                startActivity(intent)
            }
        }

        val adapter = CustomAdapter(this, itemList)
        val adapter2 = CustomAdapter(this, itemList2)
        listView.adapter = adapter
        listView2.adapter = adapter2

    }
}