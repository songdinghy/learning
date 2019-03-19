package com.sd.learning.states;

import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SONGDING
 * @Description 获取当前状态机状态
 * @date 2019/3/7 11:02
 */
@Component
public class BizStateMachinePersist implements StateMachinePersist<TurnstileStates, TurnstileEvents, Integer> {

    static Map<Integer, TurnstileStates> cache = new HashMap<>(16);

    @Override
    public void write(StateMachineContext<TurnstileStates, TurnstileEvents> stateMachineContext, Integer integer) throws Exception {
        cache.put(integer, stateMachineContext.getState());
    }

    @Override
    public StateMachineContext<TurnstileStates, TurnstileEvents> read(Integer integer) throws Exception {
        // 注意状态机的初识状态与配置中定义的一致
        return cache.containsKey(integer) ?
                new DefaultStateMachineContext<>(cache.get(integer), null, null, null, null, "turnstileStateMachine") :
                new DefaultStateMachineContext<>(TurnstileStates.Locked, null, null, null, null, "turnstileStateMachine");
    }
}