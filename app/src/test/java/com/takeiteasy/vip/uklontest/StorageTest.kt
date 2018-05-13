package com.takeiteasy.vip.uklontest

import com.takeiteasy.vip.uklontest.models.posts.RegularPost
import com.takeiteasy.vip.uklontest.storage.Storage
import org.junit.Test

class StorageTest {
    @Test
    fun `storage contains posts`() {
        val storage = Storage()
        storage.saveRegularPosts(listOf(RegularPost(1, 2, "title", "body")))

        val result: Boolean = storage.containsRegularPosts()

        assert(result)
    }
}