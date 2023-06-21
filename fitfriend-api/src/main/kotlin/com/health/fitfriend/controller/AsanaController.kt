package com.health.fitfriend.controller

import com.health.fitfriend.model.Asana
import com.health.fitfriend.service.AsanaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/yoga/asanas")
class AsanaController(private val service: AsanaService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNameNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IndexOutOfBoundsException::class)
    fun handleIdNotFound(e: IndexOutOfBoundsException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIdNotFound(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)


    @GetMapping
    fun getAsanas(): Collection<Asana> = service.getAsanas()

    @GetMapping("/id/{id}")
    fun getAsanaById(@PathVariable id: Int): Asana = service.getAsanaById(id)

    @GetMapping("/name/{name}")
    fun getAsanaByName(@PathVariable name: String): Asana = service.getAsanaByName(name)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addAsana(@RequestBody asana: Asana) = service.addAsana(asana)

}