package ru.util.listener;

import ru.entity.User;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

import java.util.ArrayList;
import java.util.List;

@WebListener
public class SessionUserListener implements HttpSessionAttributeListener {
    private final User currentUser = new User();
    private final List<User> userList = new ArrayList<>();

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if (event.getSession().getAttribute("currentId") != null &&
                event.getSession().getAttribute("currentLogin") != null
                && event.getSession().getAttribute("currentRole") != null
        ) {
            currentUser.setId((long) event.getSession().getAttribute("currentId"));
            currentUser.setLogin((String) event.getSession().getAttribute("currentLogin"));
            currentUser.setRole((String) event.getSession().getAttribute("currentRole"));
            userList.add(currentUser);
            event.getSession().setAttribute("currentUser", currentUser);
            event.getSession().removeAttribute("currentLogin");
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if (event.getSession().getAttribute("currentId") != null &&
                event.getSession().getAttribute("currentLogin") != null
                && event.getSession().getAttribute("currentRole") != null
                && event.getSession().getAttribute("currentNickname") != null
        ) {
            currentUser.setId((long) event.getSession().getAttribute("currentId"));
            currentUser.setLogin((String) event.getSession().getAttribute("currentLogin"));
            currentUser.setRole((String) event.getSession().getAttribute("currentRole"));
            currentUser.setNickname((String) event.getSession().getAttribute("currentNickname"));
            userList.add(currentUser);
            event.getSession().setAttribute("currentUser", currentUser);
            event.getSession().removeAttribute("currentLogin");
        }
    }
}
