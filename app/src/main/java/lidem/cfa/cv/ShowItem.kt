package lidem.cfa.cv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

@Suppress("DEPRECATION")
class ShowItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_item)

        val name: TextView = findViewById(R.id.showName)
        val description: TextView = findViewById(R.id.showDescription)
        val imageView: ImageView = findViewById(R.id.showImageView)

        val item = intent.getSerializableExtra("item") as? Item

        name.text = item?.name
        description.text = item?.description
        val resourceId = applicationContext.resources.getIdentifier(item?.image, "drawable", applicationContext.packageName);
        imageView.setImageResource( if(resourceId != 0) resourceId else R.drawable.ic_launcher_foreground )
    }
}