package motobeans.architecture.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import android.widget.Toast
import java.math.BigDecimal
import java.util.regex.Pattern

/**
 * Created by swati on 24/9/2018.
 */

fun String.exShowToast(context: Context?) = Toast.makeText(context, this, Toast.LENGTH_LONG).show()

fun String?.exIsNotEmptyOrNullOrBlank() = !this.isNullOrBlank() && !this.isNullOrEmpty()

fun String.isValidEmail(): Boolean {
  val regex = "^(.+)@(.+)$"

  val pattern = Pattern.compile(regex)

  val matcher = pattern.matcher(this)
  return matcher.matches()
}

fun String.isInteger(): Boolean {
  val regex = "^[0-9]*$"
  val pattern = Pattern.compile(regex)

  val matcher = pattern.matcher(this)
  return matcher.matches()
}

fun String.exToInt(): Int {
  try {
    return this.toInt()
  } catch (e: Exception) {
    return 0
  }
}


fun Int?.isGreaterThanZero(): Boolean {
  try {
    if (this!!.compareTo(0) > 0) {
      return true
    }
    return false
  } catch (e: Exception) {
    return false
  }
}

fun String.exToDouble(): Double {
  try {
    return this.toDouble()
  } catch (e: Exception) {
    return 0.0
  }
}

fun String.exToLong(): Long {
  try {
    return this.toLong()
  } catch (e: Exception) {
    return 0
  }
}

fun String.exToBoolean(): Boolean? {
  try {
    return this.toBoolean()
  } catch (e: Exception) {
    return null
  }
}

fun String.exToFloat(): Float {
  try {
    return this.toFloat()
  } catch (e: Exception) {
    return 0F
  }
}

fun Double.roundTo2DecimalPlaces() = BigDecimal(this).setScale(2,
    BigDecimal.ROUND_HALF_UP).toDouble()

fun Array<String?>.exconcatenate(seperatedBy: String = ","): String {

  var concatenatedString = ""
  for (string in this) {
    when (string.exIsNotEmptyOrNullOrBlank()) {
      true -> concatenatedString = "$concatenatedString$seperatedBy $string"
    }
  }
  concatenatedString = concatenatedString.replaceFirst("$seperatedBy ", "")
  return concatenatedString
}


fun View.exGone() {
  arrayOf(this).exGone()
}

fun View.exVisible() {
  arrayOf(this).exVisible()
}

fun View.exinvisible() {
  arrayOf(this).exInvisible()
}

fun <T : View> Array<T>.exGone() {
  for (view in this) {
    view.visibility = View.GONE
  }
}

fun <T : View> Array<T>.exVisible() {
  for (view in this) {
    view.visibility = View.VISIBLE
  }
}

fun <T : View> Array<T>.exInvisible() {
  for (view in this) {
    view.visibility = View.INVISIBLE
  }
}

fun hasNetwork(context: Context): Boolean? {
  var isConnected: Boolean? = false // Initial Value
  val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
  val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
  if (activeNetwork != null && activeNetwork.isConnected)
    isConnected = true
  return isConnected
}