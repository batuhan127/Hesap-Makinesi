import java.util.Scanner;

public class TestClass {
    public String hesapla(String test) {
        char[] c = test.toCharArray();
        int baslama = 0;
        int bitis = 0;
        int acmaSayisi=0;
        int kapamaSayisi=0;
        try {
            for(int i = 0; i < c.length; i++){
                if (c[i] == '(')
                    acmaSayisi+=1;
                else if(c[i] == ')')
                    kapamaSayisi +=1;
            }
            if(acmaSayisi != kapamaSayisi){
                throw new Exception();
            }
            for (int i = 0; i < c.length; i++) {
                if (c[i] == '(') {
                    baslama = i;
                } else if (c[i] == ')') {
                    bitis = i;
                    String asd = HesaplaUtil.duzenle(test.substring(baslama + 1, bitis));
                    if(i != c.length-1 && c[i+1] == '^'){
                        bitis = bitis+2;
                        asd = String.valueOf(Math.pow(Double.parseDouble(asd),Integer.parseInt(String.valueOf(c[i+2]))));
                    }
                    test = test.replace(test.substring(baslama, bitis + 1), asd);
                    break;
                }
            }
            if (test.substring(0, 1).equals("("))
                return hesapla(test);

            return test;
        }
        catch (Exception e){
            return "İşleminizi hatalı girdiniz";
        }

    }

    public static void main(String[] args) {
        String devam = "e";
        while (devam.equals("e")) {
            System.out.println("işleminizi girin:");
            Scanner sc = new Scanner(System.in);
            String islem = sc.nextLine();
            islem = islem.replaceAll("\\s", "");
            StringBuilder s = new StringBuilder();
            islem = s.append("(").append(islem).append(")").toString();
            TestClass t = new TestClass();
            System.out.println(t.hesapla(islem));
            System.out.println("Devam etmek istiyor musunuz? e/h");
            sc = new Scanner(System.in);
            devam = sc.next();
        }
    }
}

