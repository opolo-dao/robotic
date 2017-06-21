package lv.challenge.servlets.mvc;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Created by Daniil on 21.06.2017.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StateData {
private MemberForms memberForms = new MemberForms();
    public void changeToken(){
        memberForms.token++;
    }
    public String getToken(){
        return Integer.toString(memberForms.token);
    }

    private class MemberForms{
        int token;
    }
}
