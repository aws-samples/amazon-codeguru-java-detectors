package rules.missing_getcause_on_invocationtargetexception;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MissingGetCauseOnInvocationTargetException {

    // {fact rule=missing-getcause-on-invocationtargetexception@v1.0 defects=1}
    public Object invokeMethodNonCompliant(Method method, Object[] args) throws Throwable {
        // Noncompliant: InvocationTargetException is not caught.
        return method.invoke(args);
    }
    // {/fact}

    // {fact rule=missing-getcause-on-invocationtargetexception@v1.0 defects=0}
    public Object invokeMethodCompliant(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            // Compliant: InvocationTargetException is caught and e.getCause() is propagated further.
            Object returnValue = method.invoke(args);
            return returnValue;
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }
    // {/fact}
}
