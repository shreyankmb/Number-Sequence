package com.vmware.number.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmware.number.domain.Sequence;
import com.vmware.number.model.data.GeneratePayload;
import com.vmware.number.repository.SequenceRepository;

@Service
public class SequenceServiceImpl implements SequenceService {

    ExecutorService executorService = Executors.newFixedThreadPool(4); 
    
    @Autowired
    SequenceRepository repository;
    
    @Override
    public String generateNumber(GeneratePayload payload) {
        int goal = Integer.parseInt(payload.getGoal());
        int step = Integer.parseInt(payload.getStep());
        String uniqueId = UUID.randomUUID().toString();
        String result = getSequence(goal,step);
        Sequence sequence = Sequence.builder().uuid(uniqueId).result(result).build();
        repository.save(sequence);
        return uniqueId;
    }
    
    private String getSequence(int goal,int step) {
        goal=(goal/step)*step;
        List<String> str=new ArrayList<String>();
        for(int i=goal;i>=0;i=i-step) {
            str.add(Integer.toString(i));
        }
        String result = String.join(",", str);
        return result;
    }

    @Override
    public String getStatus(String id) {
        String result = "";        
        Sequence seq = repository.findDataByUUID(id);
            if(Objects.isNull(seq)) {
                result = "ERROR";
            } else {
                result = "SUCCESS";
            }
           
        return result;
    }

    @Override
    public String getData(String id) {
        Sequence seq = null;
        String result = null;
        try {
            seq = repository.findDataByUUID(id);            
            result = seq.getResult();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }

    /*
     * Async Call based generation
     * 
     * 
     * */
    
    @Override
    public String generateNumbers(List<GeneratePayload> payload) {
        String uniqueId = UUID.randomUUID().toString();
        StringBuilder str = new StringBuilder();
        Future<String> result = null;
        
        for(GeneratePayload request: payload) {
           result = executorService.submit(()->bulkSequence(request));
           str.append("'");
           try {
            str.append(result.get()).append("'").append(",");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        }
        Sequence sequence = Sequence.builder().uuid(uniqueId).result(str.toString()).build();
        repository.save(sequence);
        return uniqueId;
    }
    
    private String bulkSequence(GeneratePayload request) {
        int goal = Integer.parseInt(request.getGoal());
        int step = Integer.parseInt(request.getStep());
        String result = getSequence(goal,step);
        return result;
    }

}
// throw new ResourceNotFoundException("Sequence not found for id: "+id);