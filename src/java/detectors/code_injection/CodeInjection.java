/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.code_injection;

import javax.servlet.http.HttpServletRequest;
import javax.script.ScriptException;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

public class CodeInjection {

    // {fact rule=code-injection@v1.0 defects=1}
    public void evaluateJavaScriptNoncompliant(HttpServletRequest request) throws ScriptException {
        final String parameter = request.getParameter("parameter");
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        // Noncompliant: user-supplied parameter evaluated as a script.
        engine.eval(parameter);
    }
    // {/fact}

    // {fact rule=code-injection@v1.0 defects=0}
    public void evaluateJavaScriptCompliant(HttpServletRequest request) throws ScriptException {
        final String parameter = request.getParameter("parameter");
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        // Compliant: user-supplied parameter must be in allow-list to be evaluated.
        if (!parameter.matches("[\\w]+")) {
            // String does not match allow-listed characters
            throw new IllegalArgumentException();
        }
        engine.eval(parameter);
    }
    // {/fact}

}
