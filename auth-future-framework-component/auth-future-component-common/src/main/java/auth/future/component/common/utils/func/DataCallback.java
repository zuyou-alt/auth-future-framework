package auth.future.component.common.utils.func;

@FunctionalInterface
public interface DataCallback {

    void process(byte[] data) throws Exception;
}
