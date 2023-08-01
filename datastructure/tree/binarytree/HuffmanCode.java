package datastructure.tree.binarytree;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

/**
 * Huffman树功能集合
 *
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 * @create 2020-11-03 21:29
 */
public class HuffmanCode {
    public static void main(String[] args) {
//        String content = "great again???";
        String content = "i like like java do you like a java?";
        byte[] contentBytes = content.getBytes();
//        System.out.println(contentBytes.length);

        List<Node1> nodes = getNodes(contentBytes);
        Node1 huffmanTreeRoot = createHuffmanTree(nodes);

        getCodes(huffmanTreeRoot, "", stringBuilder);
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        System.out.println(huffmanCodes);
        //生成的Huffman编码表：左边为字符的byte值的十进制表示,右边为表示Huffman路径的字符串;
        //"make america great again?"的huffman编码表:
//{32=001, 97=01, 114=1011, 99=11110, 116=11111, 101=100, 103=1100, 105=1101, 107=0000, 109=1110, 110=0001, 63=1010}


        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
        System.out.println(Arrays.toString(huffmanCodeBytes));
        //"make america great again?"按照huffman编码表转换为byte[]后的结果:
//[-28, 33, 122, 94, -7, 57, 113, -7, 113, -47, 10]
        //对应的二进制字符串
//1101 01 0000 100 001 01 1101 100 1010 1100 11110 01 001 101 1101 01 0001 11111 001 01 1011 01 1100 0001 11111 11111 11111 11111 11111 1011 10


        //测试封装后的huffmanZip();
//        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
//        System.out.println("压缩后的字节数组为：" + Arrays.toString(huffmanCodeBytes));

        //测试解压
        byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("原字符串为：" + new String(sourceBytes));
    }

    //1.将由字符串转换而来字节数组转换为一个Node1列表;
    private static List<Node1> getNodes(byte[] bytes) {
        ArrayList<Node1> nodes = new ArrayList<>();

        //遍历bytes，统计每一个byte出现的次数->map[key value]
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        //把每一个键值对转换为Node对象
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node1(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }

    //2.通过List<Node1>创建对应的Huffman树
    private static Node1 createHuffmanTree(List<Node1> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node1 left = nodes.get(0);
            Node1 right = nodes.get(1);
            //创建一棵新的二叉树，它的根节点没有data只有weight；
            Node1 parent = new Node1(null, left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            //删除左右节点
            nodes.remove(left);
            nodes.remove(right);
            //将新的二叉树添加到List中
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    //3.生成Huffman编码表
    //(1). 将Huffman编码表存放在Map<Byte, String>形式，其中String用于存储路径
    static Map<Byte, String> huffmanCodes = new HashMap<>();

    //(2). 定义一个StringBuilder用于拼接路径
    static StringBuilder stringBuilder = new StringBuilder();

    //(3). 定义一个方法将传入的node节点的所有叶子节点的Huffman路径添加到huffmanCodes中

    /**
     * 获得传入的node节点的所有叶子节点的赫夫曼编码,并放入HuffmanCodes中;
     *
     * @param node          传入的节点;
     * @param code          路径：左子节点为"0",右子节点为"1";
     * @param stringBuilder 用于拼接路径;
     */
    private static void getCodes(Node1 node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder2中;
        stringBuilder2.append(code);
        if (node != null) {//node不为空才进行处理,否则不进行处理;
            if (node.data == null) {//处理非叶子结点
                //向左递归;
                getCodes(node.left, "0", stringBuilder2);
                //向右递归;
                getCodes(node.right, "1", stringBuilder2);
            } else {//处理叶子节点
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    //(4). 为了调用方便，重载getCodes;
    private static Map<Byte, String> getCodes(Node1 root) {
        if (root == null) {
            return null;
        }
        //需要考虑只有一个root节点的情形
        if (root.left == null && root.right == null) {
            getCodes(root, "0", stringBuilder);
        } else {
            getCodes(root.left, "0", stringBuilder);
            getCodes(root.right, "1", stringBuilder);
        }
        return huffmanCodes;
    }

    //4. 编写一个方法,把某个字符串对应的byte[]数组,通过Huffman编码表,返回压缩后的byte[]数组
    //例如字符串的Huffman编码的第一个字节为:
    //  huffmanCodes[0] = 10101000(补码) => 10101000 - 1 = 10100111(反码) => 11011000(原码) == -88
    //
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //(1) 利用Huffman编码表将bytes转换为对应的字符串;
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        //(2) 将字符串转化为对应的byte[]
        //  a.统计返回的字符串的长度;
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //也可写成:
        //int len = (stringBuilder.length() + 7) / 8;

        //(3) 创建一个存储压缩后的byte[]
        //    修正：在末尾增加一个byte用于描述最后一位byte中有几位有效bit;
        byte[] huffmanCodeBytes = new byte[len + 1];
        //    默认8位,否则默认0位,会导致诸如11101100一类只有一个字节的huffmanCodeBytes被全部truncated
        huffmanCodeBytes[len] = 8;

        //(4) 把stringBuilder中的字符串每8位转换成一个byte存入huffmanCodeBytes;
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {//末尾可能不够8位,将末尾取尽;
                strByte = stringBuilder.substring(i);
                //判断末尾的strByte的有效bit数,保存到byte[]末尾添加的标记字节中;
                huffmanCodeBytes[len] = (byte) strByte.length();
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte转换成一个byte,放入huffmanCodeBytes
            //需要考虑一个问题：strByte可能是以0开头的字符串如“00100111”,此时Integer.parseInt会忽略开头的0,返回100111;
            //用(byte)将一个int型十进制数i强转为时,先将i转换为32位二进制表示,然后截取最后8位:
            //int型十进制100111 -> 二进制0000 0000 0000 0001 1000 0111 0000 1111 -> 截取后8位得到:0000 1111
            huffmanCodeBytes[index++] = (byte) Integer.parseInt(strByte, 2);
        }
        return huffmanCodeBytes;
    }

    //5. 将以上方法封装起来,方便调用;
    private static byte[] huffmanZip(byte[] bytes) {
        //(1) 根据bytes创建一个Node1列表
        List<Node1> nodes = getNodes(bytes);
        //(2) 根据Node1创建一个Huffman树,获得其root;
        Node1 huffmanTreeRoot = createHuffmanTree(nodes);
        //(3) 根据Huffman树创建编码表;
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //(4) 根据bytes和编码表创建一个压缩后的byte[]并返回;
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    //5. 完成数据的解压：现将HuffmanCodeBytes转换成对应的二进制数字符串,再将字符串根据编码表转换成原字符串
/*
    //(1) 创建一个方法,将一个byte转换为string
    private static String byteToBitString(boolean flag, byte b){
        //使用temp保存b;相当于把byte型的b扩展为int型的temp,
        int temp = b;
        //1. 当传入的byte b不是byte[] huffmanCodeBytes的最后一个元素时,flag = true;
        if (flag) {
            //256 == 0000 0000 0000 0000 0000 0001 0000 0000
            //20 == 1 0100
            //20 | 256 == 0001 0001 0100 == 十进制276
            // 按位或运算
            temp |= 256;
            // (1) 用于给正数temp补位,例如从byte[] 中取出的20以二进制表示是10100,当初存储时高位的3个0被省略了;
            //  转换为int型temp == 0000 0000 0000 0000 0000 0000 0001 0100
            //  经过temp |= 256补高位运算后,temp变为二进制0000 0000 0000 0000 0000 0001 0001 0100;
            //  接下来只需截取后8位再转换为字符串就可以得到"00010100"这个字符串;

            // (2) 如果byte[]中有一个负数-60,即二进制下的1100 0100
            //  转换为int型temp == 1111 1111 1111 1111 1111 1111 1100 0100
            //  经过temp |= 256补高位运算后,temp保持不变1111 1111 1111 1111 1111 1111 1100 0100
            //  (原因是int型256的二进制是0000 0000 0000 0000 0000 0001 0000 0000,
            //  temp |= 256只会改变第9位,而负数-60的第9位已是1,所以保持不变)
        }
        //2. 如果传入的byte b是原数组byte[]的最后一个元素,则可能是以下情况
        //  (1) 如果当初存储到byte[]中的该元素满8位如1000 1000或0000 0000,则会直接存入;
        //      取出后分别转换为temp1 == 1111 1111 1111 1111 1111 1111 1000 1000 == -120
        //                    temp2 == 0000 0000 0000 0000 0000 0000 0000 0000 == 0
        //      然后Integer.toBinaryString(temp1)会返回"1111 1111 1111 1111 1111 1111 1000 1000"作为str,显然不符合要求;
        //          Integer.toBinaryString(temp2)会返回"0",显然不符合要求
        //      ==> 可以采取temp =| 256按位或运算进行调整;
        //  (2) 如果当初存储到byte[]中的该元素不满8位、不全为0、且首位不为0如10100,则会补齐为正数0001 0100再存入;
        //      取出后转换为temp == 0000 0000 0000 0000 0000 0000 0001 0100
        //      然后Integer.toBinaryString(temp)会返回"10100"作为str,符合转换要求;
        //      ==> 此时不可以采取按位或运算,否则会多出高位的0;
        //  (3) 如果当初存储到byte[]中的该元素不满8位、不全为0、且首位为0如0110,则会补齐为0000 0110;
        //      取出后转换为temp == 0000 0000 0000 0000 0000 0000 0000 0110
        //      然后Integer.toBinaryString(temp)会返回"110"作为str,显然不符合要求;
        //      ==> 此时不可以采取按位或运算,否则会多出高位的0;
        //  (4) 如果当初存储到byte[]中的该元素不满8位且全为0,如0 0000,则会作为0000 0000存入;
        //      取出后转换为temp == 0000 0000 0000 0000 0000 0000 0000 0000 == 0
        //      然后Integer.toBinaryString(temp)会返回"0"作为str,显然不符合要求;

        // 总结：可以在创建byte[]数组时多增添一个byte用于描述最后一个byte有多少位有效bit;

        String str = Integer.toBinaryString(temp);
        if (flag){
            //如果传入的byte b在转换为temp后进行了temp |= 256运算,则截取后8位
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }
*/
    //修正后的byteToBitString()
    public static String byteToBitString(boolean flag, byte b, byte lastByteFlag) {
        String str = "";
        int temp = b;
        if (flag) {
            temp |= 256;
        } else {//处理到倒数第二个byte时,根据lastByteFlag判断它有几位有效bit
            if (lastByteFlag == 8) {//如果满8位只需temp =| 256按位或即可,
                flag = true;
            } else {//否则要进行truncate
                //根据有效bit的位数创建用于进行按位与运算的orBit
                //1. 通过*2运算将temp的低位补齐,例如 0110 -> 0110 0000
                int count = lastByteFlag;
                while (count++ < 8) {
                    temp *= 2;
                }
                //2. 通过temp |= 256将temp的第9位补上1 0110 0000 -> 0001 0110 0000
                temp |= 256;
                //3. 利用Integer.toBinaryString(temp)获取共9位字符串;"101100000"
                str = Integer.toBinaryString(temp);
                //4. 返回其中(1,1+lastByteFlag)范围的substring;"0110"
                return str.substring(1, 1 + lastByteFlag);
            }
        }

        str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        }
        return str;
    }


    //(2) 编写一个方法，完成对压缩数据的解码
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanCodeBytes) {
        // a. 获得huffmanCodeBytes对应的二进制字符串;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanCodeBytes.length - 1; i++) {//由于byte[]多出一个标记元素,因此遍历只需到倒数第二位;
            //判断是否为最后倒数第二个字节
            boolean flag = (i == huffmanCodeBytes.length - 2);
            stringBuilder.append(byteToBitString(!flag, huffmanCodeBytes[i], huffmanCodeBytes[huffmanCodeBytes.length - 1]));
        }

        //b. 把获得的字符串按照编码表进行解码
        //  利用HashMap进行反向查询，创建一个新的HashMap进行接收;
        HashMap<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //c. 创建一个集合存放stringBuilder中的byte
        ArrayList<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;//扫描计数器
            boolean flag = true;
            Byte b = null;

            while (flag) {
                //递增地取出'1'/'0';
                String key = stringBuilder.substring(i, i + count);//尽量匹配key
                b = map.get(key);
                if (b == null) {//说明没有匹配到
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i直接后移count位;
        }
        //for循环结束后,list中就存放了所有字符
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    //6. 编写方法，将一个文件压缩
    public static void zipFile(String srcFile, String dstFile) {

        FileInputStream fis = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            //创建文件输入流;
            fis = new FileInputStream(srcFile);
            //创建一个和源文件大小相同的byte[]
            byte[] buffer = new byte[fis.available()];
            //读取文件,存入byte[]中;
            fis.read(buffer);
            //使用Huffman编码对源文件进行压缩;
            byte[] huffmanCodeBytes = huffmanZip(buffer);
            //创建文件的输出流
            fos = new FileOutputStream(dstFile);
            //创建一个和文件输出流相关的ObjectOutputStream;以便写入huffmanCodes作为解码工具;
            //以对象流的方式写入huffmanCodeBytes,以便以后恢复源文件;
            oos = new ObjectOutputStream(fos);
            oos.writeObject(huffmanCodeBytes);
            //注意一定要将huffmanCodes写入,作为解码工具;
            oos.writeObject(huffmanCodes);
            new ObjectOutputStream(fos);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    //7. 创建一个方法解压压缩文件
    public static void unzipFile(String zipFile, String dstFile) {
        //创建文件输入流;
        FileInputStream fis = null;
        //创建相关联的对象输入流;
        ObjectInputStream ois = null;
        //创建文件输出流
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(zipFile);
            ois = new ObjectInputStream(fis);
            //读取byte[] huffmanCodeBytes
            byte[] huffmanCodeBytes = (byte[]) ois.readObject();
            //读取编码表HuffmanCodes
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //进行解码
            byte[] decodedBytes = decode(huffmanCodes, huffmanCodeBytes);
            fos = new FileOutputStream(dstFile);
            fos.write(decodedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

class Node1 implements Comparable<Node1> {
    Byte data;
    int weight;
    Node1 left;
    Node1 right;

    public Node1(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override
    public int compareTo(@NotNull Node1 o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node1{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}
