package com.health.fitfriend.controller

import com.health.fitfriend.model.Pranayam
import com.health.fitfriend.service.PranayamService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/yoga/pranayams")
class PranayamController(private val pranayamService: PranayamService) {

    @ExceptionHandler
    fun handlePranayamNameNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler
    fun handlePranayamIdNotFound(e: IndexOutOfBoundsException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @GetMapping
    fun getPranayams(): Collection<Pranayam> = pranayamService.getPranayams()

    @GetMapping("/name/{name}")
    fun getPranayamByName(@PathVariable name: String): Pranayam = pranayamService.getpranayamByName(name)

    @GetMapping("/id/{id}")
    fun getPranayamById(@PathVariable id: Int): Pranayam = pranayamService.getpranayamById(id)

}