package lidem.cfa.cv

import android.view.ViewGroup
import android.app.Activity
import android.view.View
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter(private val context: Activity, private val itemList: ArrayList<Item>)
    : BaseAdapter() {
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.item_card,null)
        val imageView = rowView.findViewById<ImageView>(R.id.image_item)
        val textView = rowView.findViewById<TextView>(R.id.text_item)
        val resourceId = context.resources.getIdentifier(itemList[p0].image, "drawable", context.packageName);
        imageView.setImageResource( if(resourceId != 0) resourceId else R.drawable.ic_launcher_foreground )
        textView.text = itemList[p0].name

        return rowView
    }
    override fun getItem(p0: Int): Any {
        return itemList.get(p0)
    }
    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }
    override fun getCount(): Int {
        return itemList.size
    }
}