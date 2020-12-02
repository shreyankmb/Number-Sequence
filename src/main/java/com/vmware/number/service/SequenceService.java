package com.vmware.number.service;

import java.util.List;

import com.vmware.number.model.data.GeneratePayload;

public interface SequenceService {
    
    String generateNumber(GeneratePayload payload);
    
    String generateNumbers(List<GeneratePayload> payload);
    
    String getStatus(String id);
    
    String getData(String id);

}
