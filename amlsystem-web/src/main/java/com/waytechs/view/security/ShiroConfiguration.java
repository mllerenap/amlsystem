package com.waytechs.view.security;

import com.waytechs.model.ejb.facades.AdMenuFacade;
import com.waytechs.model.ejb.facades.AdMenuRoleFacade;
import com.waytechs.model.ejb.facades.AdRoleFacade;
import com.waytechs.model.entities.AdMenu;
import com.waytechs.model.entities.AdMenuRole;
import com.waytechs.model.entities.AdRole;
import java.util.List;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;


/**
 * Created by nebrass on 17/11/2015.
 */
@Named
public class ShiroConfiguration {
    
    @Inject
    private AdMenuRoleFacade adMenuRoleFacade;
    
    @Inject
    private AdMenuFacade adMenuFacade;

    @Produces
    public WebSecurityManager getSecurityManager() {
        DefaultWebSecurityManager securityManager = null;
        if (securityManager == null) {
            AuthorizingRealm realm = new SecurityRealm();

            CredentialsMatcher credentialsMatcher = new PasswordMatcher();
            ((PasswordMatcher) credentialsMatcher).setPasswordService(new BCryptPasswordService());
            realm.setCredentialsMatcher(credentialsMatcher);

            securityManager = new DefaultWebSecurityManager(realm);

            CacheManager cacheManager = new EhCacheManager();
            ((EhCacheManager) cacheManager).setCacheManagerConfigFile("classpath:ehcache.xml");
            securityManager.setCacheManager(cacheManager);


            byte[] cypherKey = String.format("0x%s",
                    Hex.encodeToString(new AesCipherService().generateNewKey().getEncoded()))
                    .getBytes();

            RememberMeManager rememberMeManager = new CookieRememberMeManager();
            ((CookieRememberMeManager) rememberMeManager).setCipherKey(cypherKey);
            securityManager.setRememberMeManager(rememberMeManager);
        }
        return securityManager;
    }

    @Produces
    public FilterChainResolver getFilterChainResolver() {
        
        System.out.println("*********************** getFilterChainResolver LOAD INIT *************");
        
        FilterChainResolver filterChainResolver = null;
        if (filterChainResolver == null) {
            FormAuthenticationFilter authc = new FormAuthenticationFilter();
            AnonymousFilter anon = new AnonymousFilter();
            UserFilter user = new UserFilter();
            RolesAuthorizationFilter roles = new RolesAuthorizationFilter();
            
            MultipleRolesAuthorizationFilter multipleroles = new MultipleRolesAuthorizationFilter();

            authc.setLoginUrl(WebPages.LOGIN_URL);
            authc.setSuccessUrl(WebPages.HOME_URL);
            user.setLoginUrl(WebPages.LOGIN_URL);
            
            //roles.isAccessAllowed(request, response, roles);
            

            FilterChainManager fcMan = new DefaultFilterChainManager();
            fcMan.addFilter("authc", authc);
            fcMan.addFilter("anon", anon);
            fcMan.addFilter("user", user);
            fcMan.addFilter("roles", roles);
            fcMan.addFilter("multipleroles", multipleroles); 
            
            
            /*
            /javax.faces.resource/** = anon
            /login.jsf = authc
            /ui/admin/** = authc, roles[ADMINISTRADOR]
            /home.jsf = authc, roles[ADMINISTRADOR,INVITADO]
            */

            fcMan.createChain("/javax.faces.resource/**", "anon");
            fcMan.createChain("/api/auth/**", "anon");
            fcMan.createChain("/resources/**", "anon");
            
            //fcMan.addToChain(string, string1, string2);
            
            
            //fcMan.createChain("/ui/admin/**", "authc, roles[ADMINISTRADOR]");
            //fcMan.createChain("/home/*", "authc, roles[ADMINISTRADOR], roles[INVITADO]");
            
            
            
            List<AdMenu> listaMenu = adMenuFacade.findAll();
            if(  listaMenu != null & !listaMenu.isEmpty() ){
                for (AdMenu m : listaMenu) { 
                    
                    List<AdMenuRole> listaMenuRoles = adMenuRoleFacade.findByAdMenuId(m);
                    
                    System.out.println("listaMenuRoles: "+listaMenuRoles);
                    
                    String rolePattern = "multipleroles[#var]";
                    String rolePermitidos = "";

                    if(  listaMenuRoles != null & !listaMenuRoles.isEmpty() ){

                        for (AdMenuRole mr : listaMenuRoles) {
                            
                            if(!rolePermitidos.isEmpty()){
                                rolePermitidos = rolePermitidos + ", ";
                            }
                            
                            System.out.println("url: "+mr.getAdMenuId().getUrl()+" role: "+mr.getAdRoleId().getMnemonic());
                            //String rolItem = rolePattern.replace("#var", mr.getAdRoleId().getName());
                            rolePermitidos = rolePermitidos + "\"" + mr.getAdRoleId().getMnemonic()+ "\"";
                        }
                        
                        rolePermitidos = rolePattern.replace("#var", rolePermitidos);
                        
                        System.out.println("createChain url "+m.getUrl() +" rolePermitidos: "+rolePermitidos);
                        
                        fcMan.createChain(m.getUrl(), "authc, "+rolePermitidos);
                        
                    }
                }
            }
            
            //fcMan.createChain("/ui/admin/ad_users.jsf", "authc, roles[ADMINISTRADOR]"); 
            //fcMan.createChain("/home**", "authc, roles[ADMINISTRADOR], roles[INVITADO]");
            
            fcMan.createChain("/**", "user");

            PathMatchingFilterChainResolver resolver = new PathMatchingFilterChainResolver();
            resolver.setFilterChainManager(fcMan);
            filterChainResolver = resolver;
            
            System.out.println("*********************** getFilterChainResolver LOAD END *************");
        }
        return filterChainResolver;
    }

}
