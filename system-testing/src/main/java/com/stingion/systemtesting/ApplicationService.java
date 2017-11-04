package com.stingion.systemtesting;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class ApplicationService {
    private int x;

    public int multiply(int x, int y) {
        return x * y;
    }
}