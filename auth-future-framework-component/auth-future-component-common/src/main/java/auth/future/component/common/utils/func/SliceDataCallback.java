package auth.future.component.common.utils.func;

@FunctionalInterface
public interface SliceDataCallback {

    Object process(int index, byte[] data) throws Exception;

}
