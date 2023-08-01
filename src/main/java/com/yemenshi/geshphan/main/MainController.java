package com.yemenshi.geshphan.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final CommentRepository commentRepository;

    @RequestMapping(value = "/greetings", method = {RequestMethod.GET, RequestMethod.POST})
    @Operation(summary = "ㅎㅇ", description = "인사임")
    public String greetings() {
        return "hello";
    }

    @RequestMapping(value = "/init", method = {RequestMethod.GET, RequestMethod.POST}, consumes = "application/json")
    @Operation(summary = "[BETA] Set initial Data", description = "[BETA]\nSet initial data. List of content(String) parsed to JSON is required.")
    public ResponseEntity<String> init(@RequestBody String data) {
        ObjectMapper om = new ObjectMapper();
        try {
            List<String> contents = om.readValue(data, new TypeReference<>(){});
            return new ResponseEntity<>(commentRepository.init(contents).name(), HttpStatusCode.valueOf(200));

        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("An error occurred while parsing the JSON");
        }
    }

    @PostMapping("/save")
    @Operation(summary = "Save comment", description = "Comment will be saved with a key format of \"yyMMddhhmmssSS\".")
    public ResponseEntity<String> save(String comment) {
        return new ResponseEntity<>(commentRepository.save(comment).name(), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete comment", description = "Comment with the given id will be deleted. The format of id must be \"yyMMddhhmmss\".")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return new ResponseEntity<>(commentRepository.delete(id).name(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/list")
    @Operation(summary = "Look up list of comments", description = "Look up the entire list of comments with POSTED status.")
    public ResponseEntity<List<Comment>> getList() {
        return new ResponseEntity<>(commentRepository.getCommentList(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/list/{id}")
    @Operation(summary = "Look up a comment", description = "Look up a specific comment with the given id.")
    public ResponseEntity<List<Comment>> getList(@PathVariable String id) {
        return commentRepository.getComment(id)
                .map(list -> new ResponseEntity<>(list, HttpStatusCode.valueOf(200)))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatusCode.valueOf(400)));
    }

}



