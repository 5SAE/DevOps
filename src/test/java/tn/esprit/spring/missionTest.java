package tn.esprit.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import tn.esprit.spring.services.EmployeServiceImpl;
import tn.esprit.spring.services.ITimesheetService;

import java.text.ParseException;


import static junit.framework.Assert.*;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class missionTest {

    @Autowired
    ITimesheetService TS;
    @Autowired
    MissionRepository Mr;
    @Autowired
    TimesheetRepository TSR;
    @Autowired
    DepartementRepository DR;
    @Autowired
    EmployeServiceImpl ESI;

    private static final Logger l = LogManager.getLogger(missionTest.class);

    @Test
    public void testAddMission(){
        int id = TS.ajouterMission(new Mission("mission1","Departement1"));
        assertNotNull(id);
        l.info("mission added " + id);
    }

    @Test
    public void testAffecterMissionADepartement(){

        int idm = TS.ajouterMission(new Mission("mission1",null));
        if (idm > 0){
            l.info("mission added");
        }
        Departement departement = new Departement("Departement1");
        DR.save(departement);
        l.info("departement created and added");
        Mission mission = Mr.findById(idm).get();
        TS.affecterMissionADepartement(mission.getId(),departement.getId());
        l.info("Mission affected");

    }

    
    @Test
    public void Deletemission() {
        int missionId = ESI.ajouterMission(new Mission("mission", "description"));
        ESI.deleteMissionById(missionId);
        l.info("Mission est supprimé");
    }

    public void timeTest() throws ParseException {

        long start1 = System.nanoTime();
        testAddMission();
        long end1 = System.nanoTime();
        System.out.println("test add mission take Time in nano seconds: "+ (end1-start1));

        long start2 = System.nanoTime();
        testAffecterMissionADepartement();
        long end2 = System.nanoTime();
        System.out.println("test affect mission to departement Time in nano seconds: "+ (end2-start2));

    }

    @Around("execution(* tn.esprit.spring.service.*(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        if(elapsedTime > 0 ){
            System.out.println(pjp + " took "+ elapsedTime + "MS");
        }
        System.out.println("Method execution time: " + elapsedTime + " milliseconds.");
        return obj;
    }
}  