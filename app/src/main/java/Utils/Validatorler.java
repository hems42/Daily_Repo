package Utils;

import android.telephony.PhoneNumberUtils;
import android.widget.EditText;

public class Validatorler {
    public static  boolean isValidTelNumber(String tel_number)
    {


        if(tel_number.startsWith("0")&&tel_number.length()==11)
        {
            return  true;
        }

        else
        {
            return false;
        }
    }

    public static  boolean isValidTelNumber(EditText tel_number)
    {
        String telnumber=tel_number.getText().toString();
        if(!telnumber.matches(""))
        {
            if(telnumber.substring(0)!="0")
            {
                tel_number.setError("numaranýn baþýna 0 koyun!!");
                return false;
            }
            else if(telnumber.length()!=11)
            {
                tel_number.setError("geçerli bir tel non girin!!");
                return false;
            }
            else
            {
                return true;
            }

        }

        else
        {

            tel_number.setError("bu alaný doldurun!!");
            return false;
        }

    }

    public static  boolean isValidTc_no(String TCNo) {
       // String TCNo = String.valueOf(tckno);

        //11 haneden olusmali.
        if (TCNo.length() != 11)
            return false;

        //1. 3. 5. 7. ve 9. hanelerin toplamý
        int sum1 =
                Character.getNumericValue(TCNo.charAt(0)) + Character.getNumericValue(TCNo.charAt(2)) +
                        Character.getNumericValue(TCNo.charAt(4)) + Character.getNumericValue(TCNo.charAt(6)) +
                        Character.getNumericValue(TCNo.charAt(8));
        // 2. 4. 6. ve 8. hanelerin toplamý
        int sum2 =
                Character.getNumericValue(TCNo.charAt(1)) + Character.getNumericValue(TCNo.charAt(3)) +
                        Character.getNumericValue(TCNo.charAt(5)) + Character.getNumericValue(TCNo.charAt(7));

        //ilk toplamin 7 katýndan, ikinci toplam cikartildiginda, sonucun 10’a bolumunden kalan, 10. haneyi vermeli.
        int tenthDigit = ((sum1 * 7) - sum2) % 10;
        if (tenthDigit != Character.getNumericValue(TCNo.charAt(9)))
            return false;

        //Ýlk on hanenin toplaminin 10'a bolumunden kalan onbirinci haneyi vermeli.
        int sum = sum1 + sum2 + Character.getNumericValue(TCNo.charAt(9));
        if ((sum % 10) != Character.getNumericValue(TCNo.charAt(10)))
            return false;

        return true;

    }


   /* public static boolean isMobileNumberValid(String phoneNumber)
    {
        boolean isValid = false;

        // Use the libphonenumber library to validate Number
        PhoneNumberUtils phoneUtil = PhoneNumberUtils.getInstance();
        PhoneNumber.PhoneNumber swissNumberProto =null ;
        try {
            swissNumberProto = phoneUtil.parse(phoneNumber, "CH");
        } catch (NumberParseException e) {
            System.err.println("NumberParseException was thrown: " + e.toString());
        }

        if(phoneUtil.isValidNumber(swissNumberProto))
        {
            isValid = true;
        }

        // The Library failed to validate number if it contains - sign
        // thus use regex to validate Mobile Number.
        String regex = "[0-9*#+() -]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }*/
}
