package otus.gpb.homework.viewandresources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.appbar.MaterialToolbar

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        setCartItem(R.id.cart_item_1, R.drawable.img_cake, "On the top", 1.5)
        setCartItem(R.id.cart_item_2, R.drawable.img_disk, "Forever Friends", 4.5)
        setCartItem(R.id.cart_item_3, R.drawable.img_clock, "Prototyping Kit", 8.0)
        setCartItem(R.id.cart_item_4, R.drawable.img_cactus, "Basket & Coffee", 22.0)

        findViewById<MaterialToolbar>(R.id.cart_top_bar).setNavigationOnClickListener {
            finish()
        }
    }

    private fun setCartItem(@IdRes layout: Int, @DrawableRes image: Int, name: String, price: Double) {
        var item: ViewGroup = findViewById(layout)
        item.findViewById<ImageView?>(R.id.item_image)?.apply {
            setImageDrawable(getDrawable(image))
        }
        item.findViewById<TextView?>(R.id.item_name)?.apply {
            text = name
        }
        item.findViewById<TextView?>(R.id.item_caption)?.apply {
            text = "Caption"
        }
        item.findViewById<TextView?>(R.id.item_price)?.apply {
            text = "\$US,${String.format("%.2f", price)}"
        }
    }

}