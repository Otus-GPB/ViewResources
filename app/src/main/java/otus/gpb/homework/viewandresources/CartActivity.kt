package otus.gpb.homework.viewandresources

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.appbar.MaterialToolbar
import kotlin.random.Random

private const val SHIPPING_PRICE = 2.00f
private const val TAX = 3.50f

class CartActivity : AppCompatActivity() {

    private var stupidId = Random.nextInt()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        findViewById<MaterialToolbar>(R.id.topCartBar).setNavigationOnClickListener {
            onBackPressed()
        }
        val items = makeItems()
        items.makeCart(findViewById(R.id.linear_layout))
        findViewById<TextView>(R.id.count_items_text_view).text = resources.getString(R.string.countItems, items.size)
        findViewById<TextView>(R.id.tax_value).text = String.format("%.2f", TAX)
        findViewById<TextView>(R.id.shipping_value).text = String.format("%.2f", SHIPPING_PRICE)
        findViewById<TextView>(R.id.subtotal_value).text = String.format("%.2f", items.getTotalPrice())
        findViewById<TextView>(R.id.order_total_value).text = String.format("%.2f", items.getTotalPrice() + TAX + SHIPPING_PRICE)
    }

    private fun makeItems() = listOf(
        Item(R.drawable.cake, 5.50f, "Cake for you", "Very very tasty"),
        Item(R.drawable.watch, 12.0f, "Best watch ever", "Shows time and nothing more"),
        Item(R.drawable.phone, 50.50f, "Phone for your creativity", "You'll like it for sure"),
        Item(R.drawable.laptop, 85.75f, "Modern laptop", "Enjoy it every day. 100000GB RAM"),
        Item(R.drawable.shoes, 54.99f, "Classical shoes", "Smart shoes for smart person"),
        Item(R.drawable.parker, 29.75f, "Parker pen", "Makes you look even better"),
        Item(R.drawable.cat, 9.99f, "Cat?", "Are you sure wanna buy a cat online?"),
        Item(R.drawable.car, 990.00f, "Nice orange nar", "As cool as you")
    )

    private fun createLayout(resource: Int, price: Float, name: String, description: String) : ConstraintLayout {
        val imageView = ImageView(this)
        imageView.id = stupidId++
        val imageParams = makeDefaultParams(dp(48), dp(48))
        imageParams.topMargin = dp(16)
        imageParams.bottomMargin = dp(16)
        imageParams.marginStart = dp(16)
        imageParams.horizontalBias = 0.0f
        imageView.layoutParams = imageParams
        imageView.setImageDrawable(resources.getDrawable(resource))

        val cancelView = ImageView(this)
        val cancelParams = makeDefaultParams(dp(14), dp(14))
        cancelParams.bottomMargin = dp(2)
        cancelParams.marginEnd = dp(16)
        cancelParams.bottomToBottom = imageView.id
        cancelParams.horizontalBias = 1.0f
        cancelParams.verticalBias = 1.0f
        cancelView.layoutParams = cancelParams
        cancelView.setImageDrawable(resources.getDrawable(R.drawable.cancel_24dp))

        val priceView = TextView(this)
        priceView.typeface = Typeface.DEFAULT_BOLD
        priceView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
        val priceParams = makeDefaultParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        priceParams.topMargin = dp(4)
        priceParams.marginEnd = dp(16)
        priceParams.topToTop = imageView.id
        priceParams.horizontalBias = 1.0f
        priceParams.verticalBias = 0.0f
        priceView.layoutParams = priceParams
        priceView.text = resources.getString(R.string.price, price)

        val nameView = TextView(this)
        nameView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16.0f)
        nameView.typeface = Typeface.DEFAULT_BOLD
        val nameParams = makeDefaultParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        nameParams.marginStart = dp(80)
        nameParams.topMargin = dp(4)
        nameParams.topToTop = imageView.id
        nameParams.horizontalBias = 0.0f
        nameParams.verticalBias = 0.0f
        nameView.layoutParams = nameParams
        nameView.text = name

        val descriptionView = TextView(this)
        descriptionView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14.0f)
        val descriptionParams = makeDefaultParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        descriptionParams.marginStart = dp(80)
        descriptionParams.bottomMargin = dp(4)
        descriptionParams.bottomToBottom = imageView.id
        descriptionParams.horizontalBias = 0.0f
        descriptionParams.verticalBias = 1.0f
        descriptionView.layoutParams = descriptionParams
        descriptionView.text = description

        val layout = ConstraintLayout(this)
        layout.addView(imageView)
        layout.addView(cancelView)
        layout.addView(priceView)
        layout.addView(nameView)
        layout.addView(descriptionView)

        return layout
    }

    private fun makeDefaultParams(width: Int, height: Int) : ConstraintLayout.LayoutParams {
        val params = ConstraintLayout.LayoutParams(width, height)
        params.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
        params.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
        params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
        params.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
        return params
    }

    private data class Item (val drawable: Int, val price: Float, val name: String, val description: String)

    private fun dp (int: Int) = (int * resources.displayMetrics.density).toInt()

    private fun List<Item>.makeCart(layout: LinearLayout) {
        this.forEach { layout.addView(createLayout(it.drawable, it.price, it.name, it.description)) }
    }

    private fun List<Item>.getTotalPrice(): Float {
        var count = 0.0f
        this.forEach { count += it.price }
        return count
    }
}