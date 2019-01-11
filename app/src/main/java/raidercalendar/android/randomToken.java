package raidercalendar.android;

import java.util.Random;

public class randomToken {

    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";

    public static String shortToken(int size){
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(size);
        for(int i=0;i<size;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }

}
