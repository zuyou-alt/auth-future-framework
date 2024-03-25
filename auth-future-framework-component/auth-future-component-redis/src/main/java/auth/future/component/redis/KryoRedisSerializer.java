package auth.future.component.redis;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.ByteArrayOutputStream;
/**
 * 使用Kryo进行序列化
 * @author Hzy
 */
public class KryoRedisSerializer<T> implements RedisSerializer<T> {

    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    private static final ThreadLocal<Kryo> KRYO = ThreadLocal.withInitial(Kryo::new);

    private final Class<T> clazz;

    public KryoRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) return EMPTY_BYTE_ARRAY;
        Kryo kryo = KRYO.get();
        kryo.setReferences(true);
        kryo.setRegistrationRequired(false);
        kryo.register(clazz);
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();Output output = new Output(bos)) {
            kryo.writeClassAndObject(output, t);
            output.flush();
            return bos.toByteArray();
        } catch (Exception e) {
            throw new SerializationException(e.getMessage(),e);
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) return null;
        Kryo kryo = KRYO.get();
        kryo.setReferences(true);
        kryo.setRegistrationRequired(false);
        kryo.register(clazz);
        try (Input input = new Input(bytes)) {
           return clazz.cast(kryo.readClassAndObject(input));
        } catch (Exception e) {
            throw new SerializationException(e.getMessage(),e);
        }
    }


}