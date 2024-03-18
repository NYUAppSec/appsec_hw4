package com.example.giftcardsite


import com.example.giftcardsite.api.model.Card
import com.example.giftcardsite.api.service.CardInterface
import com.example.giftcardsite.api.model.Product
import com.example.giftcardsite.api.model.BuyCardInfo

import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import org.junit.Assert.assertEquals
/**
 * Test to ensure that API functionality exists from api/service after build
 */
class CardInterfaceTest {

    private val mockCardInterface = mockk<CardInterface>()

    @Test
    fun testUseCard() {
        val cardNumber = 1234
        val authHeader = "Example auth header"
        val card = Card("data", Product(1, "product_name", "image_path", 10, "description"), 100.0, false, 1)
        val mockCall = mockk<Call<Card?>>()
        every { mockCardInterface.useCard(cardNumber, authHeader) } returns mockCall
        every { mockCall.execute() } returns Response.success(card)

        val result = mockCardInterface.useCard(cardNumber, authHeader).execute().body()
        println("testUseCard results:\n$result")
        assertEquals(card, result)
    }

    @Test
    fun testGetCards() {
        val authHeader = "Example auth header"
        val cards = listOf(Card("data1", Product(1, "product_name1", "image_path1", 10, "description1"), 100.0, false, 1),
            Card("data2", Product(2, "product_name2", "image_path2", 20, "description2"), 200.0, true, 2))
        val mockCall = mockk<Call<List<Card?>?>>()
        every { mockCardInterface.getCards(authHeader) } returns mockCall
        every { mockCall.execute() } returns Response.success(cards)

        val result = mockCardInterface.getCards(authHeader).execute().body()
        //println("testGetCards results:\n$result")
        assertEquals(cards, result)

    }

    @Test
    fun testBuyCard() {
        val productNumber = 123
        val buyCardInfo = BuyCardInfo(10)
        val authHeader = "Example auth header"
        val card = Card("data", Product(1, "product_name", "image_path", 10, "description"), 100.0, false, 1)
        val mockCall = mockk<Call<Card?>>()
        every { mockCardInterface.buyCard(productNumber, buyCardInfo, authHeader) } returns mockCall
        every { mockCall.execute() } returns Response.success(card)

        val result = mockCardInterface.buyCard(productNumber, buyCardInfo, authHeader)?.execute()?.body()
        assertEquals(card, result)
    }

}