package com.health.fitfriend.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.health.fitfriend.model.Meditation
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
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class MeditationControllerTest @Autowired constructor(
    private val mockMvc: MockMvc, private val objectMapper: ObjectMapper
) {

    private val baseUrl = "/yoga/meditations"

    @Test
    fun `should return all banks`() {
        //when/then
        mockMvc.get(baseUrl)
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].id") { value(1) }
            }
    }

    @Nested
    @DisplayName("/yoga/meditations/{identifier}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetSingleMeditationByIdentifier {
        @Test
        fun `should provide a single meditation by name`() {
            mockMvc.get("$baseUrl/name/First")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                }
        }

        @Test
        fun `should provide a single meditation by id`() {
            mockMvc.get("$baseUrl/id/1")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                }
        }
    }

    @Nested
    @DisplayName("/yoga/meditation/{invalid}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class HandleNotFoundTests {
        @Test
        fun `should return NOT_FOUND when no matching name for meditation is found`() {
            val name = "hello"
            mockMvc.get("$baseUrl/name/$name")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }

        @Test
        fun `should return NOT_FOUND when no matching id for meditation is present `() {
            val id = 126465
            mockMvc.get("$baseUrl/id/$id")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }

    @Nested
    @DisplayName("/POST /yoga/meditations")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PostNewMeditation {
        @Test
        fun `should add a new meditation`() {
            val newMeditation = Meditation(4, "four", "four", "four", "four", "four", "four")

            val postResponse = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newMeditation)
            }

            postResponse.andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(postResponse))
                    }
                }

            mockMvc.get("$baseUrl/id/${newMeditation.id}")
                .andExpect { status { isOk() } } }
        }

        @Test
        fun `should show BAD_REQUEST while performing post request for existing meditation id`() {
            val invalidMeditation = Meditation(1, "four", "four", "four", "four", "four", "four")

            val badRequestResponse = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidMeditation)
            }
            badRequestResponse.andDo { print() }
                .andExpect { status { isBadRequest() } }
        }

    @Nested
    @DisplayName("PATCH yoga/meditation")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class UpdateExistingMeditation {

        @Test
        fun `should update the existing meditation `() {
            val updatedMeditation = Meditation(1, "four", "four", "four", "four", "four", "four")

            val patchResponse = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedMeditation)
            }

            patchResponse.andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updatedMeditation))
                    }
                }

            mockMvc.get("$baseUrl/id/${updatedMeditation.id}")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updatedMeditation))
                    }
                }
        }

        @Test
        fun `should return NOT_FOUND when no such id exists for meditation`() {
            val wrongPatchMeditation = Meditation(10, "four", "four", "four", "four", "four", "four")

            val patchResponse = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(wrongPatchMeditation)
            }

            patchResponse.andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }
}