package auth.future.component.common.utils;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 获取IP方法
 * @author Hzy
 */
public class IpUtils {

    // IP的正则
    private static final Pattern PATTERN = Pattern .compile("(1\\d{1,2}|2[0-4]\\d|25[0-5]|\\d{1,2})\\."
            + "(1\\d{1,2}|2[0-4]\\d|25[0-5]|\\d{1,2})\\."
            + "(1\\d{1,2}|2[0-4]\\d|25[0-5]|\\d{1,2})\\."
            + "(1\\d{1,2}|2[0-4]\\d|25[0-5]|\\d{1,2})");


    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    public static String getHostIp(){
        try{
            return InetAddress.getLocalHost().getHostAddress();
        }catch (UnknownHostException e){
            return "127.0.0.1";
        }
    }

    public static String getHostName(){
        try{
            return InetAddress.getLocalHost().getHostName();
        }catch (UnknownHostException e){
            return "未知";
        }
    }

    //校验Ip是否包含在Ip段内
    public static boolean ipIsValid(String ipSection, String ip) {
        if (ipSection == null) {
            throw new NullPointerException("IP段不能为空！");
        }
        if (ip == null) {
            throw new NullPointerException("IP不能为空！");
        }
        ipSection = ipSection.trim();
        ip = ip.trim();
        final String REGX_IP ="((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";
        final String REGX_IPB = REGX_IP + "\\-" + REGX_IP;
        if (!ipSection.matches(REGX_IPB) || !ip.matches(REGX_IP)) {
            return false;
        }
        int idx = ipSection.indexOf('-');
        idx = idx < 0 ? ipSection.length() : idx;
        String[] sips = ipSection.substring(0, idx).split("\\.");
        String[] sipe = ipSection.substring(idx + 1).split("\\.");
        String[] sipt = ip.split("\\.");
        long ips = 0L, ipe = 0L, ipt = 0L;
        for (int i = 0; i < 4; ++i) {
            ips = ips << 8 | Integer.parseInt(sips[i]);
            ipe = ipe << 8 | Integer.parseInt(sipe[i]);
            ipt = ipt << 8 | Integer.parseInt(sipt[i]);
        }
        if (ips > ipe) {
            long t = ips;
            ips = ipe;
            ipe = t;
        }
        return ips <= ipt && ipt <= ipe;
    }

    /**
     *
     * getAvaIpList:(根据IP白名单设置获取可用的IP列表).
     */
    private static Set<String> getAvaIpList(String allowIp) {
        Set<String> ipList = new HashSet<>();
        for (String allow : allowIp.replaceAll("\\s", "").split(";")) {
            if (allow.contains("*")) {
                String[] ips = allow.split("\\.");
                String[] from = new String[] { "0", "0", "0", "0" };
                String[] end = new String[] { "255", "255", "255", "255" };
                List<String> tem = new ArrayList<>();
                for (int i = 0; i < ips.length; i++)
                    if (ips[i].contains("*")) {
                        tem = complete(ips[i]);
                        from[i] = null;
                        end[i] = null;
                    } else {
                        from[i] = ips[i];
                        end[i] = ips[i];
                    }
                StringBuffer fromIP = new StringBuffer();
                StringBuffer endIP = new StringBuffer();
                for (int i = 0; i < 4; i++)
                    if (from[i] != null) {
                        fromIP.append(from[i]).append(".");
                        endIP.append(end[i]).append(".");
                    } else {
                        fromIP.append("[*].");
                        endIP.append("[*].");
                    }
                fromIP.deleteCharAt(fromIP.length() - 1);
                endIP.deleteCharAt(endIP.length() - 1);
                for (String s : tem) {
                    String ip = fromIP.toString().replace("[*]",
                            s.split(";")[0])
                            + "-"
                            + endIP.toString().replace("[*]", s.split(";")[1]);
                    if (validate(ip)) {
                        ipList.add(ip);
                    }
                }
            } else {
                if (validate(allow)) {
                    ipList.add(allow);
                }
            }
        }
        return ipList;
    }

    private static Set<String> getAvaliIpList(Set<String> ipSet) {
        Set<String> ipList = new HashSet<String>();
        for (String allow : ipSet) {
            if (allow.contains("*")) {
                String[] ips = allow.split("\\.");
                String[] from = new String[] { "0", "0", "0", "0" };
                String[] end = new String[] { "255", "255", "255", "255" };
                List<String> tem = new ArrayList<>();
                for (int i = 0; i < ips.length; i++)
                    if (ips[i].contains("*")) {
                        tem = complete(ips[i]);
                        from[i] = null;
                        end[i] = null;
                    } else {
                        from[i] = ips[i];
                        end[i] = ips[i];

                    }
                StringBuffer fromIP = new StringBuffer();
                StringBuffer endIP = new StringBuffer();
                for (int i = 0; i < 4; i++)
                    if (from[i] != null) {
                        fromIP.append(from[i]).append(".");
                        endIP.append(end[i]).append(".");
                    } else {
                        fromIP.append("[*].");
                        endIP.append("[*].");
                    }
                fromIP.deleteCharAt(fromIP.length() - 1);
                endIP.deleteCharAt(endIP.length() - 1);
                for (String s : tem) {
                    String ip = fromIP.toString().replace("[*]",
                            s.split(";")[0])
                            + "-"
                            + endIP.toString().replace("[*]", s.split(";")[1]);
                    if (validate(ip)) {
                        ipList.add(ip);
                    }
                }
            } else {
                if (validate(allow)) {
                    ipList.add(allow);
                }
            }
        }
        return ipList;
    }

    /**
     * 对单个IP节点进行范围限定
     */
    private static List<String> complete(String arg) {
        List<String> com = new ArrayList<String>();
        if (arg.length() == 1) {
            com.add("0;255");
        } else if (arg.length() == 2) {
            String s1 = complete(arg, 1);
            if (s1 != null)
                com.add(s1);
            String s2 = complete(arg, 2);
            if (s2 != null)
                com.add(s2);
        } else {
            String s1 = complete(arg, 1);
            if (s1 != null)
                com.add(s1);
        }
        return com;
    }



    private static String complete(String arg, int length) {
        String from = "";
        String end = "";
        if (length == 1) {
            from = arg.replace("*", "0");
            end = arg.replace("*", "9");
        } else {
            from = arg.replace("*", "00");
            end = arg.replace("*", "99");
        }
        if (Integer.parseInt(from) > 255)
            return null;
        if (Integer.parseInt(end) > 255)
            end = "255";
        return from + ";" + end;
    }



    /**
     * 在添加至白名单时进行格式校验
     */
    private static boolean validate(String ip) {
        for (String s : ip.split("-"))
            if (!PATTERN.matcher(s).matches()) {
                return false;
            }
        return true;
    }

    /**
     *
     * checkLoginIP:(根据IP,及可用Ip列表来判断ip是否包含在白名单之中).
     */
    private static boolean checkLoginIP(String ip, Set<String> ipList) {
        if (ipList.contains(ip))
            return true;
        else {
            for (String allow : ipList) {
                if (allow.contains("-")) {
                    String[] from = allow.split("-")[0].split("\\.");
                    String[] end = allow.split("-")[1].split("\\.");
                    String[] tag = ip.split("\\.");
                    // 对IP从左到右进行逐段匹配
                    boolean check = true;
                    for (int i = 0; i < 4; i++) {
                        int s = Integer.parseInt(from[i]);
                        int t = Integer.parseInt(tag[i]);
                        int e = Integer.parseInt(end[i]);
                        if (!(s <= t && t <= e)) {
                            check = false;
                            break;
                        }
                    }
                    if (check) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    /**
     *
     * checkLoginIP:(根据IP地址，及IP白名单设置规则判断IP是否包含在白名单).
     * @param ip 要验证的IP
     * @param ipWhiteConfig ip配置
     * @return true 包含，false 不包含
     */
    public static boolean checkLoginIP(String ip,String ipWhiteConfig){
        Set<String> ipList = getAvaIpList(ipWhiteConfig);
        return checkLoginIP(ip, ipList);
    }

    /**
     *
     * ip在ipList中，则返回true
     */
    public static boolean checkIpList(String ip,List<String> ipList){
        Set<String> ipSet = new HashSet<>();
        for(String ipStr : ipList){
            if(!ipStr.trim().startsWith("#")){
                ipSet.add(ipStr.trim());
            }
        }
        ipSet = getAvaliIpList(ipSet);
        return checkLoginIP(ip, ipSet);
    }

    // 测试

    public static void main(String[] args) {
        String ipWhilte = "192.168.1.1;" +                 //设置单个IP的白名单
                "192.168.2.*;" +                 //设置ip通配符,对一个ip段进行匹配
                "192.168.3.17-192.168.3.38";     //设置一个IP范围
        System.out.println(ipWhilte);
        boolean flag = checkLoginIP("192.168.2.2",ipWhilte);
        boolean flag2 = checkLoginIP("192.168.1.2",ipWhilte);
        boolean flag3 = checkLoginIP("192.168.3.16",ipWhilte);
        boolean flag4 = checkLoginIP("192.168.3.17",ipWhilte);
        System.out.println(flag);  //true
        System.out.println(flag2);  //false
        System.out.println(flag3);  //false
        System.out.println(flag4);  //true

    }
}
