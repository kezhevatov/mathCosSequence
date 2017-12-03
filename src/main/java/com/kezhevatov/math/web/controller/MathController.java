package com.kezhevatov.math.web.controller;

import com.kezhevatov.math.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Andrey Kezhevatov.
 * Date: 03.12.2017
 */
@RestController
@RequestMapping(MathController.SEQUENCE_PATH)
public class MathController {

    public static final String SEQUENCE_PATH = "math/cos/sequence";

    private final MathService mathService;

    @Autowired
    public MathController(MathService mathService) {
        this.mathService = mathService;
    }

    @RequestMapping(method = GET)
    public ResponseEntity<String> getSequenceByIndex(@RequestParam("n") int elementIndex) {
        return new ResponseEntity<>(mathService.getSequenceByIndex(elementIndex), HttpStatus.OK);
    }
}
