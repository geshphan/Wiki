package com.yemenshi.geshphan.main;

import com.yemenshi.geshphan.GeshphanApplication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/sys")
@RequiredArgsConstructor
@Slf4j
public class SystemController {

    @GetMapping("/restart")
    public void reboot(String pw) {
        if ("1q2w3e4r!".equals(pw)) {
            GeshphanApplication.restart();
        }
    }
}
