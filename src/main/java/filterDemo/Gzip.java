package filterDemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * 提供统一的压缩方法封装
 */
class Gzip {

    public byte[] GzipBytes(byte[] data) throws IOException {
        System.out.println("压缩前：" + data.length);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);

        //GZIP对数据压缩，GZIP写入的数据是保存在byteArrayOutputStream上的
        gzipOutputStream.write(data);
        gzipOutputStream.close();

        // 获取压缩后的byte数组
        byte[] result = byteArrayOutputStream.toByteArray();
        System.out.println("压缩后：" + result.length);
        return result;
    }
}
