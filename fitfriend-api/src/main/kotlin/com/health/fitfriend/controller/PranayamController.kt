package com.health.fitfriend.controller

import com.health.fitfriend.model.Pranayam
import com.health.fitfriend.service.PranayamService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
@RequestMapping("/yoga/pranayams")
class PranayamController(private val pranayamService: PranayamService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handlePranayamNameNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IndexOutOfBoundsException::class)
    fun handlePranayamIdNotFound(e: IndexOutOfBoundsException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException):ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun getPranayams(): Collection<Pranayam> = pranayamService.getPranayams()

    @GetMapping("/name/{name}")
    fun getPranayamByName(@PathVariable name: String): Pranayam = pranayamService.getpranayamByName(name)

    @GetMapping("/id/{id}")
    fun getPranayamById(@PathVariable id: Int): Pranayam = pranayamService.getpranayamById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addPranayam(@RequestBody pranayam: Pranayam): Pranayam = pranayamService.addPranayam(pranayam)

    @PatchMapping
    fun updatePranayam(@RequestBody pranayam: Pranayam): Pranayam = pranayamService.updatePranayam(pranayam)
}