package com.example.demo.Services;

import com.example.demo.Models.Appuser;
import com.example.demo.Models.Log;
import com.example.demo.Repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService
{

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private UserService userService;

    public Iterable<Log> getAllLogs()
    {
        return this.logRepository.findAll();
    }

    public Log getLogById(int id) {

        return logRepository.findById(id)
                .orElse(null);
    }

    public Log save(String on_table ,String record_id , String operation)
    {

//              String username = Appuser.getAuthUsername();
//              Appuser appuser = userService.getUserByUsername(username);
//              purchase.setAppuser(appuser);

        Appuser appuser = userService.getUserByUsername("admin");

        Log log = new Log(on_table,record_id,operation,appuser);

        return this.logRepository.save(log);
    }

    public Log delete(int id)
    {
        Log log = this.logRepository.findById(id).orElse(null);
        if (log != null)
        {
            this.logRepository.deleteById(id);
        }
        return log;
    }
}