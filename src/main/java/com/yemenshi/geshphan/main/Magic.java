package com.yemenshi.geshphan.main;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/magic"))
@RequiredArgsConstructor
@Slf4j
public class Magic {

    private final CommentRepository commentRepository;

    @GetMapping("/comment/error")
    @Operation(responses = {@ApiResponse(content = @Content(schema = @Schema(hidden = true)))})
    public ResponseEntity<?> error(HttpServletRequest request) {
        String errorCode = request.getParameter("errorCode");
        return new ResponseEntity<>(errorCode + " 마법 짜잔~", HttpStatusCode.valueOf(418));
    }

    @GetMapping("/comment/history")
    @Operation(responses = {@ApiResponse(content = @Content(schema = @Schema(hidden = true)))})
    public ResponseEntity<?> getHistory() {
        return new ResponseEntity<>(commentRepository.getBox(), HttpStatusCode.valueOf(200));
    }
}
