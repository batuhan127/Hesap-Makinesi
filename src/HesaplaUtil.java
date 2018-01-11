import java.util.ArrayList;
import java.util.List;

public class HesaplaUtil {

    public static Double hesapla(String yapilacakIslem) {
        double x = 0;
        double y = 0;
        char islem = ' ';
        char islemtoarray[] = yapilacakIslem.toCharArray();
        for (int i = 1; i < yapilacakIslem.toCharArray().length; i++) {
            if (HesaplaUtil.rakamMi(islemtoarray[i])) {
                continue;
            } else {
                x = Double.parseDouble(yapilacakIslem.substring(0, i));
                islem = yapilacakIslem.substring(i, i + 1).toCharArray()[0];
                y = Double.parseDouble(yapilacakIslem.substring(i + 1));
                break;
            }
        }
        switch (islem) {
            case '+':
                return x + y;
            case '-':
                return x - y;
            case '^':
                if (y % 2 == 0)
                    return x / Math.abs(x) * Math.pow(x, y);
                else
                    return Math.pow(x,y);
            case '*':
                return x * y;
            case '/':
                return x / y;
            default:
                return null;
        }
    }

    static Boolean rakamMi(Character c) {
        if (c.equals('.') || c.equals('0') || c.equals('1') || c.equals('2') || c.equals('3') || c.equals('4') || c.equals('5') || c.equals('6') || c.equals('7') || c.equals('8') || c.equals('9')) {
            return true;
        } else {
            return false;
        }

    }

    public static String duzenle(String test) {
        char c[] = test.toCharArray();
        List<Character> asd = new ArrayList<Character>();
        for (char x : c) {
            asd.add(x);
        }
        if (asd.contains('^')) {
            test = HesaplaUtil.islemibul('^', test);
            return duzenle(test);
        }

        if (asd.contains('/')) {
            test = HesaplaUtil.islemibul('/', test);
            return duzenle(test);
        }
        if (asd.contains('*')) {
            test = HesaplaUtil.islemibul('*', test);
            return duzenle(test);
        }

        if (asd.get(0).equals('-') || asd.get(0).equals('+')) {
            asd.remove(0);
        }
        if (asd.contains('-')) {
            test = HesaplaUtil.islemibul('-', test);
            return duzenle(test);
        }
        if (asd.contains('+')) {
            test = HesaplaUtil.islemibul('+', test);
            return duzenle(test);
        }


        return test;
    }

    public static String islemibul(Character islem, String test) {
        char c[] = test.toCharArray();
        int baslama = 0;
        int bitis = test.length();
        for (int i = 0; i < c.length; i++) {
            if (i == 0 && c[i] == islem)
                continue;
            if (c[i] == islem) {
                for (int j = i - 1; j > 0; j--) {
                    if (j == i - 1 && !HesaplaUtil.rakamMi(c[i - 1])) {
                        continue;
                    }
                    if (!HesaplaUtil.rakamMi(c[j])) {
                        baslama = j + 1;
                        break;
                    }
                }
                for (int j = i + 1; j < c.length; j++) {
                    if (j == i + 1 && !HesaplaUtil.rakamMi(c[i + 1])) {
                        continue;
                    }
                    if (!HesaplaUtil.rakamMi(c[j])) {
                        bitis = j;
                        break;
                    }
                }
                break;
            }
        }
        Double y = HesaplaUtil.hesapla(test.substring(baslama, bitis));
        StringBuilder s = new StringBuilder();
        s.append(test.substring(0, baslama)).append(y).append(test.substring(bitis));
        return s.toString();
    }
}
