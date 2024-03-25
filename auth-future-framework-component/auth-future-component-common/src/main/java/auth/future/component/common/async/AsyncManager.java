package auth.future.component.common.async;

import auth.future.component.common.utils.SpringUtil;
import auth.future.component.common.utils.ThreadUtil;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 异步任务管理器
 */
public class AsyncManager {
    /**
     * 操作延迟10毫秒
     */
    private static final int OPERATE_DELAY_TIME = 10;

    /**
     * 异步操作任务调度线程池
     */
    private final ScheduledExecutorService executor =SpringUtil.getBean(ScheduledExecutorService.class);

    /**
     * 单例模式
     */
    private AsyncManager(){}

    private static final AsyncManager ME = new AsyncManager();

    public static AsyncManager getInstance() {
        return ME;
    }

    /**
     * 执行任务
     * 
     * @param task 任务
     */
    public void execute(TimerTask task) {
        executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 停止任务线程池
     */
    public void shutdown() {
        ThreadUtil.shutdownAndAwaitTermination(executor);
    }
}
