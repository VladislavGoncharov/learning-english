package vladislav_goncharov.learning_english.utils;

import javax.servlet.http.HttpServletRequest;

public class NumberOfCorrectAnswersUtil {

    public static String getNumberOfCorrectAnswersInSession(HttpServletRequest request) {
       String  quantity = (String) request.getSession().getAttribute("numberOfCorrectAnswers");

        if (quantity == null) {
            request.getSession().setAttribute("numberOfCorrectAnswers", "0");
            return "0";
        }
        return quantity;
    }

    public static void increaseNumberOfCorrectAnswersInSession(HttpServletRequest request) {
       String quantity = (String) request.getSession().getAttribute("numberOfCorrectAnswers");
       int i = Integer.parseInt(quantity) + 1;
       request.getSession().setAttribute("numberOfCorrectAnswers", String.valueOf(i));
    }

    public static void resetNumberOfCorrectAnswersInSession(HttpServletRequest request) {
        request.getSession().setAttribute("numberOfCorrectAnswers", "0");
    }

}