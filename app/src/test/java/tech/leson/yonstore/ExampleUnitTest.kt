package tech.leson.yonstore

import org.junit.Test

import org.junit.Assert.*
import tech.leson.yonstore.utils.AppUtils

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
  @Test
  fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
  }

  @Test
  fun splitArray() {
    println(AppUtils.stringToArray("Nike Abc Xyz"))
  }
}