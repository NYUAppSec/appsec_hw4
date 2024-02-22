package com.example.giftcardsite

import com.example.giftcardsite.api.model.Product
import com.example.giftcardsite.api.service.ProductInterface

import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import org.junit.Assert.assertEquals
/**
 * Test to ensure that API functionality exists after compilation
 */
class ProductInterfaceTest {

    private val mockProductInterface = mockk<ProductInterface>()

    @Test
    fun testGetAllProducts() {
        val mockCall = mockk<Call<List<Product?>?>>()
        every { mockProductInterface.getAllProducts() } returns mockCall

        val products = listOf(
            Product(1, "Product 1", "image1.jpg", 10, "Description 1"),
            Product(2, "Product 2", "image2.jpg", 20, "Description 2")
        )

        every { mockCall.execute() } returns Response.success(products)

        val result = mockProductInterface.getAllProducts()?.execute()?.body()

        // Log the formatted string
        println("ProductInterfaceTest results:\n$result")

        assertEquals("[Product(productId=1, productName=Product 1, productImageLink=image1.jpg, recommendedPrice=10, description=Description 1), Product(productId=2, productName=Product 2, productImageLink=image2.jpg, recommendedPrice=20, description=Description 2)]", "$result")
    }
}