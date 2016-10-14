package com.waytechs.view.security;

import com.waytechs.model.ejb.facades.AdPermissionFacade;
import com.waytechs.model.ejb.facades.AdUserFacade;
import com.waytechs.model.ejb.facades.AdUserRolesFacade;
import com.waytechs.model.entities.AdPermission;
import com.waytechs.model.entities.AdUser;
import com.waytechs.model.entities.AdUserRoles;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 * Created by nebrass on 17/11/2015.
 */
public class SecurityRealm extends AuthorizingRealm {

    private Logger logger;
    
    private AdUserFacade adUserFacade;
    
    private AdUserRolesFacade adUserRolesFacade;
    
    private AdPermissionFacade adPermissionFacade;

    public SecurityRealm() {
        super();
        this.logger = Logger.getLogger(SecurityRealm.class.getName());

        setAuthenticationCachingEnabled(Boolean.TRUE);

        try {
            InitialContext ctx = new InitialContext();
            String moduleName = (String) ctx.lookup("java:module/ModuleName");
            this.adUserFacade = (AdUserFacade) ctx.lookup("java:global/amlsystem-ear/amlsystem-ejb-1.0-SNAPSHOT/AdUserFacade");
            this.adUserRolesFacade = (AdUserRolesFacade) ctx.lookup("java:global/amlsystem-ear/amlsystem-ejb-1.0-SNAPSHOT/AdUserRolesFacade");
            
            this.adPermissionFacade = (AdPermissionFacade) ctx.lookup("java:global/amlsystem-ear/amlsystem-ejb-1.0-SNAPSHOT/AdPermissionFacade");
            
        } catch (NamingException ex) {
            logger.warning("Cannot do the JNDI Lookup to instantiate the User service : " + ex);
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // identify account to log to
        UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
        String username = userPassToken.getUsername();

        if (username == null) {
            logger.warning("Username is null.");
            return null;
        }

        // read password hash and salt from db
        AdUser user = this.adUserFacade.findByUsername(username);

        if (user == null) {
            logger.warning("No account found for user [" + username + "]");
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        String username = (String) getAvailablePrincipal(principals);

        Set<String> roleNames = new HashSet<>();
        
        AdUser user = this.adUserFacade.findByUsername(username);
        
        List<AdUserRoles> listaUsuarioRoles = this.adUserRolesFacade.findByAdUser(user);
        
        Set<String> permissions = new HashSet<>();
        
        if( listaUsuarioRoles != null && !listaUsuarioRoles.isEmpty() ){
            
            for(AdUserRoles us :  listaUsuarioRoles){
                roleNames.add(us.getAdRoleId().getName());     
                
                List<AdPermission> permisos = this.adPermissionFacade.findByAdRoleId(us.getAdRoleId());
                
                if(  permisos != null && !permisos.isEmpty()){
                    for (AdPermission p : permisos) {
                        permissions.add(p.getAdActionId().getAdModuleId().getId()+":"+p.getAdActionId().getId());
                    }
                }
                
                
            }
        }
        
        

        AuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        
        System.out.println("doGetAuthorizationInfo permissions: "+permissions);
        
        ((SimpleAuthorizationInfo)info).setStringPermissions(permissions);
        
        /**
         * If you want to do Permission Based authorization, you can grab the Permissions List associated to your user:
         * For example:
         * Set<String> permissions = new HashSet<>();
         * permissions.add(this.userService.findByUsername(username).getRole().getPermissions());
         * ((SimpleAuthorizationInfo)info).setStringPermissions(permissions);
         */
        return info;
    }

}

