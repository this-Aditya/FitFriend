package com.health.fitfriend.controller

import jdk.jfr.ContentType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class AsanaControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc
    private val baseUrl = "/yoga/asanas"

    @Test
    fun `should return all asanas`() {
        //when /then
        mockMvc.get(baseUrl)
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].id") { value(1) }
            }
    }

    @Nested
    @DisplayName("getAsana()")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetAsana {
        @Test
        fun `should return a single asana by id`() {
            //given
            val id = 3
            //when/then
            mockMvc.get("$baseUrl/id/$id")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.id") { value(id) }
                }
        }

        @Test
        fun `should return the single asana by name`() {
            //given
            val name = "first"
            //when / then
            mockMvc.get("$baseUrl/name/$name")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.name") { value(name) }
                }
        }
        
        @Test
        fun `should return NOT_FOUND if the asana name does not exist `() {
            //given
            val name = "fourth"
           //when //then
          mockMvc.get("$baseUrl/name/$name")
              .andDo { print() }
              .andExpect { status { isNotFound() } }
              }

        @Test
        fun `should return NOT_FOUND if the asana id does not exist`() {
           val id = 4678498
            mockMvc.get("$baseUrl/id/$id")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
           }
           }
    }






