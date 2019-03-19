package com.sd.learning.states;

import org.springframework.statemachine.annotation.OnStateChanged;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * @author SONGDING
 * @Description 定义动作监听类，StatemachineMonitor（名称随意），添加注解@WithStateMachine。本例中使用id进行状态机绑定，
 * 根据文档定义，可以使用name和id两种属性绑定需要监听的状态机实例。如果不定义任何name或者id，默认监听名称为stateMachine的状态机。
 * @date 2019/3/7 11:05
 */
@WithStateMachine(id = "turnstileStateMachine")
public class StatemachineMonitor {

    @OnTransition
    public void anyTransition() {
        System.out.println("--- OnTransition --- init");
    }

    @OnTransition(target = "Unlocked")
    public void toState1() {
        System.out.println("--- OnTransition --- toState1");
    }

    @OnStateChanged(source = "Unlocked")
    public void fromState1() {
        System.out.println("--- OnTransition --- fromState1");
    }
}
