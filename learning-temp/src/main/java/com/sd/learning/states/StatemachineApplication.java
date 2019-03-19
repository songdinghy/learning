package com.sd.learning.states;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;

/**
 * @author SONGDING
 * @Description 启动类及测试用例
 * @date 2019/3/7 10:15
 */
@SpringBootApplication
public class StatemachineApplication implements CommandLineRunner {

    @Autowired
    private StateMachine<TurnstileStates, TurnstileEvents> stateMachine;

    @Autowired
    private StateMachinePersister stateMachinePersist;

    public static void main(String[] args) {
        SpringApplication.run(StatemachineApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        stateMachine.start();

        stateMachinePersist.restore(stateMachine, 1);
        System.out.println("--- push ---");
        stateMachine.sendEvent(TurnstileEvents.PUSH);
        stateMachinePersist.persist(stateMachine, 1);

        stateMachinePersist.restore(stateMachine, 1);
        System.out.println("--- push ---");
        stateMachine.sendEvent(TurnstileEvents.PUSH);
        stateMachinePersist.persist(stateMachine, 1);

        stateMachinePersist.restore(stateMachine, 1);
        System.out.println("--- coin ---");
        stateMachine.sendEvent(TurnstileEvents.COIN);
        stateMachinePersist.persist(stateMachine, 1);

        stateMachinePersist.restore(stateMachine, 1);
        System.out.println("--- coin ---");
        stateMachine.sendEvent(TurnstileEvents.COIN);
        stateMachinePersist.persist(stateMachine, 1);

        stateMachine.stop();
    }
}