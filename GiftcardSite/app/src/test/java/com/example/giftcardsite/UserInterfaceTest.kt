package com.example.giftcardsite

import com.example.giftcardsite.api.model.LoginInfo
import com.example.giftcardsite.api.model.RegisterInfo
import com.example.giftcardsite.api.model.User
import com.example.giftcardsite.api.service.UserInterface
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
/**
 * Test to ensure that API functionality exists from api/service after build
 */
class UserInterfaceTest {

    private val mockUserInterface = mockk<UserInterface>()

    @Test
    fun testLoginUser() {
        val loginInfo = LoginInfo("username", "password")
        val user = User("username", "token", "email")
        val mockCall = mockk<Call<User?>>()
        every { mockUserInterface.loginUser(loginInfo) } returns mockCall
        every { mockCall.execute() } returns Response.success(user)

        val result = mockUserInterface.loginUser(loginInfo)?.execute()?.body()
        println("LoginUserTest results:\n$result")

        assertEquals(user, result)
    }

    @Test
    fun testRegisterUser() {
        val registerInfo = RegisterInfo("test", "test@test.com", "test", "test")
        val user = User("username", "token", "email")
        val mockCall = mockk<Call<User?>>()
        every { mockUserInterface.registerUser(registerInfo) } returns mockCall
        every { mockCall.execute() } returns Response.success(user)

        val result = mockUserInterface.registerUser(registerInfo)?.execute()?.body()
        println("RegisterUserTest results:\n$result")

        assertEquals(user, result)
    }
}
