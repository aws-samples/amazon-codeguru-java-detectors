/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.cross_site_scripting;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public class CrossSiteScripting {

    // {fact rule=cross-site-scripting@v1.0 defects=1}
    public ModelAndView inputSanitizationNonCompliant(@RequestParam String favoriteColor) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsp/example.jsp");
        // Noncompliant: user-supplied parameter might contain malicious content.
        modelAndView.addObject("preferredColor", favoriteColor);
        return modelAndView;
    }
    // {/fact}

    // {fact rule=cross-site-scripting@v1.0 defects=0}
    public ModelAndView inputSanitizationCompliant(@RequestParam String favoriteColor) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsp/example.jsp");
        // Compliant: user-supplied parameter must be in allow-list.
        if (favoriteColor.matches("[a-z]+")) {
            modelAndView.addObject("preferredColor", favoriteColor);
        } else {
            throw new IllegalArgumentException("Invalid color!");
        }
        return modelAndView;
    }
    // {/fact}

}
