package lidem.cfa.cv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

@Suppress("DEPRECATION")
class ShowItem : AppCompatActivity() {

    var editItem: Item = Item("","","")
    var editPosition: String = "null"
    var editListName: String = "null"
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.show_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_edit -> {
                val intent = Intent(this, EditItem::class.java)
                intent.putExtra("item", editItem)
                intent.putExtra("position", editPosition)
                intent.putExtra("listName", editListName)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_item)

        val name: TextView = findViewById(R.id.showName)
        val description: TextView = findViewById(R.id.showDescription)
        val imageView: ImageView = findViewById(R.id.showImageView)

        val item = intent.getSerializableExtra("item") as? Item
        val position = intent.getSerializableExtra("position").toString()
        val listName = intent.getSerializableExtra("listName").toString()

        editItem = item!!
        editPosition = position
        editListName = listName

        name.text = item?.name
        description.text = item?.description
        val resourceId = applicationContext.resources.getIdentifier(item?.image, "drawable", applicationContext.packageName);
        imageView.setImageResource( if(resourceId != 0) resourceId else R.drawable.ic_launcher_foreground )
    }
}