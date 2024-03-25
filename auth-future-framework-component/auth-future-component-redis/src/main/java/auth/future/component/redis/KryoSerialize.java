package auth.future.component.redis;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.JavaSerializer;

import java.io.*;
import java.util.List;

public class KryoSerialize  {
    /**
     * 将对象序列化为字节
     * @param o 泛型对象
     * @return 序列化后的字节数组
     * @param <T> 必须实现序列化接口的对象
     */
    public static <T extends Serializable> byte[] serialize(T o) {
        Kryo kryo = new Kryo();
        kryo.register(o.getClass(),new JavaSerializer());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Output output = new Output(bos);
        kryo.writeObject(output,o);
        output.close();
        try {
            bos.flush();
            bos.close();
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将字节反序列化为对象
     * @param bytes 需要反序列化的字节数组
     * @param tClass 反序列化的目标对象
     */
    public static <T extends Serializable> T deserialize(byte[] bytes,Class<T> tClass){
        if (bytes==null) return null;
        Kryo kryo = new Kryo();
        kryo.register(tClass,new JavaSerializer());
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Input input = new Input(bis);
        T t = kryo.readObject(input, tClass);
        input.close();
        try {
            bis.close();
            return t;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 集合强制转换
     * @param object 需要转换的集合
     * @param tClass 转换的目标集合的类型
     */
    public static <T> List<T> objToList(Object object,Class<T> tClass){
        if (object instanceof List<?> objectList){
            return  objectList.stream().map(tClass::cast).toList();
        }
        return null;
    }
}