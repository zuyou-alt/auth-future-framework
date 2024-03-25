package auth.future.component.common.utils.func;

@FunctionalInterface
public interface ProcessCallback {

    Object process() throws RuntimeException;
}
