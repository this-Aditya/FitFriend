package com.health.fitfriend.controller

import com.health.fitfriend.service.MeditationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/yoga/meditations")
class MeditationController(private val service: MeditationService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @GetMapping
    fun getMeditations() = service.getMeditations()

    @GetMapping("id/{id}")
    fun getMeditationById(@PathVariable id: Int) = service.getMeditationById(id)

    @GetMapping("/name/{name}")
    fun getMediatationByName(@PathVariable name:String) = service.getMeditationByName(name)

}