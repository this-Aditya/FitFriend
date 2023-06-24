package com.health.fitfriend.controller

import com.health.fitfriend.model.Meditation
import com.health.fitfriend.service.MeditationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import kotlin.IllegalArgumentException

@RestController
@RequestMapping("/yoga/meditations")
class MeditationController(private val service: MeditationService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun getMeditations() = service.getMeditations()

    @GetMapping("id/{id}")
    fun getMeditationById(@PathVariable id: Int) = service.getMeditationById(id)

    @GetMapping("/name/{name}")
    fun getMediatationByName(@PathVariable name:String) = service.getMeditationByName(name)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addMeditation(@RequestBody meditation: Meditation): Meditation = service.addMeditation(meditation)

    @PatchMapping
    fun updateMeditation(@RequestBody meditation: Meditation): Meditation = service.updateMeditation(meditation)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteMeditation(@PathVariable id: Int): Unit = service.deleteMeditation(id)
}