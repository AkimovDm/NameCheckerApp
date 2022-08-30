package com.example.NameChecker;

public class InputHandler {

    public static String checkForMarks(String input){

        StringBuilder result=new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            if(Character.isAlphabetic(input.charAt(i)))
                result.append(input.charAt(i));
        }


        return result.toString();
    }


        public static String translitirate(String input) {

            char[] charArray = input.toLowerCase().trim().toCharArray();

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < charArray.length; i++) {

                if (charArray[i] == 'а') result.append("a");
                else if (charArray[i] == 'б') result.append("b");
                else if (charArray[i] == 'в') result.append("v");
                else if (charArray[i] == 'г') result.append("g");
                else if (charArray[i] == 'д') result.append("d");
                else if (charArray[i] == 'е') result.append("e");
                else if (charArray[i] == 'ё') result.append("e");
                else if (charArray[i] == 'ж') result.append("zh");
                else if (charArray[i] == 'з') result.append("z");
                else if (charArray[i] == 'и') result.append("i");
                else if (charArray[i] == 'й') result.append("y");
                else if (charArray[i] == 'к') result.append("k");
                else if (charArray[i] == 'л') result.append("l");
                else if (charArray[i] == 'м') result.append("m");
                else if (charArray[i] == 'н') result.append("n");
                else if (charArray[i] == 'о') result.append("o");
                else if (charArray[i] == 'п') result.append("p");
                else if (charArray[i] == 'р') result.append("r");
                else if (charArray[i] == 'с') result.append("s");
                else if (charArray[i] == 'т') result.append("t");
                else if (charArray[i] == 'у') result.append("u");
                else if (charArray[i] == 'ф') result.append("f");
                else if (charArray[i] == 'х') result.append("kh");
                else if (charArray[i] == 'ц') result.append("ts");
                else if (charArray[i] == 'ч') result.append("ch");
                else if (charArray[i] == 'ш') result.append("sh");
                else if (charArray[i] == 'щ') result.append("sh");
                else if (charArray[i] == 'ъ') result.append("");
                else if (charArray[i] == 'ы') result.append("i");
                else if (charArray[i] == 'ь') result.append("");
                else if (charArray[i] == 'э') result.append("e");
                else if (charArray[i] == 'ю') result.append("yu");
                else if (charArray[i] == 'я') result.append("ya");
                else result.append(charArray[i]);
            }


            return result.toString();
        }
    }

