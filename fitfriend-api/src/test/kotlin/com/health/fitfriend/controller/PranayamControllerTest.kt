package com.health.fitfriend.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.health.fitfriend.model.Pranayam
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.mockito.ArgumentMatchers.matches
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class PranayamControllerTest @Autowired constructor(val mockMvc: MockMvc, val objectMapper: ObjectMapper) {

    private val baseUrl = "/yoga/pranayams"

    @Test
    fun `should returns all pranayams`() {
        mockMvc.get(baseUrl)
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].id") { value(1) }
            }
    }

    @Nested
    @DisplayName("")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetPranayam {

        @Test
        fun `should return a single pranayam by name`() {
            val name = "anulom vilom"
            mockMvc.get("$baseUrl/name/$name")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.name").value(matches("(?i)$name"))
                }
        }

        @Test
        fun `should return a pranayam by id`() {
            val id = 2
            mockMvc.get("$baseUrl/id/$id")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.id").value(id)
                }
        }
    }

    @Nested
    @DisplayName("/get/unavailable")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class HandlingNotFound {

        @Test
        fun `should return not found if pranayam name is not found`() {
            val name = "hello"
            mockMvc.get("$baseUrl/name/$name")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }

        @Test
        fun `should return not found if pranayam id is not available`() {
            val id = 9867
            mockMvc.get("$baseUrl/id/$id")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }

    @Nested
    @DisplayName("POST /yoga/pranayams")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class ShouldPostPranayam {
        @Test
        fun `should update a pranayam `() {
            val pranayamToUpdate = Pranayam(5, "first", "first", "first", "first", "first", "first", "first")

            val postedResponse = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(pranayamToUpdate)
            }

            postedResponse.andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(pranayamToUpdate))
                    }
                }

            mockMvc.get("$baseUrl/id/${pranayamToUpdate.id}")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(pranayamToUpdate))
                    }
                }
        }

        @Test
        fun `should return BAD_REQUEST when pranayam with id already exists `() {
            val invalidPranayamToUpdate = Pranayam(1, "first", "first", "first", "first", "first", "first", "first")

            mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidPranayamToUpdate)
            }
                .andExpect { status { isBadRequest() } }
        }
    }

}