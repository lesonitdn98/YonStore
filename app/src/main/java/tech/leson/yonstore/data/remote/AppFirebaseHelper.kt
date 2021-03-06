package tech.leson.yonstore.data.remote

import android.net.Uri
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.FirebaseStorage
import tech.leson.yonstore.data.model.*
import tech.leson.yonstore.utils.AppUtils
import java.util.*

@Suppress("UNCHECKED_CAST")
class AppFirebaseHelper(
    private val auth: FirebaseAuth,
    private val database: FirebaseFirestore,
    private val storage: FirebaseStorage,
) : FirebaseHelper {

    override fun register(registerData: User): Task<DocumentReference> {
        val user = ObjectMapper().convertValue(registerData, Map::class.java) as Map<String, Any>
        return database.collection("users").add(user)
    }

    override fun getUser(uid: String): Task<QuerySnapshot> =
        database.collection("users").whereEqualTo("accountId", uid).get()

    override fun updateUser(user: User): Task<Void> {
        val data = ObjectMapper().convertValue(user, Map::class.java) as MutableMap<String, Any>
        return database.collection("users").document(user.id).update(data)
    }

    override fun getAllCategory() = database.collection("categories").get()

    override fun getLimitCategory(limit: Long): Task<QuerySnapshot> =
        database.collection("categories").limit(limit).get()

    override fun getCategoryByStyle(style: String): Task<QuerySnapshot> =
        database.collection("categories").whereEqualTo("style", style).get()

    override fun saveImage(file: Uri): Task<Uri> {
        val riversRef = storage.reference.child("images/${file.lastPathSegment}")
        return riversRef.putFile(file).continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            riversRef.downloadUrl
        }
    }

    override fun deleteImage(url: String) = storage.getReferenceFromUrl(url).delete()

    override fun createProduct(product: Product): Task<DocumentReference> {
        val data = ObjectMapper().convertValue(product, Map::class.java) as MutableMap<String, Any>
        data["search"] = AppUtils.stringToArray(product.name)
        return database.collection("products").add(data)
    }

    override fun getAllProduct() = database.collection("products").get()

    override fun getProductById(id: String): Task<DocumentSnapshot> =
        database.collection("products").document(id).get()

    override fun getProductByCode(code: String) =
        database.collection("products").whereEqualTo("code", code).limit(1).get()

    override fun getProductByCategory(category: Category) =
        database.collection("products").whereEqualTo("categoryId", category.uid).get()

    override fun updateProduct(product: Product): Task<Void> {
        val data = ObjectMapper().convertValue(product, Map::class.java) as MutableMap<String, Any>
        data["search"] = AppUtils.stringToArray(product.name)
        return database.collection("products").document(product.id).update(data)
    }

    override fun removeProduct(product: Product) =
        database.collection("products").document(product.id).delete()

    override fun searchProduct(searchData: String) = database.collection("products")
        .whereArrayContains("search", searchData.toLowerCase(Locale.ROOT)).get()

    override fun onOrder(order: Order): Task<DocumentReference> {
        val data = ObjectMapper().convertValue(order, Map::class.java) as MutableMap<String, Any>
        return database.collection("orders").add(data)
    }

    override fun getOrderByUserId(userId: String) =
        database.collection("orders").whereEqualTo("userId", userId).get()

    override fun getAllOrder() = database.collection("orders").get()

    override fun getOrderByStatus(status: Int) =
        database.collection("orders").whereEqualTo("status", status).get()

    override fun updateOrder(order: Order): Task<Void> {
        val data = ObjectMapper().convertValue(order, Map::class.java) as MutableMap<String, Any>
        return database.collection("orders").document(order.id).update(data)
    }

    override fun createEvent(event: Event): Task<DocumentReference> {
        val data = ObjectMapper().convertValue(event, Map::class.java) as Map<String, Any>
        return database.collection("events").add(data)
    }

    override fun getAllEvent() = database.collection("events").get()

    override fun createReview(review: Review): Task<DocumentReference> {
        val data = ObjectMapper().convertValue(review, Map::class.java) as Map<String, Any>
        return database.collection("reviews").add(data)
    }

    override fun getReviewByProductId(productId: String) =
        database.collection("reviews").whereEqualTo("productId", productId).get()

    override fun getMyReviewByProductId(userId: String, productId: String) =
        database.collection("reviews").whereEqualTo("productId", productId)
            .whereEqualTo("userId", userId).get()

    override fun updateReview(review: Review): Task<Void> {
        val data = ObjectMapper().convertValue(review, Map::class.java) as Map<String, Any>
        return database.collection("reviews").document(review.id).update(data)
    }

    override fun logoutFirebase() {
        auth.signOut()
    }
}
