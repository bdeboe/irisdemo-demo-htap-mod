package com.irisdemo.htap;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.irisdemo.htap.worker.AccumulatedMetrics;
import com.irisdemo.htap.worker.WorkerService;

@CrossOrigin()
@RestController
public class AppController 
{

    @Autowired
    WorkerService workerService;
    
    @Autowired
    AccumulatedMetrics accumulatedMetrics;

    /**
     * This is called by the container HEALTHCHECK
     **/
    @GetMapping(value = "/worker/test")
    public int test() 
    {
        return 1;
    }

    @PostMapping(value = "/worker/startSpeedTest")
    public void startSpeedTest() throws IOException, SQLException 
    {
        workerService.startSpeedTest();
    }

    @PostMapping(value = "/worker/stopSpeedTest")
    public void stopSpeedTest() 
    {
        workerService.stopSpeedTest();
    }

    @GetMapping(value = "/worker/getActiveFeeds")
    public int getActiveFeeds() 
    {
        return workerService.getNumberOfActiveFeeds();
    }
    
    @GetMapping("/worker/getMetrics")
    public AccumulatedMetrics getMetrics() 
    {
        return accumulatedMetrics;
    }


}