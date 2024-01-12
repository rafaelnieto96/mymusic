package com.example.mymusic.mymusic.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/exercises")
public class AudioController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping
    public ResponseEntity<?> addExercise(@RequestBody Exercise exercise) {
        return new ResponseEntity<>(exerciseService.addExercise(exercise), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExercise(@PathVariable long id, @RequestBody Exercise updatedExercise) {
        Exercise updated = exerciseService.updateExercise(id, updatedExercise);
        if (updated != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updated);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllExercises() {
        return new ResponseEntity<>(exerciseService.getAllExercises(), HttpStatus.OK);
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<?> getExerciseByName(@PathVariable String name) {
        return new ResponseEntity<>(exerciseService.getExerciseByName(name), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteExercise(@PathVariable long id) {
        exerciseService.deleteExercise(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Exercise deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Map<String, String>> deleteAllExercises() {
        exerciseService.deleteAllExercises();
        Map<String, String> response = new HashMap<>();
        response.put("message", "All exercises deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
