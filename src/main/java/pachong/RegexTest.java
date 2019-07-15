package pachong;

public class RegexTest {
    public static void main(String[] args) {
        String regex = "https://www.315jiage.cn/[^/]+/$";

   /*     [^/]
        {2}
        {3,}
        {3,8}
        {1,}
        +
        *
                \\w
    \\W
\\d  \\t \\n \\b \\s*/
        String[] urls = {
                "https://www.315jiage.cn/x-KeChuan/",
                "https://www.315jiage.cn/z-NanKe/",
                "https://www.315jiage.cn/b-HuGan/",
                "https://www.315jiage.cn/sss/test/",
        };
        for(String url:urls){
            System.out.println("url = " + url);
            System.out.println("match = " + url.matches(regex));
        }
    }
}
