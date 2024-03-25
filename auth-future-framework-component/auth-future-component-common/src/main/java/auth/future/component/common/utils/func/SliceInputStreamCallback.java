package auth.future.component.common.utils.func;

import java.io.InputStream;

@FunctionalInterface
public interface SliceInputStreamCallback {

    void process(InputStream is);
}
