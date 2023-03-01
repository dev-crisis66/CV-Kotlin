package lidem.cfa.cv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class EditItem : AppCompatActivity() {

    private lateinit var buttonImg: Button
    private lateinit var imageView: ImageView

    companion object{
        val IMAGE_REQUEST_CODE = 100
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_item)

        buttonImg = findViewById(R.id.editBtn)
        val buttonValidate = findViewById<Button>(R.id.editValidate)
        val name = findViewById<EditText>(R.id.editName)
        val description = findViewById<EditText>(R.id.editDescription)
        imageView = findViewById(R.id.editImg)

        val item = intent.getSerializableExtra("item") as? Item
        val position = intent.getSerializableExtra("position").toString()
        val listName = intent.getSerializableExtra("listName").toString()

        val resourceId = applicationContext.resources.getIdentifier(item!!.image, "drawable", applicationContext.packageName);
        imageView.setImageResource( if(resourceId != 0) resourceId else R.drawable.ic_launcher_foreground )

        name.setText(item.name)
        description.setText(item.description)

        buttonImg.setOnClickListener {
            pickImageGallery()
        }
        buttonValidate.setOnClickListener {
            val newItem = Item(name.text.toString(), "test",description.text.toString())
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("item", newItem)
            intent.putExtra("position", position)
            intent.putExtra("listName", listName)
            println(listName)
            startActivity(intent)
        }
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            imageView.setImageURI(data?.data)
        }
    }
}