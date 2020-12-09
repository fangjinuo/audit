package com.jn.audit.servlet;

import com.jn.audit.core.AuditRequest;
import com.jn.audit.core.model.Principal;
import com.jn.audit.core.model.PrincipalType;
import com.jn.audit.core.principal.PrincipalExtractor;
import com.jn.langx.invocation.MethodInvocation;
import com.jn.langx.util.Strings;

import javax.servlet.http.HttpServletRequest;

public class ServletAuditEventPrincipalExtractor implements PrincipalExtractor<HttpServletRequest, MethodInvocation> {
    @Override
    public Principal get(AuditRequest<HttpServletRequest, MethodInvocation> wrappedRequest) {
        HttpServletRequest request = wrappedRequest.getRequest();
        Principal principal = new Principal();
        principal.setClientHost(request.getRemoteHost());
        principal.setClientIp(request.getRemoteAddr());
        principal.setClientPort(request.getRemotePort());
        java.security.Principal p = request.getUserPrincipal();
        String principalName = null;
        if (p != null) {
            principalName = p.getName();
        } else {
            principalName = request.getRemoteUser();
        }
        principal.setPrincipalId(request.getRemoteUser());
        principal.setPrincipalName(principalName);
        principal.setPrincipalType((Strings.isEmpty(request.getAuthType()) ? PrincipalType.anonymous : PrincipalType.authenticated).name());
        return principal;
    }
}
