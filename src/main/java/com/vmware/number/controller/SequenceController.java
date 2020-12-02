package com.vmware.number.controller;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vmware.number.model.data.GeneratePayload;
import com.vmware.number.model.response.GenerateResponse;
import com.vmware.number.model.response.StatusResponse;
import com.vmware.number.service.SequenceService;



@RestController
@RequestMapping("/api")
public class SequenceController {

    @Autowired
    SequenceService squenceService;
    
    
    
    @PostMapping("/generate")
    public ResponseEntity<Object> createSequence(@RequestBody GeneratePayload payload) {
        String task = squenceService.generateNumber(payload);
        GenerateResponse response = GenerateResponse.builder().task(task).build();
        return ResponseEntity.accepted().body(response);
    }
    
    @GetMapping("/tasks/{id}/status")
    public ResponseEntity<Object> getStatus(@PathVariable(value = "id") String id){
        String result = squenceService.getStatus(id);
        StatusResponse response = StatusResponse.builder().result(result).build();
        return ResponseEntity.accepted().body(response);
    }
    
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Object> getData(@PathVariable(value = "id") String id,@RequestParam(value="action")String action){
        String result = squenceService.getData(id);
        StatusResponse response = StatusResponse.builder().result(result).build();
        return ResponseEntity.accepted().body(response);
    }
    
    @PostMapping("/bulkGenerate")
    public ResponseEntity<Object> createSequences(@RequestBody List<GeneratePayload> payload) {
        String task = squenceService.generateNumbers(payload);
        GenerateResponse response = GenerateResponse.builder().task(task).build();
        return ResponseEntity.accepted().body(response);
        
    }
}
